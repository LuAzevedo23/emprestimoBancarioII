package com.luazevedo.emprestimoBancarioII.jsonResponse;

import com.luazevedo.emprestimoBancarioII.json.response.JsonResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Classe de teste para JsonResponse
 */
public class JsonResponseTest {

    /**
     * Testa a criação de um JsonResponse.
     * Verifica se os valores do status e da mensagem estão corretos.
     */
    @Test
    public void testJsonResponse(){
        JsonResponse response = new JsonResponse("Sucesso!", "Operação realizada com sucesso!");

        assertEquals("Sucesso!", response.getStatus());
        assertEquals("Operação realizada com sucesso!", response.getMessage());
    }
}
