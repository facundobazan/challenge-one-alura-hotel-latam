package ar.com.facundobazan.hotel_alura.entities;

import ar.com.facundobazan.hotel_alura.entities.records.RegistroUsuario;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "email", unique = true, nullable = false, length = 100)
    private String email;
    @Column(name = "clave", nullable = false, length = 100)
    private String clave;
    @Column(name = "fecha_registro", nullable = false, updatable = false)
    private LocalDate fechaRegistro;

    public Usuario() {

    }

    public Usuario(Long id, String email, String clave, LocalDate fechaRegistro) {

        this.id = id;
        this.email = email;
        this.clave = clave;
        this.fechaRegistro = fechaRegistro;
    }

    public Usuario(RegistroUsuario usuario) {

        this.email = usuario.email();
        this.clave = usuario.clave();

        this.fechaRegistro = LocalDate.now();
    }

    public String getClave() {

        return clave;
    }

    public void setClave(String clave) {

        this.clave = clave;
    }

    public Long getId() {

        return id;
    }

    public String getEmail() {

        return email;
    }
}