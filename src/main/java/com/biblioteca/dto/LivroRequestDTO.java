package com.biblioteca.dto;

import jakarta.validation.constraints.*;
import lombok.*;

/**
 * DTO de entrada para criação/atualização de um Livro.
 * Contém validações de Bean Validation.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroRequestDTO {

    @NotBlank(message = "O título é obrigatório")
    @Size(max = 200, message = "O título deve ter no máximo 200 caracteres")
    private String titulo;

    @NotBlank(message = "O autor é obrigatório")
    @Size(max = 150, message = "O autor deve ter no máximo 150 caracteres")
    private String autor;

    @Size(max = 17, message = "O ISBN deve ter no máximo 17 caracteres")
    private String isbn;

    @Size(max = 100, message = "A editora deve ter no máximo 100 caracteres")
    private String editora;

    @Min(value = 1450, message = "O ano de publicação deve ser a partir de 1450")
    @Max(value = 2100, message = "O ano de publicação não pode ser superior a 2100")
    private Integer anoPublicacao;

    @Positive(message = "O número de páginas deve ser positivo")
    private Integer numeroPaginas;

    @Size(max = 50, message = "O gênero deve ter no máximo 50 caracteres")
    private String genero;

    @PositiveOrZero(message = "A quantidade disponível não pode ser negativa")
    private Integer quantidadeDisponivel;
}
