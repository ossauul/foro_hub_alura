package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.Curso;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of ="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaCreacion;
    private Boolean status;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "autor")
    private Usuario autor;
    @Enumerated(EnumType.STRING)
    private Curso curso;

    public Topico(DatosNuevoTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = datos.fecha();
        this.status = true;
        this.autor = datos.autor();
        this.curso = datos.curso();
    }

    public void desactivarTopico() {
        this.status = false;
    }

    public void actualizarDatos(DatosActualizarTopico datosActualizar) {
        if (datosActualizar.titulo() != null) {
            this.titulo = datosActualizar.titulo();
        }
        if (datosActualizar.mensaje()!= null) {
            this.mensaje = datosActualizar.mensaje();
        }

    }

}
