package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioDTO paraDTO(Usuario usuario);

    Usuario paraEntity(UsuarioDTO usuarioDTO);

    List<UsuarioDTO> paraDTO(List<Usuario> usuarios);

    List<Usuario> paraEntity(List<UsuarioDTO> UsuarioDTO);
}


