package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Reserva;
import ar.com.facundobazan.hotel_alura.entities.records.RecHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecNuevoHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecReserva;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.*;
import java.util.stream.Collectors;

public class HuespedServicio {

    public RecHuesped buscarPorDocumento(String documento) {

        if (documento.isBlank())
            throw new RuntimeException("No se puede realizar la busqueda, se recibieron valores nulos");

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped huesped = huespedDAO.getByDocument(documento);

            if (huesped == null) return null;

            List<Reserva> reservaList = huesped.getReservas();

            return new RecHuesped(
                    huesped.getId(),
                    huesped.getApellido(),
                    huesped.getNombre(),
                    huesped.getDocumento(),
                    huesped.getFechaNacimiento(),
                    huesped.getNacionalidad(),
                    huesped.getTelefono(),
                    huesped.getReservas().stream()
                            .map(reserva -> new RecReserva(
                                    reserva.getId(),
                                    reserva.getFechaEntrada(),
                                    reserva.getFechaSalida(),
                                    reserva.getValor(),
                                    reserva.getFormaPago()))
                            .collect(Collectors.toList())
            );
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void registrarReserva(RecHuesped huesped, long idReserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            //  Se obtiene la reserva
            ReservaDAO reservaDAO = new ReservaDAO(em);
            Reserva reserva = reservaDAO.getOne(idReserva);
            if (reserva == null) throw new RuntimeException("No se ha encontrado la reserva");

            // Si existe, se obtiene el huesped
            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped huespedAux = null;
            try {

                huespedAux = huespedDAO.getByDocument(huesped.documento());
            } catch (Exception e) {

                e.printStackTrace();
            }

            em.getTransaction().begin();
            if (huespedAux == null) {

                huespedAux = new Huesped();
                huespedAux.asignarHuesped(huesped);
                huespedAux.addReserva(reserva);
                huespedAux = huespedDAO.create(huespedAux);
                reserva.setHuesped(huespedAux);
                reservaDAO.update(reserva);
            } else {
                reserva.setHuesped(huespedAux);
                reservaDAO.update(reserva);
            }
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public ArrayList<RecHuesped> obtenerHuespedes() {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            List<Huesped> huespedesAux = huespedDAO.getAll();

            List<RecReserva> reservas = new ArrayList<>();

            List<RecHuesped> huespedes = new ArrayList<>();

            huespedesAux.forEach(huesped -> {
                huespedes.add(
                        new RecHuesped(
                                huesped.getId(),
                                huesped.getApellido(),
                                huesped.getNombre(),
                                huesped.getDocumento(),
                                huesped.getFechaNacimiento(),
                                huesped.getNacionalidad(),
                                huesped.getTelefono(),
                                huesped.getReservas()
                                        .stream()
                                        .filter(r -> r.getId() == huesped.getId())
                                        .map(r ->
                                                new RecReserva(
                                                        r.getId(),
                                                        r.getFechaEntrada(),
                                                        r.getFechaSalida(),
                                                        r.getValor(),
                                                        r.getFormaPago()))
                                        .collect(Collectors.toList())));
            });

            /*if (results.isEmpty()) return huespedes;

            for (Huesped h : results)
                huespedes.add(new RecHuesped(
                        h.getId(),
                        h.getApellido(),
                        h.getNombre(),
                        h.getDocumento(),
                        h.getFechaNacimiento(),
                        h.getNacionalidad(),
                        h.getTelefono(),
                        reservas);*/

            return (ArrayList<RecHuesped>) huespedes;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void borrarHuesped(long id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            em.getTransaction().begin();
            huespedDAO.delete(id);
            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregrarReserva(long id, RecReserva reserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            em.getTransaction().begin();

            Huesped huesped = huespedDAO.getOne(id);

            if (huesped == null) throw new Exception("No se encontro el usuario");

            final Reserva RESERVA = new Reserva(
                    reserva.id(),
                    reserva.fechaEntrada(),
                    reserva.fechaSalida(),
                    reserva.valor(),
                    reserva.formaPago(),
                    huesped);

            huesped.addReserva(RESERVA);
            huespedDAO.update(huesped);

            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void asignarReserva(long huespedId, long reservaId) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);

            Huesped huesped = obtenerPorId(huespedId);
            RecReserva reserva = new ReservaServicio().obtenerReservaPorId(reservaId);

            if (huesped == null) throw new Exception("No se encontro el usuario");
            if (reserva == null) throw new Exception("No se encontro la reserva");

            em.getTransaction().begin();

            huesped.addReserva(new Reserva(
                    reserva.id(),
                    reserva.fechaEntrada(),
                    reserva.fechaSalida(),
                    reserva.valor(),
                    reserva.formaPago(),
                    huesped
            ));

            huespedDAO.update(huesped);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void cancelarReserva(long reservaId) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped huesped = huespedDAO.getByReservaId(reservaId);
            if (huesped == null) throw new Exception("No se encontro el usuario");

            em.getTransaction().begin();
            huesped.removeReservaById(reservaId);
            new HuespedDAO(em).update(huesped);
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    public Huesped obtenerPorId(long id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            return new HuespedDAO(em).getOne(id);
        }
    }


    public void modificar(RecHuesped huesped) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            em.getTransaction().begin();

            Huesped huespedAux = obtenerPorId(huesped.id());
            huespedAux.setApellido(huesped.apellido());
            huespedAux.setNombre(huesped.nombre());
            huespedAux.setTelefono(huesped.telefono());
            huespedAux.setRegistroReservas(huesped.reservas());
            new HuespedDAO(em).update(huespedAux);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    private void actualizar(Huesped huesped) {

        final Huesped HUESPED = new Huesped(
                huesped.getId(),
                huesped.getApellido(),
                huesped.getNombre(),
                huesped.getDocumento(),
                huesped.getFechaNacimiento(),
                huesped.getNacionalidad(),
                huesped.getTelefono(),
                huesped.getReservas()
        );

        actualizar(HUESPED);
    }

    public RecHuesped registrarHuesped(RecNuevoHuesped huesped) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            em.getTransaction().begin();

            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped nuevoHuesped = huespedDAO.create(new Huesped(huesped));

            em.getTransaction().commit();

            List<RecReserva> reservas = new ArrayList<>();
            nuevoHuesped.getReservas().forEach(r -> {
                        reservas.add(new RecReserva(
                                r.getId(),
                                r.getFechaEntrada(),
                                r.getFechaSalida(),
                                r.getValor(),
                                r.getFormaPago()));
                    }
            );

            return new RecHuesped(
                    nuevoHuesped.getId(),
                    nuevoHuesped.getApellido(),
                    nuevoHuesped.getNombre(),
                    nuevoHuesped.getDocumento(),
                    nuevoHuesped.getFechaNacimiento(),
                    nuevoHuesped.getNacionalidad(),
                    nuevoHuesped.getTelefono(),
                    reservas
            );
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
