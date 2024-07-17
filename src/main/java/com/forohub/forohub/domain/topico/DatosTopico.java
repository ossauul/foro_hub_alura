package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.Curso;
import com.forohub.forohub.domain.usuario.DatosUsuario;
import com.forohub.forohub.domain.usuario.DatosUsuarioId;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosTopico(Long id, String titulo, String mensaje, LocalDateTime fechaCreacion, Usuario autor, Curso curso ) {
    /*public DatosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), new DatosUsuario(topico.getAutor()), topico.getCurso());*/
    public DatosTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor(), topico.getCurso());
    }
}
