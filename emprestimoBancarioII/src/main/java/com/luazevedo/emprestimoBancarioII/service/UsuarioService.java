package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.UsuarioDTO;
import com.luazevedo.emprestimoBancarioII.entity.Usuario;
import com.luazevedo.emprestimoBancarioII.exception.UsuarioNotFoundException;
import com.luazevedo.emprestimoBancarioII.mapper.UsuarioMapper;
import com.luazevedo.emprestimoBancarioII.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço para operações relacionadas a usuários.
 * Contém métodos para gerenciar operações de usuários.
 *
 * @see Usuario
 * @see UsuarioDTO
 * @see UsuarioMapper
 */
@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public Usuario salvar(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }
/**
 * Salva um novo usuário.
 *
 * @param usuarioDTO O DTO do usuáro a ser salvo.
 * @return o DTO do usuário salvo.
 */
public UsuarioDTO save(UsuarioDTO usuarioDTO){
    Usuario usuario = usuarioMapper.paraEntity(usuarioDTO);
    Usuario usuarioSalvo = usuarioRepository.save(usuario);
    return usuarioMapper.paraDTO(usuarioSalvo);
}
    /**
     * Encontra um usuário pelo ID.
     *
     * @param id O ID do usuário.
     * @return o DTO do usuário.
     * @throws UsuarioNotFoundException Se o usuario não for encontrado.
     */
    public UsuarioDTO findById(Long id){
        Usuario usuario=usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException("Usuário não encontrado com ID: " + id));
        return usuarioMapper.paraDTO(usuario);
    }
    /**
     * Encontra todos os usuários.
     *
     * @return Uma lista de DTOs de usuários.
     */
    public List<UsuarioDTO> findAll(){
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(usuarioMapper::paraDTO)
                .collect((Collectors.toList()));
    }
    /**
     * Atualiza um usuário existente.
     *
     * @param id        O ID do usuário a ser atualizado.
     * @param usuarioDTO O DTO com os novos dados.
     * @return O DTO do usuário atualizado.
     * @throws UsuarioNotFoundException Se o usuário não for encontrado.
     */
    public UsuarioDTO update(Long id, UsuarioDTO usuarioDTO){
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNotFoundException("Usuário não encontrado com ID: " + id));

        usuarioExistente.setUsername(usuarioDTO.getUsername());
        usuarioExistente.setSenha(usuarioDTO.getSenha());

        Usuario usuarioAtualizado = usuarioRepository.save(usuarioExistente);
        return usuarioMapper.paraDTO(usuarioAtualizado);

    }
/**
 * Deleta um usuário pelo ID.
 *
 * @param id O ID do usuaário a ser deletado.
 * @throws UsuarioNotFoundException Se o usuário não for encontrado.
 */
public void delete(Long id){
    if(!usuarioRepository.existsById(id)){
        throw new UsuarioNotFoundException(("Usuário não encontrado com ID: "  + id));
    }
}


}