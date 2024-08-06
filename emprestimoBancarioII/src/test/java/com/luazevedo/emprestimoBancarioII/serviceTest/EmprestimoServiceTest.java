package com.luazevedo.emprestimoBancarioII.serviceTest;

import com.luazevedo.emprestimoBancarioII.entity.Emprestimo;
import com.luazevedo.emprestimoBancarioII.exception.ValorNaoExistenteNaBaseDeDadosException;
import com.luazevedo.emprestimoBancarioII.repository.EmprestimoRepository;
import com.luazevedo.emprestimoBancarioII.service.EmprestimoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EmprestimoServiceTest {

    @Mock
    private EmprestimoRepository repository;

    @InjectMocks
    private EmprestimoService emprestimoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveEmprestimo() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setValor(BigDecimal.valueOf(1000));
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataTermino(LocalDate.now().plusMonths(6));
        emprestimo.setTaxaJuros(BigDecimal.valueOf(0.05));

        when(repository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        Emprestimo savedEmprestimo = emprestimoService.save(emprestimo);

        assertNotNull(savedEmprestimo);
        assertEquals(emprestimo.getValor(), savedEmprestimo.getValor());
    }

    @Test
    public void testDelete_ValidId() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        emprestimoService.delete(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    public void testDelete_InvalidId() {
        when(repository.existsById(1L)).thenReturn(false);

        assertThrows(ValorNaoExistenteNaBaseDeDadosException.class, () -> {
            emprestimoService.delete(1L);
        });
    }

    @Test
    public void testFindById_ValidId() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.of(emprestimo));

        Emprestimo foundEmprestimo = emprestimoService.findById(1L);

        assertNotNull(foundEmprestimo);
        assertEquals(emprestimo.getId(), foundEmprestimo.getId());
    }

    @Test
    public void testFindById_InvalidId() {
        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValorNaoExistenteNaBaseDeDadosException.class, () -> {
            emprestimoService.findById(1L);
        });
    }

    @Test
    public void testUpdate_ValidId() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);
        emprestimo.setValor(BigDecimal.valueOf(1000));

        when(repository.findById(1L)).thenReturn(Optional.of(emprestimo));
        when(repository.save(any(Emprestimo.class))).thenReturn(emprestimo);

        emprestimo.setValor(BigDecimal.valueOf(1200));
        Long updatedId = emprestimoService.update(emprestimo);

        assertNotNull(updatedId);
        assertEquals(emprestimo.getId(), updatedId);
    }

    @Test
    public void testUpdate_InvalidId() {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(1L);

        when(repository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ValorNaoExistenteNaBaseDeDadosException.class, () -> {
            emprestimoService.update(emprestimo);
        });
    }
}