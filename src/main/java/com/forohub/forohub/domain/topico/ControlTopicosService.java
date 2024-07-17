package com.forohub.forohub.domain.topico;

import com.forohub.forohub.domain.topico.Validaciones.ValidacionTopicos;
import com.forohub.forohub.domain.usuario.UsuarioRepository;
import com.forohub.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ControlTopicosService {
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    List<ValidacionTopicos> validadores;

    public DatosTopico controlTopico(DatosNuevoTopico datos)
    {
        validadores.forEach(v-> v.validar(datos));
        var topico =new Topico(datos);
        topicoRepository.save(topico);
        return new DatosTopico(topico);
    }

}
