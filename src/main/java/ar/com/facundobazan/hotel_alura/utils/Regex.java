package ar.com.facundobazan.hotel_alura.utils;

public class Regex {

    public static boolean validarLongitudMinima(int longitudMinima, String cadena) {
        String regex = String.format("^.{%s,}$", longitudMinima);
        return cadena.matches(regex);
    }

    public static boolean validarLongitudMaxima(int longitudMaxima, String cadena) {
        String regex = String.format("^.{%s,}$", longitudMaxima);
        return cadena.matches(regex);
    }

    public static boolean validarRangoLongitud(int longitudMinima, int longitudMaxima, String cadena) {
        String regex = String.format("^.{%s,%s}$", longitudMinima, longitudMaxima);
        return cadena.matches(regex);
    }

    /*
    *     ^ indica el inicio de la cadena.
    (?=.*[a-z]) es una comprobación anticipada para asegurarse de que al menos una letra minúscula esté presente en cualquier parte de la cadena.
    .* coincide con cualquier número de caracteres (incluidos cero).
    $ indica el final de la cadena.
    * */
    public static boolean validarMinimoCaracteresMinusculas(int longitudMinima, String cadena) {
        String regex = String.format("^(?=.*[a-z]){%s,}.*$", longitudMinima);
        return cadena.matches(regex);
    }

    public static boolean validarMaximoCaracteresMinusculas(int longitudMaxima, String cadena) {
        String regex = String.format("^(?=.*[a-z]){,%s}.*$", longitudMaxima);
        return cadena.matches(regex);
    }

    public static boolean validarRangoCaracteresMinusculas(int longitudMinima, int longitudMaxima, String cadena) {
        String regex = String.format("^(?=.*[a-z]){%s,%s}.*$", longitudMinima, longitudMaxima);
        return cadena.matches(regex);
    }

    public static boolean validarMinimoCaracteresMayusculas(int longitudMinima, String cadena) {
        String regex = String.format("^(?=.*[A-A]){%s,}.*$", longitudMinima);
        return cadena.matches(regex);
    }

    public static boolean validarMaximoCaracteresMayusculas(int longitudMaxima, String cadena) {
        String regex = String.format("^(?=.*[A-Z]){,%s}.*$", longitudMaxima);
        return cadena.matches(regex);
    }

    public static boolean validarRangoCaracteresMayusculas(int longitudMinima, int longitudMaxima, String cadena) {
        String regex = String.format("^(?=.*[A-Z]){%s,%s}.*$", longitudMinima, longitudMaxima);
        return cadena.matches(regex);
    }

    public static boolean validarCorreo(String cadena){
        String regex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return cadena.matches(regex);
    }

    public static boolean validarDatosNumericos(String cadena){
        String regex = "^[0-9]$";
        return cadena.matches(regex);
    }

    public static boolean validarDatosString(String cadena){
        String regex = "^[a-zA-Z]$";
        return cadena.matches(regex);
    }
}
