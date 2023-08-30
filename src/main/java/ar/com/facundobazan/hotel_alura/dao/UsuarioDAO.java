package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class UsuarioDAO {

    private final EntityManager entityManager;

    public UsuarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Usuario getById(Long id) {

        return this.entityManager.find(Usuario.class, id);
    }

    public Usuario getByEmail(String email) {

        String queryBase = "SELECT U FROM Usuario U WHERE email = :email";
        TypedQuery<Usuario> query = this.entityManager.createQuery(queryBase, Usuario.class);
        query.setParameter("email", email);

        return query.getSingleResult();
    }

    public Usuario signIn(String email, String password) {

        String queryBase = "SELECT U FROM Usuario U WHERE email = :email and clave = :password";
        TypedQuery<Usuario> query = this.entityManager.createQuery(queryBase, Usuario.class);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return query.getSingleResult();
    }

    public void create(Usuario usuario) {

        this.entityManager.persist(usuario);
    }

    public void update(Usuario usuario) {

        this.entityManager.merge(usuario);
    }
}