package com.biblioteca.service;

import com.biblioteca.dto.LivroRequestDTO;
import com.biblioteca.dto.LivroResponseDTO;
import com.biblioteca.exception.ResourceNotFoundException;
import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Camada de serviço responsável pelas regras de negócio dos Livros.
 */
@Service
@RequiredArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    /**
     * Lista todos os livros cadastrados.
     */
    @Transactional(readOnly = true)
    public List<LivroResponseDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Busca um livro pelo ID.
     *
     * @throws ResourceNotFoundException se o livro não for encontrado
     */
    @Transactional(readOnly = true)
    public LivroResponseDTO buscarPorId(Long id) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro", id));
        return LivroResponseDTO.fromEntity(livro);
    }

    /**
     * Busca livros por título (busca parcial, case-insensitive).
     */
    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorTitulo(String titulo) {
        return livroRepository.findByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Busca livros por autor (busca parcial, case-insensitive).
     */
    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorAutor(String autor) {
        return livroRepository.findByAutorContainingIgnoreCase(autor)
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Busca livros por gênero.
     */
    @Transactional(readOnly = true)
    public List<LivroResponseDTO> buscarPorGenero(String genero) {
        return livroRepository.findByGeneroIgnoreCase(genero)
                .stream()
                .map(LivroResponseDTO::fromEntity)
                .toList();
    }

    /**
     * Cadastra um novo livro.
     */
    @Transactional
    public LivroResponseDTO criar(LivroRequestDTO dto) {
        Livro livro = Livro.builder()
                .titulo(dto.getTitulo())
                .autor(dto.getAutor())
                .isbn(dto.getIsbn())
                .editora(dto.getEditora())
                .anoPublicacao(dto.getAnoPublicacao())
                .numeroPaginas(dto.getNumeroPaginas())
                .genero(dto.getGenero())
                .quantidadeDisponivel(dto.getQuantidadeDisponivel())
                .build();

        Livro salvo = livroRepository.save(livro);
        return LivroResponseDTO.fromEntity(salvo);
    }

    /**
     * Atualiza um livro existente.
     *
     * @throws ResourceNotFoundException se o livro não for encontrado
     */
    @Transactional
    public LivroResponseDTO atualizar(Long id, LivroRequestDTO dto) {
        Livro livro = livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro", id));

        livro.setTitulo(dto.getTitulo());
        livro.setAutor(dto.getAutor());
        livro.setIsbn(dto.getIsbn());
        livro.setEditora(dto.getEditora());
        livro.setAnoPublicacao(dto.getAnoPublicacao());
        livro.setNumeroPaginas(dto.getNumeroPaginas());
        livro.setGenero(dto.getGenero());
        livro.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());

        Livro atualizado = livroRepository.save(livro);
        return LivroResponseDTO.fromEntity(atualizado);
    }

    /**
     * Remove um livro pelo ID.
     *
     * @throws ResourceNotFoundException se o livro não for encontrado
     */
    @Transactional
    public void deletar(Long id) {
        if (!livroRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livro", id);
        }
        livroRepository.deleteById(id);
    }
}
