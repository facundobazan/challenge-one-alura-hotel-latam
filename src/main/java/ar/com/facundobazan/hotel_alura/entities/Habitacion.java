package ar.com.facundobazan.hotel_alura.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String piso;
    private String departamento;
    private int habitacionSimple;
    private int habitacionDoble;
    private int habitacionMatrimonial;
    private int habitacionSuite;
    @Embedded
    private Servicios servicios;

    public Habitacion() {

    }

    public Habitacion(Long id, String piso, String departamento, int habitacionSimple, int habitacionDoble, int habitacionMatrimonial, int habitacionSuite, Servicios servicios) {

        this.id = id;
        this.piso = piso;
        this.departamento = departamento;
        this.habitacionSimple = habitacionSimple;
        this.habitacionDoble = habitacionDoble;
        this.habitacionMatrimonial = habitacionMatrimonial;
        this.habitacionSuite = habitacionSuite;
        this.servicios = servicios;
    }

    public Long getId() {

        return id;
    }

    public String getPiso() {

        return piso;
    }

    public String getDepartamento() {

        return departamento;
    }

    public int getHabitacionSimple() {

        return habitacionSimple;
    }

    public int getHabitacionDoble() {

        return habitacionDoble;
    }

    public int getHabitacionMatrimonial() {

        return habitacionMatrimonial;
    }

    public int getHabitacionSuite() {

        return habitacionSuite;
    }

    public Servicios getServicios() {

        return servicios;
    }
}
