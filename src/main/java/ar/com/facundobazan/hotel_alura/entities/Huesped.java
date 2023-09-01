package ar.com.facundobazan.hotel_alura.entities;

import ar.com.facundobazan.hotel_alura.entities.records.RecHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecNuevoHuesped;
import ar.com.facundobazan.hotel_alura.entities.records.RecReserva;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

    @OneToMany(mappedBy = "huesped", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<Reserva> reservas = new ArrayList<>();

    public Huesped() {

    }

    public Huesped(
            Long id,
            String apellido,
            String nombre,
            String documento,
            LocalDate fechaNacimiento,
            String nacionalidad,
            String telefono,
            List<Reserva> reservas) {

        this.id = id;
        this.apellido = apellido;
        this.nombre = nombre;
        this.documento = documento;
        this.fechaNacimiento = fechaNacimiento;
        this.nacionalidad = nacionalidad;
        this.telefono = telefono;
        this.reservas = reservas;
    }

    public Huesped(RecHuesped huesped) {
        this.id = huesped.id();
        this.apellido = huesped.apellido();
        this.nombre = huesped.nombre();
        this.documento = huesped.documento();
        this.fechaNacimiento = huesped.fechaNacimiento();
        this.nacionalidad = huesped.nacionalidad();
        this.telefono = huesped.telefono();
        setRegistroReservas(huesped.reservas());
    }

    public Huesped(RecNuevoHuesped huesped) {
        this.apellido = huesped.apellido();
        this.nombre = huesped.nombre();
        this.documento = huesped.documento();
        this.fechaNacimiento = huesped.fechaNacimiento();
        this.nacionalidad = huesped.nacionalidad();
        this.telefono = huesped.telefono();
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

    public void setRegistroReservas(List<RecReserva> reservas) {

        reservas.forEach(r ->
                this.reservas
                        .add(new Reserva(
                                r.id(), r.fechaEntrada(),
                                r.fechaSalida(), r.valor(),
                                r.formaPago(), this)));
    }

    public void addReserva(Reserva reserva) {

        reserva.setHuesped(this);
        this.reservas.add(reserva);
    }

    public void addRegistroReserva(RecReserva reserva) {

        this.reservas.add(new Reserva(reserva));
    }

    public void asignarHuesped(RecHuesped huesped) {

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

    public void removeReservaById(long id) {

        this.reservas.removeIf(reserva -> reserva.getId() == id);
    }

    public void removeReserva(Reserva reserva) {

        this.reservas.remove(reserva);
    }

    public void removeRegistroReserva(RecReserva reserva) {

        this.reservas.removeIf(r -> Objects.equals(r.getId(), reserva.id()));
    }
}
