package com.jr.forohud.challenge.service;

import java.util.List;
import com.jr.forohud.challenge.dto.CursoInfoDto;
import com.jr.forohud.challenge.dto.CursoResgistroDto;

public interface ICursoService {
    CursoInfoDto crearCurso(CursoResgistroDto curso);

    List<CursoInfoDto> listarCursos();

    void eliminarCurso(Long idCurso);
    
    CursoInfoDto buscarCursoPorId(Long id);

}
