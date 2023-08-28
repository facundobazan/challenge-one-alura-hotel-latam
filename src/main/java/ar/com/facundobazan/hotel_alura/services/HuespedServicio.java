package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.HuespedDAO;
import ar.com.facundobazan.hotel_alura.entities.Huesped;
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
}
