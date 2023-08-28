package ar.com.facundobazan.hotel_alura.entities;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroHuesped;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "huespedes")
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String apellido;
    @Column(nullable = false)
    private String nombre;
    @Column(name = "documento", unique = true, nullable = false, updatable = false, length = 12)
    private String documento;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    private String nacionalidad;
    private String telefono;

    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reserva> reservas;

    public Huesped() {

        this.reservas = new ArrayList<>();
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public String getApellido() {

        return apellido;
    }

    public void setApellido(String apellido) {

        this.apellido = apellido;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {

        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {

        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNacionalidad() {

        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {

        this.nacionalidad = nacionalidad;
    }

    public String getTelefono() {

        return telefono;
    }

    public void setTelefono(String telefono) {

        this.telefono = telefono;
    }

    public List<Reserva> getReservas() {

        return reservas;
    }

    public void setReservas(List<Reserva> reservas) {

        this.reservas = reservas;
    }

    public void addReserva(Reserva reserva) {

        this.reservas.add(reserva);
    }

    public void asignarHuesped(RegistroHuesped huesped) {

        this.apellido = huesped.apellido();
        this.nombre = huesped.nombre();
        this.fechaNacimiento = huesped.fechaNacimiento();
        this.nacionalidad = huesped.nacionalidad();
        this.telefono = huesped.telefono();
        this.documento = huesped.documento();
    }

    public String getDocumento() {
        return documento;
    }
}
