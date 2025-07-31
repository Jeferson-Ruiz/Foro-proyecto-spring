package com.jr.forohud.challenge.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jr.forohud.challenge.dto.CursoInfoDto;
import com.jr.forohud.challenge.dto.CursoResgistroDto;
import com.jr.forohud.challenge.models.Curso;
import com.jr.forohud.challenge.repository.CursoRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private CursoRepository cursoRepository;

    @Override
    @Transactional
    public CursoInfoDto crearCurso(CursoResgistroDto curso){
        Curso nuevoCurso = new Curso(curso.nombre(), curso.categoria());
        cursoRepository.save(nuevoCurso);
        return new CursoInfoDto(nuevoCurso);
    }

    @Override
    public List<CursoInfoDto> listarCursos(){
        return cursoRepository.findAll()
            .stream().map(CursoInfoDto :: new).toList();
    }

    @Override
    @Transactional
    public void eliminarCurso(Long idCurso){
        if (!cursoRepository.existsById(idCurso)) {
            throw new EntityNotFoundException("El curso con el Id: "+ idCurso+" no existe en el sistema");
        }
        cursoRepository.deleteById(idCurso);
    }

    @Override
    public CursoInfoDto buscarCursoPorId(Long id){
        Curso curso = cursoRepository.findById(id)
            .orElseThrow( () -> new EntityNotFoundException("Curso con el Id: "+ id+ " no encontrado"));
        return new CursoInfoDto(curso);
    }
}
