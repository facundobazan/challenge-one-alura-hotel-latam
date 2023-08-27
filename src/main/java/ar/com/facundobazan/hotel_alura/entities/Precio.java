package ar.com.facundobazan.hotel_alura.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "precios")
public class Precio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_actualizacion", nullable = false, updatable = false)
    private LocalDateTime fechaActualizacion;
    @Column(name = "precio_base", nullable = false, updatable = false)
    private double precioBase;
    @Column(name = "habitacion_simple", nullable = false, updatable = false)
    private double habitacionSimple;
    @Column(name = "habitacion_doble", nullable = false, updatable = false)
    private double habitacionDoble;
    @Column(name = "habitacion_matrimonial", nullable = false, updatable = false)
    private double habitacionMatrimonial;
    @Column(name = "habitacion_suite", nullable = false, updatable = false)
    private double habitacionSuite;
    @Column(name = "gimnasio", nullable = false, updatable = false)
    private double gimnasio;
    @Column(name = "solarium", nullable = false, updatable = false)
    private double solarium;
    @Column(name = "canales_premium", nullable = false, updatable = false)
    private double canalesPremium;
    @Column(name = "servicio_habitacion", nullable = false, updatable = false)
    private double servicioHabitacion;
    @Column(name = "desayuno", nullable = false, updatable = false)
    private double desayuno;
    @Column(name = "merienda", nullable = false, updatable = false)
    private double merienda;
    @Column(name = "cena", nullable = false, updatable = false)
    private double cena;

    public Precio() {

        this.precioBase = 0.0;
        this.habitacionSimple = 0.0;
        this.habitacionDoble = 0.0;
        this.habitacionMatrimonial = 0.0;
        this.habitacionSuite = 0.0;
        this.gimnasio = 0.0;
        this.solarium = 0.0;
        this.canalesPremium = 0.0;
        this.servicioHabitacion = 0.0;
        this.desayuno = 0.0;
        this.merienda = 0.0;
        this.cena = 0.0;
    }

    public Precio(Long id, LocalDateTime fechaActualizacion, double precioBase, double habitacionSimple, double habitacionDoble, double habitacionMatrimonial, double habitacionSuite, double gimnasio, double solarium, double canalesPremium, double servicioHabitacion, double desayuno, double merienda, double cena) {

        this.id = id;
        this.fechaActualizacion = fechaActualizacion;
        this.precioBase = precioBase;
        this.habitacionSimple = habitacionSimple;
        this.habitacionDoble = habitacionDoble;
        this.habitacionMatrimonial = habitacionMatrimonial;
        this.habitacionSuite = habitacionSuite;
        this.gimnasio = gimnasio;
        this.solarium = solarium;
        this.canalesPremium = canalesPremium;
        this.servicioHabitacion = servicioHabitacion;
        this.desayuno = desayuno;
        this.merienda = merienda;
        this.cena = cena;
    }

    public Long getId() {

        return id;
    }

    public LocalDateTime getFechaActualizacion() {

        return fechaActualizacion;
    }

    public double getPrecioBase() {

        return precioBase;
    }

    public double getHabitacionSimple() {

        return habitacionSimple;
    }

    public double getHabitacionDoble() {

        return habitacionDoble;
    }

    public double getHabitacionMatrimonial() {

        return habitacionMatrimonial;
    }

    public double getHabitacionSuite() {

        return habitacionSuite;
    }

    public double getGimnasio() {

        return gimnasio;
    }

    public double getSolarium() {

        return solarium;
    }

    public double getCanalesPremium() {

        return canalesPremium;
    }

    public double getServicioHabitacion() {

        return servicioHabitacion;
    }

    public double getDesayuno() {

        return desayuno;
    }

    public double getMerienda() {

        return merienda;
    }

    public double getCena() {

        return cena;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {

        this.fechaActualizacion = fechaActualizacion;
    }
}
