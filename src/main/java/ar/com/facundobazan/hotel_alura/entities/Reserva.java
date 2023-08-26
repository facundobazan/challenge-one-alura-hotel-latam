package ar.com.facundobazan.hotel_alura.entities;

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
    private FormaPago formaPago;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapKeyEnumerated(EnumType.STRING)
    private Huesped huesped;

    public Reserva() {

        this.huesped = new Huesped();
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
