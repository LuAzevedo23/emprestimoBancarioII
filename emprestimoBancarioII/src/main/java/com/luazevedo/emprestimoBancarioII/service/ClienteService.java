package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.ClienteDTO;
import com.luazevedo.emprestimoBancarioII.entity.Cliente;
import com.luazevedo.emprestimoBancarioII.mapper.ClienteMapper;
import com.luazevedo.emprestimoBancarioII.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para manipulação das entidades {@link Cliente}.
 * Contém operações de CRUD e mapeamento de DTOs para a entidade cliente.
 *
 * @see ClienteDTO
 * @see Cliente
 * @see ClienteMapper
 * @see ClienteRepository
 */

@Service
public class ClienteService {

    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    @Autowired
    public ClienteService(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Encontra todos os {@link Cliente} e os converte para DTOs.
     *
     * @return uma lista de {@link ClienteDTO}
     */

    public List<ClienteDTO> findAll() {
        List<Cliente> clientes = repository.findAll();
        return mapper.paraDTO(clientes);
    }

    /**
     * Encontra um {@link Cliente} pelo ID e o converte para DTO.
     *
     * @param id o ID do Cliente
     * @return o {@link ClienteDTO} correspondente
     * @throws RuntimeException se o Cliente não for encontrado
     */

    public ClienteDTO findById(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com id " + id + " não foi encontrado"));
        return mapper.paraDTO(cliente);
    }

    /**
     * Salva um novo {@link Cliente} a partir de um DTO.
     *
     * @param clienteDTO o DTO do Cliente a ser salvo
     * @return o ID do Cliente salvo
     */
    public Long save(ClienteDTO clienteDTO) {
        Cliente cliente = mapper.paraEntity(clienteDTO);
        Cliente clienteSalvo = repository.save(cliente);
        return clienteSalvo.getId();
    }

    /**
     * Deleta um {@link Cliente} pelo ID.
     *
     * @param id o ID do Cliente
     * @throws RuntimeException se o Cliente não for encontrado
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Cliente com id " + id + " não foi encontrado");
        }
    }

    /**
     * Atualiza um {@link Cliente} existente a partir de um DTO.
     *
     * @param clienteDTO o DTO do Cliente a ser atualizado
     * @return o ID do Cliente atualizado
     * @throws RuntimeException se o Cliente não for encontrado
     */
    public ClienteDTO update(Long id, ClienteDTO clienteDTO) {
        Cliente clienteExistente = repository.findById(id)
                .orElseThrow(()-> new RuntimeException("Cliente com id " + id + " não foi encontrado"));

        clienteExistente.setNome((clienteDTO.getNome()));
        clienteExistente.setEmail((clienteDTO.getEmail()));

        Cliente clienteAtualizado = repository.save(clienteExistente);
        return mapper.paraDTO((clienteAtualizado));

    }
}