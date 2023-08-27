package ar.com.facundobazan.hotel_alura.entities;

import jakarta.persistence.Embeddable;

@Embeddable
public class Servicios {

    private boolean gimnasio;
    private boolean solarium;
    private boolean canalesPremium;
    private boolean servicioHabitacion;
    private boolean desayuno;
    private boolean merienda;
    private boolean cena;

    public Servicios() {

    }

    public Servicios(boolean gimnasio, boolean solarium, boolean canalesPremium, boolean servicioHabitacion, boolean desayuno, boolean merienda, boolean cena) {

        this.gimnasio = gimnasio;
        this.solarium = solarium;
        this.canalesPremium = canalesPremium;
        this.servicioHabitacion = servicioHabitacion;
        this.desayuno = desayuno;
        this.merienda = merienda;
        this.cena = cena;
    }
}
