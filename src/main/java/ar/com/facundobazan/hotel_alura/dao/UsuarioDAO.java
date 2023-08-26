package ar.com.facundobazan.hotel_alura.dao;

import ar.com.facundobazan.hotel_alura.entities.Usuario;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroUsuario;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UsuarioDAO implements Crud<Usuario> {

    private final EntityManager entityManager;

    public UsuarioDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    @Override
    public Usuario getOne(Long id) {
        return null;
    }

    @Override
    public List<Usuario> getAll() {
        return null;
    }

    @Override
    public void create(Usuario usuario) {

        this.entityManager.persist(usuario);
    }

    @Override
    public void update(Usuario usuario) {

        this.entityManager.merge(usuario);
    }

    @Override
    public void delete(Long id) {

    }
}
