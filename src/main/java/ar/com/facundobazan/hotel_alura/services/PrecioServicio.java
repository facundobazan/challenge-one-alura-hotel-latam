package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.PreciosDAO;
import ar.com.facundobazan.hotel_alura.entities.FormaPago;
import ar.com.facundobazan.hotel_alura.entities.Precio;
import ar.com.facundobazan.hotel_alura.entities.records.RecNuevoPrecio;
import ar.com.facundobazan.hotel_alura.entities.records.RecPrecio;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PrecioServicio {

    public void ActualizarPrecios(RecNuevoPrecio precio) {

        try (EntityManager em = JPAUtil.getEntityManager()) {

            PreciosDAO preciosDAO = new PreciosDAO(em);
            em.getTransaction().begin();
            Precio nuevoPrecio = convertir(precio);
            nuevoPrecio.setFechaActualizacion(LocalDateTime.now());
            preciosDAO.create(nuevoPrecio);
            em.getTransaction().commit();
        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public RecPrecio obtenerUltimaActualizacion() {
        try (EntityManager em = JPAUtil.getEntityManager()) {

            PreciosDAO preciosDAO = new PreciosDAO(em);

            return convertir(preciosDAO.getLastRecord());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private Precio convertir(RecNuevoPrecio precio) {

        return new Precio(
                null,
                null,
                precio.precioBase(),
                precio.habitacionSimple(),
                precio.habitacionDoble(),
                precio.habitacionMatrimonial(),
                precio.habitacionSuite(),
                precio.gimnasio(),
                precio.solarium(),
                precio.canalesPremium(),
                precio.servicioHabitacion(),
                precio.desayuno(),
                precio.merienda(),
                precio.cena(),
                precio.tasaEfectivo(),
                precio.tasaDebito(),
                precio.tasaTarjeta()
        );
    }

    private RecPrecio convertir(Precio precio) {

        return new RecPrecio(
                precio.getId(),
                precio.getPrecioBase(),
                precio.getHabitacionSimple(),
                precio.getHabitacionDoble(),
                precio.getHabitacionMatrimonial(),
                precio.getHabitacionSuite(),
                precio.getGimnasio(),
                precio.getSolarium(),
                precio.getCanalesPremium(),
                precio.getServicioHabitacion(),
                precio.getDesayuno(),
                precio.getMerienda(),
                precio.getCena(),
                precio.getTasaEfectivo(),
                precio.getTasaDebito(),
                precio.getTasaTarjeta()
        );
    }

    public double calcularPrecioFinal(LocalDate fechaEntrada, LocalDate fechaSalida, FormaPago formaPago) {

        RecPrecio precio = obtenerUltimaActualizacion();

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
}
