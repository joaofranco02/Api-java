package com.biblioteca.dto;

import com.biblioteca.model.Livro;
import lombok.*;

import java.time.LocalDateTime;

/**
 * DTO de saída para resposta da API.
 * Desacopla a entidade JPA da representação externa.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LivroResponseDTO {

    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private String editora;
    private Integer anoPublicacao;
    private Integer numeroPaginas;
    private String genero;
    private Integer quantidadeDisponivel;
    private LocalDateTime criadoEm;
    private LocalDateTime atualizadoEm;

    /**
     * Factory method para converter a entidade em DTO de resposta.
     */
    public static LivroResponseDTO fromEntity(Livro livro) {
        return LivroResponseDTO.builder()
                .id(livro.getId())
                .titulo(livro.getTitulo())
                .autor(livro.getAutor())
                .isbn(livro.getIsbn())
                .editora(livro.getEditora())
                .anoPublicacao(livro.getAnoPublicacao())
                .numeroPaginas(livro.getNumeroPaginas())
                .genero(livro.getGenero())
                .quantidadeDisponivel(livro.getQuantidadeDisponivel())
                .criadoEm(livro.getCriadoEm())
                .atualizadoEm(livro.getAtualizadoEm())
                .build();
    }
}
