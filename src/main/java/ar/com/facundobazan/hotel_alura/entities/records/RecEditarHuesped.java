package ar.com.facundobazan.hotel_alura.entities.records;

import java.time.LocalDate;
import java.util.List;

public record RecEditarHuesped(
        Long id,
        String apellido,
        String nombre,
        String telefono){

}
