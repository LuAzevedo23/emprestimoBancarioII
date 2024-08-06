package com.luazevedo.emprestimoBancarioII.controller;

import com.luazevedo.emprestimoBancarioII.dto.EmprestimoDTO;
import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {

    @Autowired
    private EmprestimoService emprestimoService;

    @GetMapping("/{id}")
    public ResponseEntity<EmprestimoDTO> getEmprestimoById(@PathVariable Long id) {
        Emprestimo emprestimo = emprestimoService.findById(id);
        EmprestimoDTO emprestimoDTO = convertToDTO(emprestimo); // Conversão para DTO
        return ResponseEntity.ok(emprestimoDTO);
    }

    // Método para converter Emprestimo para EmprestimoDTO
    private EmprestimoDTO convertToDTO(Emprestimo emprestimo) {
        EmprestimoDTO dto = new EmprestimoDTO();
        dto.setId(emprestimo.getId());
        dto.setValor(emprestimo.getValor());
        dto.setDataEmprestimo(emprestimo.getDataEmprestimo());
        dto.setDataTermino(emprestimo.getDataTermino());
        dto.setTaxaJuros(emprestimo.getTaxaJuros());
        return dto;
    }
}