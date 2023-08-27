package ar.com.facundobazan.hotel_alura.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
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
    private BigDecimal precioBase;
    @Column(name = "habitacion_simple", nullable = false, updatable = false)
    private BigDecimal habitacionSimple;
    @Column(name = "habitacion_doble", nullable = false, updatable = false)
    private BigDecimal habitacionDoble;
    @Column(name = "habitacion_matrimonial", nullable = false, updatable = false)
    private BigDecimal habitacionMatrimonial;
    @Column(name = "habitacion_suite", nullable = false, updatable = false)
    private BigDecimal habitacionSuite;
    @Column(name = "gimnasio", nullable = false, updatable = false)
    private BigDecimal gimnasio;
    @Column(name = "solarium", nullable = false, updatable = false)
    private BigDecimal solarium;
    @Column(name = "canales_premium", nullable = false, updatable = false)
    private BigDecimal canalesPremium;
    @Column(name = "servicio_habitacion", nullable = false, updatable = false)
    private BigDecimal servicioHabitacion;
    @Column(name = "desayuno", nullable = false, updatable = false)
    private BigDecimal desayuno;
    @Column(name = "merienda", nullable = false, updatable = false)
    private BigDecimal merienda;
    @Column(name = "cena", nullable = false, updatable = false)
    private BigDecimal cena;

    public Precio() {

        this.precioBase = new BigDecimal(0.0);
        this.habitacionSimple = new BigDecimal(0.0);
        this.habitacionDoble = new BigDecimal(0.0);
        this.habitacionMatrimonial = new BigDecimal(0.0);
        this.habitacionSuite = new BigDecimal(0.0);
        this.gimnasio = new BigDecimal(0.0);
        this.solarium = new BigDecimal(0.0);
        this.canalesPremium = new BigDecimal(0.0);
        this.servicioHabitacion = new BigDecimal(0.0);
        this.desayuno = new BigDecimal(0.0);
        this.merienda = new BigDecimal(0.0);
        this.cena = new BigDecimal(0.0);
    }

    public Precio(Long id, LocalDateTime fechaActualizacion, BigDecimal precioBase, BigDecimal habitacionSimple, BigDecimal habitacionDoble, BigDecimal habitacionMatrimonial, BigDecimal habitacionSuite, BigDecimal gimnasio, BigDecimal solarium, BigDecimal canalesPremium, BigDecimal servicioHabitacion, BigDecimal desayuno, BigDecimal merienda, BigDecimal cena) {

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

    public BigDecimal getPrecioBase() {

        return precioBase;
    }

    public BigDecimal getHabitacionSimple() {

        return habitacionSimple;
    }

    public BigDecimal getHabitacionDoble() {

        return habitacionDoble;
    }

    public BigDecimal getHabitacionMatrimonial() {

        return habitacionMatrimonial;
    }

    public BigDecimal getHabitacionSuite() {

        return habitacionSuite;
    }

    public BigDecimal getGimnasio() {

        return gimnasio;
    }

    public BigDecimal getSolarium() {

        return solarium;
    }

    public BigDecimal getCanalesPremium() {

        return canalesPremium;
    }

    public BigDecimal getServicioHabitacion() {

        return servicioHabitacion;
    }

    public BigDecimal getDesayuno() {

        return desayuno;
    }

    public BigDecimal getMerienda() {

        return merienda;
    }

    public BigDecimal getCena() {

        return cena;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {

        this.fechaActualizacion = fechaActualizacion;
    }
}
