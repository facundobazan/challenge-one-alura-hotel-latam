package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Huesped;
import ar.com.facundobazan.hotel_alura.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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

    public Huesped getByDocument(String documento) {

        String query = "SELECT H FROM Huesped H WHERE documento = :documento";
        TypedQuery<Huesped> typedQuery = this.entityManager.createQuery(query, Huesped.class);
        typedQuery.setParameter("documento", documento);

        return typedQuery.getSingleResult();
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
    public Huesped create(Huesped huesped) {

        this.entityManager.persist(huesped);
        return huesped;
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

    public void getByReservaId(long id) {

        /*String queryBase = "SELECT H , R" +
                "FROM Huesped H " +
                "INNER JOIN Reserva R " +
                "ON R.Huesped = H " +
                "WHERE R.id = :id";
        TypedQuery<Huesped> query = this.entityManager.createQuery(queryBase, Huesped.class);
        query.setParameter("id", id);

        return query.getSingleResult();*/
    }
}
