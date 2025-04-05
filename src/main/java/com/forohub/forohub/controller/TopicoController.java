package com.forohub.forohub.controller;


import com.forohub.forohub.domain.topico.*;
import com.forohub.forohub.domain.usuario.DatosUsuario;
import com.forohub.forohub.domain.usuario.Usuario;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    ControlTopicosService service;

    @PostMapping
    @Transactional
        public ResponseEntity<DatosTopico> nuevoTopico(@RequestBody @Valid DatosNuevoTopico datos, UriComponentsBuilder uriComponentsBuilder)
        {
           // Topico topico = new topicoRepository.save(new Topico(datos));
            //DatosTopico datosTopico = new DatosTopico(topico.getId(), topico.getTitulo(), topico.getMensaje(), topico.getFechaCreacion(), topico.getAutor(), topico.getCurso());
            /*var topico =new Topico(datos);
            topicoRepository.save(topico);
            URI url =uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
            return ResponseEntity.created(url).body(new DatosTopico(topico));*/
            var response = service.controlTopico(datos);
            return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<Page<DatosTopico>> listar(@PageableDefault(size = 10) Pageable paginacion)
    {
        var page = topicoRepository.findAllByStatusTrue(paginacion).map(DatosTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalle(@PathVariable Long id)
    {
        var topico = topicoRepository.getReferenceById(id);
        return ResponseEntity.ok(new DatosTopico(topico));

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity eliminarTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.getReferenceById(id);
        topico.desactivarTopico();
        return ResponseEntity.noContent().build();
    }



}
