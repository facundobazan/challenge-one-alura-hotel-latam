package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Huesped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class HuespedDAO implements Crud<Huesped> {

    private final EntityManager entityManager;

    public HuespedDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public Huesped getOne(Long id) {

        return this.entityManager.find(Huesped.class, id);
    }

    public List<Huesped> find(String apellido, String nombre) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Huesped> query = builder.createQuery(Huesped.class);
        Root<Huesped> from = query.from(Huesped.class);

        Predicate filtro = builder.and();
        if (!apellido.isBlank()) filtro = builder.and(filtro, builder.equal(from.get("apellido"), apellido));
        if (!nombre.isBlank()) filtro = builder.and(filtro, builder.equal(from.get("nombre"), nombre));

        query = query.where(filtro);
        return this.entityManager.createQuery(query).getResultList();
    }

    @Override
    public List<Huesped> getAll() {

        String query = "SELECT H FROM Huesped H";
        return this.entityManager.createQuery(query, Huesped.class).getResultList();
    }

    @Override
    public void create(Huesped huesped) {

        this.entityManager.persist(huesped);
    }

    @Override
    public void update(Huesped huesped) {

        this.entityManager.merge(huesped);
    }

    @Override
    public void delete(Long id) {

        Huesped huesped = getOne(id);
        this.entityManager.remove(huesped);
    }
}
