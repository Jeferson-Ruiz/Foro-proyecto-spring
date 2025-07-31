package com.jr.forohud.challenge.service;

import java.util.List;
import com.jr.forohud.challenge.dto.ActualizarTopicoDto;
import com.jr.forohud.challenge.dto.TopicoInfoDto;
import com.jr.forohud.challenge.dto.TopicoRegistroDto;

public interface ITopicoService {
     
     TopicoInfoDto crearTopico (TopicoRegistroDto registroDto);

     List<TopicoInfoDto> listarTopicos();

     public TopicoInfoDto buscarTopicoPorId(Long id);

     void eliminarTopico(Long id);

     void actualizarTopico(Long id ,ActualizarTopicoDto datosActualizados);

}
