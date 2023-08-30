package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.dao.UsuarioDAO;
import ar.com.facundobazan.hotel_alura.entities.FormaPago;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Reserva;
import ar.com.facundobazan.hotel_alura.entities.Usuario;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroPrecio;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroReserva;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaServicio {

    public Long registrarReserva(RegistroReserva reserva) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            PrecioServicio precioServicio = new PrecioServicio();
            RegistroPrecio precio = precioServicio.obtenerUltimaActualizacion();
            em.getTransaction().begin();

            Reserva nuevaReserva = convertir(reserva);
            double total = calcularPrecioFinal(reserva.fechaEntrada(), reserva.fechaSalida(), reserva.formaPago(), precio);
            nuevaReserva.setValor(total);
            nuevaReserva.setId(reservaDAO.reservar(nuevaReserva));
            em.getTransaction().commit();
            return nuevaReserva.getId();
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private Reserva convertir(RegistroReserva reserva) {
        return new Reserva(
                reserva.id(),
                reserva.fechaEntrada(),
                reserva.fechaSalida(),
                reserva.valor(),
                reserva.formaPago(),
                null
        );
    }

    private RegistroReserva convertir(Reserva reserva) {
        return new RegistroReserva(
                reserva.getId(),
                reserva.getFechaEntrada(),
                reserva.getFechaSalida(),
                reserva.getValor(),
                reserva.getFormaPago()
        );
    }

    public double calcularPrecioFinal(LocalDate fechaEntrada, LocalDate fechaSalida, FormaPago formaPago, RegistroPrecio precio) {
        int diasFechaEntrada = fechaEntrada.getDayOfYear();
        int diasFechaSalida = fechaSalida.getDayOfYear();
        int cantidadDias = diasFechaSalida - diasFechaEntrada + 1;
        double precioFinal = cantidadDias * precio.precioBase();

        switch (formaPago) {
            case EFECTIVO -> {
                return precioFinal * precio.tasaEfectivo();
            }
            case DEBITO -> {
                return precioFinal * precio.tasaDebito();
            }
            case CREDITO -> {
                return precioFinal * precio.tasaTarjeta();
            }
        }

        throw new RuntimeException("La operación falló");
    }

    public ArrayList<RegistroReserva> obtenerReservas() {

        ArrayList<RegistroReserva> registroReservas = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) reservaDAO.getAll();
            for (Reserva r: reservas) registroReservas.add(convertir(r));

            return registroReservas;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<RegistroReserva> obtenerReservasAsignadas() {

        ArrayList<RegistroReserva> registroReservas = new ArrayList<>();

        try (EntityManager em = JPAUtil.getEntityManager()) {

            ReservaDAO reservaDAO = new ReservaDAO(em);
            ArrayList<Reserva> reservas = (ArrayList<Reserva>) reservaDAO.getAllAsigned();
            for (Reserva r: reservas) registroReservas.add(convertir(r));

            return registroReservas;
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

            ReservaDAO reservaDAO = new ReservaDAO(em);
            Huesped huesped = reservaDAO.getOne(id).getHuesped();

            if (huesped != null){

                em.getTransaction().begin();
                HuespedDAO huespedDAO = new HuespedDAO(em);
                huesped.removeReserva(id);
                huespedDAO.update(huesped);
                em.getTransaction().commit();
            }

            System.out.println("huesped = " + huesped);
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
