package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.PreciosDAO;
import ar.com.facundobazan.hotel_alura.entities.Precio;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroPrecio;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;

public class PrecioServicio {

    public void ActualizarPrecios(RegistroPrecio precio) {

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

    public RegistroPrecio obtenerUltimaActualizacion() {
        try (EntityManager em = JPAUtil.getEntityManager()) {

            PreciosDAO preciosDAO = new PreciosDAO(em);

            return convertir(preciosDAO.getLastRecord());

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    private Precio convertir(RegistroPrecio precio) {

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

    private RegistroPrecio convertir(Precio precio) {

        return new RegistroPrecio(
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
}
