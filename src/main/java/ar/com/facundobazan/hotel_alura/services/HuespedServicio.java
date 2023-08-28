package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.dao.ReservaDAO;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Reserva;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

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
}
