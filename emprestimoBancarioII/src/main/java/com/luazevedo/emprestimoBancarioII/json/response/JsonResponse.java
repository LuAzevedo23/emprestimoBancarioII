package com.luazevedo.emprestimoBancarioII.json.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JsonResponse {
    private final String status;
    private final String message;
}
