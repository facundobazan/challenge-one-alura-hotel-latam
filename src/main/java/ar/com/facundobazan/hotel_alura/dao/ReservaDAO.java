package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Reserva;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.time.LocalDate;
import java.util.List;

public class ReservaDAO implements Crud<Reserva> {

    private final EntityManager entityManager;

    public ReservaDAO(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    @Override
    public Reserva getOne(Long id) {

        return this.entityManager.find(Reserva.class, id);
    }

    public List<Reserva> findByDate(LocalDate fechaReserva) {

        String queryBase = "SELECT R FROM Reserva R WHERE fecha_reserva = :fechaReserva";
        TypedQuery<Reserva> query = this.entityManager.createQuery(queryBase, Reserva.class);
        query.setParameter("fechaReserva", fechaReserva);

        return query.getResultList();
    }

    @Override
    public List<Reserva> getAll() {

        String query = "SELECT R FROM Reserva R";
        return this.entityManager.createQuery(query, Reserva.class).getResultList();
    }

    @Override
    public Reserva create(Reserva reserva) {

        this.entityManager.persist(reserva);
        return reserva;
    }

    public Long reservar(Reserva reserva) {


        this.entityManager.persist(reserva);
        return reserva.getId();
    }

    @Override
    public void update(Reserva reserva) {

        this.entityManager.merge(reserva);
    }

    @Override
    public void delete(Long id) {

        Reserva reserva = getOne(id);
        this.entityManager.remove(reserva);
    }
}
