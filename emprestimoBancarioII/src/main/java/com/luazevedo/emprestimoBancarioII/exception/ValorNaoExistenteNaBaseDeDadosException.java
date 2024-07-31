package com.luazevedo.emprestimoBancarioII.exception;

public class ValorNaoExistenteNaBaseDeDadosException extends AbstractMinhaException {
    public ValorNaoExistenteNaBaseDeDadosException(String valor) {
        super(String.format("Valor %s não existente na Base de Dados", valor));
    }
}
