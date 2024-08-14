package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.mapper.UsuarioMapper;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Serviço para manipulação das entidades {@link Usuario}.
 */
@Service
public class UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    @Autowired
    public UsuarioService(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    /**
     * Encontra todos os {@link Usuario} e os converte para DTOs.
     *
     * @return uma lista de {@link UsuarioDTO}
     */
    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return mapper.paraDTO(usuarios);
    }

    /**
     * Encontra um {@link Usuario} pelo ID e o converte para DTO.
     *
     * @param id o ID do Usuario
     * @return o {@link UsuarioDTO} correspondente
     * @throws RuntimeException se o Usuario não for encontrado
     */
    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario com id " + id + " não foi encontrado"));
        return mapper.paraDTO(usuario);
    }

    /**
     * Salva um novo {@link Usuario} a partir de um DTO.
     *
     * @param usuarioDTO o DTO do Usuario a ser salvo
     * @return o ID do Usuario salvo
     */
    public Long save(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.paraEntity(usuarioDTO);
        return repository.save(usuario).getId();
    }

    /**
     * Deleta um {@link Usuario} pelo ID.
     *
     * @param id o ID do Usuario
     * @throws RuntimeException se o Usuario não for encontrado
     */
    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario com id " + id + " não foi encontrado");
        }
    }

    /**
     * Atualiza um {@link Usuario} existente a partir de um DTO.
     *
     * @param usuarioDTO o DTO do Usuario a ser atualizado
     * @return o ID do Usuario atualizado
     * @throws RuntimeException se o Usuario não for encontrado
     */
    public Long update(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapper.paraEntity(usuarioDTO);
        if (repository.existsById(usuario.getId())) {
            return repository.save(usuario).getId();
        } else {
            throw new RuntimeException("Usuario com id " + usuario.getId() + " não foi encontrado");
        }
    }
}