package ar.com.facundobazan.hotel_alura.entities.records;

import ar.com.facundobazan.hotel_alura.entities.FormaPago;

import java.time.LocalDate;

public record RegistroReserva(Long id,
                              LocalDate fechaEntrada,
                              LocalDate fechaSalida,
                              double valor,
                              FormaPago formaPago) {

}
