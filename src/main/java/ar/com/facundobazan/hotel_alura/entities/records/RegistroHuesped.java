package ar.com.facundobazan.hotel_alura.entities.records;

import java.time.LocalDate;

public record RegistroHuesped(Long id, String apellido, String nombre, String documento, LocalDate fechaNacimiento, String nacionalidad, String telefono) {
}
