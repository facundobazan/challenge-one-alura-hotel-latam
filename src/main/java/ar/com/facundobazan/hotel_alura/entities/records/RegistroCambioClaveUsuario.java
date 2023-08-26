package ar.com.facundobazan.hotel_alura.entities.records;

public record RegistroCambioClaveUsuario(Long id, String claveActual, String claveNueva, String reClaveNueva) {
}
