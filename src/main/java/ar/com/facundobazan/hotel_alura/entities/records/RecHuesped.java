package ar.com.facundobazan.hotel_alura.entities.records;

import java.time.LocalDate;
import java.util.List;

public record RecHuesped(
        Long id,
        String apellido,
        String nombre,
        String documento,
        LocalDate fechaNacimiento,
        String nacionalidad,
        String telefono,
        List<RecReserva> reservas){

}
