package ar.com.facundobazan.hotel_alura.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {

    private static final EntityManagerFactory FACTORY = Persistence.createEntityManagerFactory("mysql");

    public static EntityManager getEntityManager(){

        return FACTORY.createEntityManager();
    }
}
