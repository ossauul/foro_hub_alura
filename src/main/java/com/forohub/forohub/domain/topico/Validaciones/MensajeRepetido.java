package com.forohub.forohub.domain.topico.Validaciones;

import com.forohub.forohub.domain.topico.DatosNuevoTopico;
import com.forohub.forohub.domain.topico.DatosTopico;
import com.forohub.forohub.domain.topico.TopicoRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MensajeRepetido implements ValidacionTopicos{

    @Autowired
    private TopicoRepository repository;
    @Override
    public void validar(DatosNuevoTopico datos) {
        var busqueda = repository.findAllByMensaje(datos.mensaje());
        if(datos.titulo().equalsIgnoreCase(String.valueOf(busqueda)))
        {
                throw new ValidationException("No se permite el mismo mensaje");

        }


    }
}
