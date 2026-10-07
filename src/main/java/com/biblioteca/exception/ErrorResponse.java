package com.biblioteca.exception;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * Corpo padrão de resposta para erros da API.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;
    private Map<String, String> erros;
}
