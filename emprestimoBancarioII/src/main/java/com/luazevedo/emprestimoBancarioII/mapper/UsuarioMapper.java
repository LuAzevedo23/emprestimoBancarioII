package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioCreateDTO;
import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper responsável pela conversão entre a entidade Usuário e o DTO UsuarioDTO.
 */

@Mapper(componentModel = "spring", uses = {RoleMapper.class})
public interface UsuarioMapper {

    // Mapeia de Usuario para UsuarioDTO
    @Mapping(target = "username", source = "username")
    UsuarioDTO paraDTO(Usuario usuario);

    //Mapeia lista de Usuario para listsa de UsuarioDTO
    List<UsuarioDTO> paraDTO(List<Usuario> usuarios);

    /**
     * Converte um UsuarioDTO para uma entidade Usuario.
     *
     * @param usuarioDTO O DTO a ser convertido.
     * @return A entidade Usuario correspondente.
     */
    @Mapping(target = "roles", source = "roles")
    Usuario paraEntity(UsuarioDTO usuarioDTO);

    /**
     * Converte um UsuarioCreateDTO para uma entidade Usuario.
     *
     * @param usuarioCreateDTO O DTO a ser convertido.
     * @return A entidade Usuario correspondente.
     */
    @Mapping(target = "id", ignore = true) // Ignora o ID, pois não deve ser enviado na criação
    @Mapping(target = "roles", source = "roles")
    Usuario paraEntity(UsuarioCreateDTO usuarioCreateDTO);
}





