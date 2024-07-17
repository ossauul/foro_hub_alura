package com.forohub.forohub.domain.usuario;

public record DatosUsuarioId(Long id) {
    public DatosUsuarioId(Usuario autor) {
        this(autor.getId());
    }
}
