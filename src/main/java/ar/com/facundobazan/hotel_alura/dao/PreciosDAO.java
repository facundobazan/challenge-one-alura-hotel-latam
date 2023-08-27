package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Precio;
import jakarta.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.List;

public class PreciosDAO{

    private final EntityManager entityManager;

    public PreciosDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public Precio fetById(Long id) {

        return this.entityManager.find(Precio.class, id);
    }

    public List<Precio> getAll() {

        String query = "SELECT P FROM Precio AS P";
        return this.entityManager.createQuery(query, Precio.class).getResultList();
    }

    public void create(Precio precio) {

        this.entityManager.persist(precio);
    }

    /*public void update(Precio precio) {

        this.entityManager.merge(precio);
    }*/

    public Precio getLastRecord() {

        String query = "SELECT P FROM Precio AS P WHERE fechaActualizacion = (SELECT MAX (fechaActualizacion) FROM Precio)";
        return this.entityManager.createQuery(query, Precio.class).getSingleResult();
    }
}
