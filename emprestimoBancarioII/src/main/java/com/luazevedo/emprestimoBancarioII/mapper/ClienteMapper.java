package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.ClienteDTO;
import com.luazevedo.emprestimoBancarioII.entity.Cliente;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClienteMapper {

    @Mapping(target = "nome", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "telefone", ignore = true)
    @Mapping(target = "endereco", ignore = true)
    ClienteDTO paraDTO(Cliente cliente);


    @Mapping(target = "senha", source = "clienteDTO.senha") // Se clienteDTO tiver um campo senha
    @Mapping(target = "cpf", source = "clienteDTO.cpf") // Se clienteDTO tiver um campo cpf
    @Mapping(target = "emprestimos", source = "clienteDTO.emprestimos") // Se clienteDTO tiver um campo emprestimos
    Cliente paraEntity(ClienteDTO clienteDTO);

    List<ClienteDTO> paraDTO(List<Cliente> clientes);

    default String map(List<Emprestimo> emprestimos) {
        return emprestimos.stream()
                .map(emprestimo -> String.valueOf(emprestimo.getId())) // Ou qualquer outra propriedade
                .collect(Collectors.joining(","));
    }
}