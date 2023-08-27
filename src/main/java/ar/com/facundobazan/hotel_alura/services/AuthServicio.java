package ar.com.facundobazan.hotel_alura.services;

import ar.com.facundobazan.hotel_alura.dao.UsuarioDAO;
import ar.com.facundobazan.hotel_alura.entities.Usuario;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroCambioClaveUsuario;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroLogin;
import ar.com.facundobazan.hotel_alura.entities.records.RegistroUsuario;
import ar.com.facundobazan.hotel_alura.utils.Argon2;
import ar.com.facundobazan.hotel_alura.utils.JPAUtil;
import jakarta.persistence.EntityManager;

public class AuthServicio {

    public Boolean RegistrarUsuario(RegistroUsuario usuario) {

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

    public Boolean CambiarContraseña(RegistroCambioClaveUsuario usuario) {

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

    public RegistroLogin signIn(RegistroLogin login) {

        if (login == null || login.email().isBlank() || login.clave().isBlank()) return null;

        try (EntityManager em = JPAUtil.getEntityManager()) {

            UsuarioDAO usuarioDAO = new UsuarioDAO(em);
            Usuario usuarioAux = usuarioDAO.getByEmail(login.email());

            if (!Argon2.verificar(usuarioAux.getClave(), login.clave())) return null;
            return new RegistroLogin(usuarioAux.getId(), usuarioAux.getEmail(), null);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }

    public RegistroLogin signOut(RegistroLogin login){

        return new RegistroLogin(null, null, null);
    }
}
