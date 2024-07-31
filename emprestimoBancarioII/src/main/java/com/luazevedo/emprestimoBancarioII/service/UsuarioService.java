package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.mapper.UsuarioMapper;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return mapper.paraDTO(usuarios);
    }

    public UsuarioDTO findById(Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario com id" + id + "Não foi encontrado"));
        return mapper.paraDTO(usuario);

    }

    public Long save(UsuarioDTO usuarioDTO) {
        Usuario usuarioService = (Usuario) mapper.paraEntity(usuarioDTO);
        return repository.save(usuarioService).getId();
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Usuario com id" + id + "Não foi encontrado");
        }
    }

    public Long update(UsuarioDTO usuarioDTO) {
        Usuario usuarioService = (Usuario) mapper.paraEntity(usuarioDTO);
        if (repository.existsById(usuarioService.getId())) {
            return repository.save(usuarioService).getId();
        } else {
            throw new RuntimeException("Usuario com id" + usuarioService.getId() + "Não foi encontrado");
        }
    }
}
