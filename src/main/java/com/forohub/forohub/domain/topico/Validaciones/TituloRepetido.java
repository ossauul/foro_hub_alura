package com.forohub.forohub.domain.topico.Validaciones;

import com.forohub.forohub.domain.topico.DatosNuevoTopico;
import com.forohub.forohub.domain.topico.DatosTopico;
import com.forohub.forohub.domain.topico.Topico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import com.forohub.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TituloRepetido implements ValidacionTopicos{

    @Autowired
    private TopicoRepository repository;
    @Override
    public void validar(DatosNuevoTopico datos) {
        var busqueda = repository.findAllByTitulo(datos.titulo());
        if(datos.titulo().equalsIgnoreCase(String.valueOf(busqueda)))
        {
                throw new ValidationException("No se permite el mismo titulo");

        }


    }
}
