package com.luazevedo.emprestimoBancarioII.service;

import com.luazevedo.emprestimoBancarioII.dto.GarantiaDTO;
import com.luazevedo.emprestimoBancarioII.entity.Garantia;
import com.luazevedo.emprestimoBancarioII.mapper.GarantiaMapper;
import com.luazevedo.emprestimoBancarioII.repository.GarantiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GarantiaService {

    @Autowired
    private GarantiaRepository repository;
    @Autowired
    private GarantiaMapper mapper;

    public List<GarantiaDTO> findAll() {
        List<Garantia> garantias = repository.findAll();
        return mapper.paraDTO(garantias);
    }

    public GarantiaDTO findById(Long id) {
        Garantia garantia = repository.findById(id).orElseThrow(
                () -> new RuntimeException("Garantia com id" + id + "Não foi encontrado"));
        return mapper.paraDTO(garantia);

    }

    public Long save(GarantiaDTO garantiaDTO) {
        Garantia garantia = mapper.paraEntity(garantiaDTO);
        return repository.save(garantia).getId();
    }

    public void delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Garantia com id" + id + "Não foi encontrado");
        }
    }
    public Long update (GarantiaDTO garantiaDTO) {
        Garantia garantia = mapper.paraEntity(garantiaDTO);
        if (repository.existsById(garantia.getId())) {
            return repository.save(garantia).getId();
        } else {
            throw new RuntimeException("Garantia com id" + garantia.getId() + "Não foi encontrado");
        }
    }
}
