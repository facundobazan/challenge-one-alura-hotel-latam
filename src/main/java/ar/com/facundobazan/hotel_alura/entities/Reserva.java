package ar.com.facundobazan.hotel_alura.entities;

import ar.com.facundobazan.hotel_alura.entities.records.RecNuevaReserva;
import ar.com.facundobazan.hotel_alura.entities.records.RecReserva;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "fecha_entrada", nullable = false)
    private LocalDate fechaEntrada;
    @Column(name = "fecha_salida", nullable = false)
    private LocalDate fechaSalida;
    private Double valor;
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne
    @JoinColumn(name = "huesped_id")
    private Huesped huesped;

    public Reserva() {

        this.huesped = new Huesped();
    }

    public Reserva(RecNuevaReserva reserva, double valor) {

        this.fechaEntrada = reserva.fechaEntrada();
        this.fechaSalida = reserva.fechaSalida();
        this.valor = valor;
        this.formaPago = reserva.formaPago();
    }

    public Reserva(RecReserva reserva) {

        this.id = reserva.id();
        this.fechaEntrada = reserva.fechaEntrada();
        this.fechaSalida = reserva.fechaSalida();
        this.valor = reserva.valor();
        this.formaPago = reserva.formaPago();
    }

    public Reserva(Long id, LocalDate fechaEntrada, LocalDate fechaSalida, Double valor, FormaPago formaPago, Huesped huesped) {

        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.valor = valor;
        this.formaPago = formaPago;
        this.huesped = huesped;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public LocalDate getFechaEntrada() {

        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {

        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {

        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {

        this.fechaSalida = fechaSalida;
    }

    public Double getValor() {

        return valor;
    }

    public void setValor(Double valor) {

        this.valor = valor;
    }

    public FormaPago getFormaPago() {

        return formaPago;
    }

    public void setFormaPago(FormaPago formaPago) {

        this.formaPago = formaPago;
    }

    public Huesped getHuesped() {

        return huesped;
    }

    public void setHuesped(Huesped huesped) {

        this.huesped = huesped;
    }
}
