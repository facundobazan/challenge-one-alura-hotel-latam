package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.entities.*;
import ar.com.facundobazan.hotel_alura.entities.records.*;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import javax.sound.midi.Receiver;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaServicio {

    public RecReserva realizarReserva(RecNuevaReserva reserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            final double total = new PrecioServicio().calcularPrecioFinal(
                    reserva.fechaEntrada(),
                    reserva.fechaSalida(),
                    reserva.formaPago());
            Reserva nuevaReserva = new Reserva(reserva, total);

            ReservaDAO reservaDAO = new ReservaDAO(em);
            em.getTransaction().begin();
            nuevaReserva.setId(reservaDAO.reservar(nuevaReserva));
            em.getTransaction().commit();

            return new RecReserva(
                    nuevaReserva.getId(),
                    nuevaReserva.getFechaEntrada(),
                    nuevaReserva.getFechaSalida(),
                    nuevaReserva.getValor(),
                    nuevaReserva.getFormaPago());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void confirmarReserva(RecNuevoHuesped huesped, long reserva_id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {


            RecReserva reservaAux = obtenerReservaPorId(reserva_id);
            RecHuesped huespedAux = new HuespedServicio().registrarHuesped(huesped);

            new HuespedServicio().agregrarReserva(huespedAux.id(), reservaAux);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public ArrayList<RecReserva> obtenerReservas() {

        ArrayList<RecReserva> recReservas = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) reservaDAO.getAll();

            for (Reserva r : reservas)
                recReservas.add(
                        new RecReserva(
                                r.getId(),
                                r.getFechaEntrada(),
                                r.getFechaSalida(),
                                r.getValor(),
                                r.getFormaPago()));

            return recReservas;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<RecReserva> obtenerReservasAsignadas() {

        ArrayList<RecReserva> recReservas = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) reservaDAO.getAllAsigned();

            for (Reserva r : reservas)
                recReservas.add(new RecReserva(
                        r.getId(),
                        r.getFechaEntrada(),
                        r.getFechaSalida(),
                        r.getValor(),
                        r.getFormaPago()));

            return recReservas;

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public void borrarReserva(long id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);

            em.getTransaction().begin();
            reservaDAO.delete(id);
            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void borrarReservaAsignada(long id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            Huesped huesped = new HuespedServicio().obtenerPorId(id);

            if (huesped != null) {

                em.getTransaction().begin();
                HuespedDAO huespedDAO = new HuespedDAO(em);
                huesped.removeReservaById(id);
                huespedDAO.update(huesped);
                em.getTransaction().commit();
            }

            System.out.println("huesped = " + huesped);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void modificarReserva(RecReserva reserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            Reserva reservaAux = reservaDAO.getOne(reserva.id());

            em.getTransaction().begin();

            reservaAux.setFechaEntrada(reserva.fechaEntrada());
            reservaAux.setFechaSalida(reserva.fechaSalida());
            reservaAux.setFormaPago(reserva.formaPago());
            reservaAux.setValor(new PrecioServicio()
                    .calcularPrecioFinal(reserva.fechaEntrada(), reserva.fechaSalida(), reserva.formaPago()));

            reservaDAO.update(reservaAux);

            em.getTransaction().commit();

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public RecReserva obtenerReservaPorId(long id) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            Reserva reserva = reservaDAO.getOne(id);

            return new RecReserva(
                    reserva.getId(),
                    reserva.getFechaEntrada(),
                    reserva.getFechaSalida(),
                    reserva.getValor(),
                    reserva.getFormaPago());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }
}
