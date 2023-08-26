package ar.com.facundobazan.hotel_alura.utils;

import de.mkammerer.argon2.Argon2Factory;

public class Argon2 {
    private static final de.mkammerer.argon2.Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);

    public static String encriptar(String cadena){

        return  argon2.hash(1, 1024, 1, cadena);
    }

    public static Boolean verificar(String cadenaCodificada, String cadena){

        return argon2.verify(cadenaCodificada, cadena);
    }
}
