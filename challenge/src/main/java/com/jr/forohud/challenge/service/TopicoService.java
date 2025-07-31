package com.jr.forohud.challenge.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.jr.forohud.challenge.dto.ActualizarTopicoDto;
import com.jr.forohud.challenge.dto.TopicoInfoDto;
import com.jr.forohud.challenge.dto.TopicoRegistroDto;
import com.jr.forohud.challenge.models.Curso;
import com.jr.forohud.challenge.models.Topico;
import com.jr.forohud.challenge.models.Usuario;
import com.jr.forohud.challenge.repository.CursoRepository;
import com.jr.forohud.challenge.repository.TopicoRepository;
import com.jr.forohud.challenge.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class TopicoService implements ITopicoService {

    private final TopicoRepository topicoRepository;
    private final CursoRepository cursoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoService(TopicoRepository topicoRepository, CursoRepository cursoRepository, UsuarioRepository usuarioRepository){
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    @Transactional
    public TopicoInfoDto crearTopico(TopicoRegistroDto registroDto){
        Usuario usuario = usuarioRepository.findById(registroDto.getAutorId())
            .orElseThrow(() -> new EntityNotFoundException("Autor no registrado"));

        Curso curso = cursoRepository.findById(registroDto.getCursoId())
            .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));
    
        String titulo = registroDto.getTitulo().toLowerCase().trim();
        String mensaje = registroDto.getMensaje().toLowerCase().trim();

        Boolean validarTopico = topicoRepository.existsByTituloAndMensaje(titulo, mensaje);

        if (validarTopico) {
            throw new IllegalArgumentException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico nuevoTopico = new Topico(
            registroDto.getTitulo(),
            registroDto.getMensaje(),
            usuario,
            curso
        );
        Topico topicoGuardado = topicoRepository.save(nuevoTopico);
        return new TopicoInfoDto(topicoGuardado);
    }

    @Override
    public List<TopicoInfoDto> listarTopicos(){
        return topicoRepository.findAll()
            .stream()
            .map(TopicoInfoDto::new)
            .collect(Collectors.toList());
    }

    @Override
    public TopicoInfoDto buscarTopicoPorId(Long id) {
    Topico topico = topicoRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado con ID: " + id));

    return new TopicoInfoDto(topico);
    }


    @Override
    @Transactional
    public void eliminarTopico(Long id){
        if (!topicoRepository.existsById(id)) {
            throw new EntityNotFoundException("No existe el Id: "+ id);
        }
        topicoRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void actualizarTopico(Long id ,ActualizarTopicoDto datosActualizados){

        Optional<Topico> topicoBuscado = topicoRepository.findById(id);
        if (topicoBuscado.isPresent()) {

            Topico topicoEncontrado = topicoBuscado.get();
            
            if(datosActualizados.getTitulo() != null && !datosActualizados.getTitulo().isBlank()){
                topicoEncontrado.setTitulo(datosActualizados.getTitulo());
            }
    
            if (datosActualizados.getMensaje() != null && !datosActualizados.getMensaje().isBlank()) {
                topicoEncontrado.setMensaje(datosActualizados.getMensaje());
            }
        }else{
            throw new EntityNotFoundException("El tópico con el ID: " + id + " no existe");
        }
    }
}