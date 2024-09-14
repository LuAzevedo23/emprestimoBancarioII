package com.luazevedo.emprestimoBancarioII.mapper;

import com.luazevedo.emprestimoBancarioII.dto.ClienteDTO;
import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Cliente;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmprestimoMapper {

 /**
  * Mapeia de Emprestimo para EmprestimoDTO.
  * @param emprestimo A entidade a ser convertida.
  * @return O DTO convertido.
  */
 @Mapping(target = "valor", ignore = true)
 @Mapping(target = "taxaJuros", ignore = true)
 @Mapping(target = "prazoMeses", ignore = true)
 @Mapping(target = "numeroParcelas", ignore = true)
 @Mapping(target = "parcela", ignore = true)
 @Mapping(target = "dataEmprestimo", ignore = true)
 @Mapping(target = "dataTermino", ignore = true)
 @Mapping(target = "valorTotal", ignore = true)
 EmprestimoDTO paraDTO(Emprestimo emprestimo);

 /**
  * Atualiza a entidade Emprestimo com os dados do DTO.
  * @param emprestimoDTO O DTO com os novos dados.
  * @param emprestimo A entidade a ser atualizada.
  *
  */
 /**
  * Mapeia de EmprestimoDTO para Emprestimo.
  * @param emprestimoDTO O DTO a ser convertido.
  * @return O objeto Emprestimo convertido.
  */
 @Mapping(target = "cliente", source = "cliente") // Mapeia o cliente associado
 Emprestimo paraEntity(EmprestimoDTO emprestimoDTO);
 void atualizarEntity(EmprestimoDTO emprestimoDTO, @MappingTarget Emprestimo emprestimo);

 /**
  * Mapeia de ClienteDTO para Cliente.
  * @param clienteDTO O DTO do cliente a ser convertido.
  * @return A entidade Cliente convertida.
  */
 Cliente toEntity(ClienteDTO clienteDTO);
}