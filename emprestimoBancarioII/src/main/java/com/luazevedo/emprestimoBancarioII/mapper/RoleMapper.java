package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.RoleDTO;
import com.luazevedo.emprestimoBancarioII.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "usuarios", ignore = true)
    Role paraEntity(RoleDTO roleDTO);


    RoleDTO paraDTO(Role role);

    List<RoleDTO> paraDTO(List<Role> roles);
}



