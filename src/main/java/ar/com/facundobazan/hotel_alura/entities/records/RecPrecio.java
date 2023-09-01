package ar.com.facundobazan.hotel_alura.entities.records;

public record RecPrecio(long id, double precioBase, double habitacionSimple, double habitacionDoble,
                        double habitacionMatrimonial, double habitacionSuite, double gimnasio, double solarium,
                        double canalesPremium, double servicioHabitacion, double desayuno, double merienda,
                        double cena, double tasaEfectivo, double tasaDebito, double tasaTarjeta) {

}
