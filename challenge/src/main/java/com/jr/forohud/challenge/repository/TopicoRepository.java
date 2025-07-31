package com.jr.forohud.challenge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.jr.forohud.challenge.models.Topico;

public interface TopicoRepository extends JpaRepository<Topico, Long>{
    
    Boolean existsByTituloAndMensaje(String titulo, String mensaje);

}
