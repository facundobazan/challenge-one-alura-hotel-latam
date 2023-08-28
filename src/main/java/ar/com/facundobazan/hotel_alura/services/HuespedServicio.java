package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Reserva;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class HuespedServicio {

    public RegistroHuesped buscarPorDocumento(String documento){

        if (documento.isBlank()) throw new RuntimeException("No se puede realizar la busqueda, se recibieron valores nulos");

        try(EntityManager em = JPAUtil.getEntityManager()){

            HuespedDAO huespedDAO = new HuespedDAO(em);
            Huesped huesped = huespedDAO.getByDocumento(documento);

            if (huesped == null) return null;

            return new RegistroHuesped(
                    huesped.getApellido(),
                    huesped.getNombre(),
                    huesped.getDocumento(),
                    huesped.getFechaNacimiento(),
                    huesped.getNacionalidad(),
                    huesped.getTelefono()
            );
        } catch (Exception e){

            e.printStackTrace();
        }

        return null;
    }

    public void registrarReserva(RegistroHuesped huesped, long idReserva) {

        try(EntityManager em = JPAUtil.getEntityManager()){

            ReservaDAO reservaDAO = new ReservaDAO(em);
            Reserva reserva = reservaDAO.getOne(idReserva);
            if (reserva == null) throw new RuntimeException("No se ha encontrado la reserva");

            Huesped nuevoHuesped = new Huesped();
            nuevoHuesped.asignarHuesped(huesped);
            nuevoHuesped.addReserva(reserva);

            em.getTransaction().begin();
            HuespedDAO huespedDAO = new HuespedDAO(em);
            huespedDAO.create(nuevoHuesped);
            em.getTransaction().commit();
        } catch (Exception e){

            e.printStackTrace();
        }
    }
}
