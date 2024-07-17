package com.forohub.forohub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {


    Page<Topico> findAllByStatusTrue(Pageable paginacion);

    Optional<DatosTopico> findAllByTitulo(String titulo);

    Optional<DatosTopico> findAllByMensaje(String mensaje);
}
