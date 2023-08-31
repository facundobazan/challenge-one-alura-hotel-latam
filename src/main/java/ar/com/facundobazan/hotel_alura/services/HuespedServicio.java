package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Reserva;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroReserva;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.util.ArrayList;
import java.util.List;

public class HuespedServicio {

    public RegistroHuesped buscarPorDocumento(String documento) {

        if (documento.isBlank())
            throw new RuntimeException("No se puede realizar la busqueda, se recibieron valores nulos");

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped huesped = huespedDAO.getByDocument(documento);

            if (huesped == null) return null;

            return new RegistroHuesped(
                    huesped.getId(),
                    huesped.getApellido(),
                    huesped.getNombre(),
                    huesped.getDocumento(),
                    huesped.getFechaNacimiento(),
                    huesped.getNacionalidad(),
                    huesped.getTelefono()
            );
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void registrarReserva(RegistroHuesped huesped, long idReserva) {

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

    public ArrayList<RegistroHuesped> obtenerHuespedes() {

        ArrayList<RegistroHuesped> huespedes = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            List<Huesped> results = huespedDAO.getAll();

            if (results.isEmpty()) return huespedes;

            for (Huesped h : results)
                huespedes.add(new RegistroHuesped(
                        h.getId(),
                        h.getApellido(),
                        h.getNombre(),
                        h.getDocumento(),
                        h.getFechaNacimiento(),
                        h.getNacionalidad(),
                        h.getTelefono()));

        } catch (Exception e) {

            e.printStackTrace();
        }

        return huespedes;
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

    public void modificarHuesped(RegistroHuesped huesped) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            em.getTransaction().begin();
            huespedDAO.update(new Huesped(huesped));
            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void agregrarReserva(long id, RegistroReserva reserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            HuespedDAO huespedDAO = new HuespedDAO(em);
            em.getTransaction().begin();
            Huesped huesped = huespedDAO.getOne(id);
            if (huesped == null) throw new Exception("No se encontro el usuario");
            huesped.addReserva(new Reserva(
                    reserva.fechaEntrada(),
                    reserva.fechaSalida(),
                    new PrecioServicio()
                            .calcularPrecioFinal(
                                    reserva.fechaEntrada(),
                                    reserva.fechaSalida(),
                                    reserva.formaPago()),
                    reserva.formaPago(),
                    huesped
            ));
            huespedDAO.update(huesped);
            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
