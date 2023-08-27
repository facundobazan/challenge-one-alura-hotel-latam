package ar.com.facundobazan.hotel_alura.entities.records;

import ar.com.facundobazan.hotel_alura.entities.Precio;

import java.math.BigDecimal;

public record RegistroPrecio(BigDecimal precioBase, BigDecimal habitacionSimple, BigDecimal habitacionDoble, BigDecimal habitacionMatrimonial, BigDecimal habitacionSuite, BigDecimal gimnasio, BigDecimal solarium, BigDecimal canalesPremium, BigDecimal servicioHabitacion, BigDecimal desayuno, BigDecimal merienda, BigDecimal cena) {

}
