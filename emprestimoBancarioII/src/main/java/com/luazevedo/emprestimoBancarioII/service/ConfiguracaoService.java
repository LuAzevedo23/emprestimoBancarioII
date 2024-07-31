package com.luazevedo.emprestimoBancarioII.service;


import com.luazevedo.emprestimoBancarioII.dto.ConfiguracaoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Configuracao;
import com.luazevedo.emprestimoBancarioII.mapper.ConfiguracaoMapper;
import com.luazevedo.emprestimoBancarioII.repository.ConfiguracaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracaoService {

        @Autowired
        private ConfiguracaoRepository repository;
        @Autowired
        private ConfiguracaoMapper mapper;


        public List<ConfiguracaoDTO> findAll() {
            List<Configuracao> configuracoes = repository.findAll();
            return mapper.paraDTO(configuracoes);
        }

        public ConfiguracaoDTO findById(Long id) {
            Configuracao configuracao = repository.findById(id).orElseThrow(
                    () -> new RuntimeException("Configuracao com id" + id + "Não foi encontrado"));
            return mapper.paraDTO(configuracao);

        }

        public Long save(ConfiguracaoDTO configuracaoDTO) {
            Configuracao configuracao = mapper.paraEntity(configuracaoDTO);
            return repository.save(configuracao).getId();
        }

        public void delete(Long id) {
            if (repository.existsById(id)) {
                repository.deleteById(id);
            } else {
                throw new RuntimeException("Configuracao com id" + id + "Não foi encontrado");
            }
        }
        public Long update (ConfiguracaoDTO configuracaoDTO) {
            Configuracao configuracao = mapper.paraEntity(configuracaoDTO);
            if (repository.existsById(configuracao.getId())) {
                return repository.save(configuracao).getId();
            } else {
                throw new RuntimeException("Configuracao com id" + configuracao.getId() + "Não foi encontrado");
            }
        }
    }

