package com.forohub.forohub.domain.usuario;

public record DatosUsuario(String nombre, String correoElectronico, String perfil) {
    public DatosUsuario(Usuario autor) {
        this(autor.getNombre(), autor.getCorreoElectronico(), autor.getPerfil());
    }
}
