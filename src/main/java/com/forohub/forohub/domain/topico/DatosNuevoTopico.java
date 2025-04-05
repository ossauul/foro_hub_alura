package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.Curso;
import com.forohub.forohub.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosNuevoTopico(@NotBlank String titulo,
                               @NotBlank String mensaje, @NotNull LocalDateTime fecha, @NotNull Usuario autor ,
                               @NotNull Curso curso ) {

}
