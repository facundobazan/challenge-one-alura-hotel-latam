package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.UsuarioDAO;
import ar.com.facundobazan.hotel_alura.entities.Usuario;
import ar.com.facundobazan.hotel_alura.entities.records.RecUsuarioCambioClave;
import ar.com.facundobazan.hotel_alura.entities.records.RecLogin;
import ar.com.facundobazan.hotel_alura.entities.records.RecRegistroUsuario;
import ar.com.facundobazan.hotel_alura.utils.Argon2;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class AuthServicio {

    public Boolean RegistrarUsuario(RecRegistroUsuario usuario) {

        if (usuario.clave().equals(usuario.reClave())) {

            try (EntityManager em = JPAUtil.getEntityManager()) {

                em.getTransaction().begin();
                UsuarioDAO usuarioDAO = new UsuarioDAO(em);
                Usuario nuevoUsuario = new Usuario(usuario);

                nuevoUsuario.setClave(Argon2.encriptar(nuevoUsuario.getClave()));

                usuarioDAO.create(nuevoUsuario);
                em.getTransaction().commit();
            } catch (Exception e) {

                e.printStackTrace();
            }

            return true;

        } else return false;
    }

    public Boolean CambiarContraseña(RecUsuarioCambioClave usuario) {

        if (usuario.claveNueva().equals(usuario.reClaveNueva())) {

            try (EntityManager em = JPAUtil.getEntityManager()) {

                em.getTransaction().begin();
                UsuarioDAO usuarioDAO = new UsuarioDAO(em);
                Usuario usuarioAux = usuarioDAO.getById(usuario.id());

                if (!Argon2.verificar(usuarioAux.getClave(), usuario.claveActual())) return false;

                usuarioAux.setClave(Argon2.encriptar(usuario.reClaveNueva()));
                usuarioDAO.update(usuarioAux);
                em.getTransaction().commit();
            } catch (Exception e) {

                e.printStackTrace();
            }

            return true;

        } else return false;
    }

    public RecLogin signIn(RecLogin login) {

        if (login == null || login.email().isBlank() || login.clave().isBlank()) return null;

        try (EntityManager em = JPAUtil.getEntityManager()) {

            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            Usuario usuarioAux = usuarioDAO.getByEmail(login.email());

            if (!Argon2.verificar(usuarioAux.getClave(), login.clave())) return null;
            return new RecLogin(usuarioAux.getId(), usuarioAux.getEmail(), null);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public RecLogin signOut(RecLogin login){

        return new RecLogin(null, null, null);
    }
}
