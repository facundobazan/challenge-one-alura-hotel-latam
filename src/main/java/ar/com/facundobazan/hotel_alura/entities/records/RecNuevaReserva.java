package ar.com.facundobazan.hotel_alura.entities.records;

import ar.com.facundobazan.hotel_alura.entities.FormaPago;

import java.time.LocalDate;

public record RecNuevaReserva(LocalDate fechaEntrada,
                              LocalDate fechaSalida,
                              FormaPago formaPago) {

}
