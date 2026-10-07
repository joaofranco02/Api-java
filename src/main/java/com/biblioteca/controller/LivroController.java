package com.biblioteca.controller;

import com.biblioteca.dto.LivroRequestDTO;
import com.biblioteca.dto.LivroResponseDTO;
import com.biblioteca.service.LivroService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller REST para operações CRUD de Livros.
 *
 * Endpoints:
 *   GET    /api/livros              - Lista todos os livros
 *   GET    /api/livros/{id}         - Busca livro por ID
 *   GET    /api/livros/busca/titulo?q=...  - Busca por título
 *   GET    /api/livros/busca/autor?q=...   - Busca por autor
 *   GET    /api/livros/busca/genero?q=...  - Busca por gênero
 *   POST   /api/livros              - Cria um novo livro
 *   PUT    /api/livros/{id}         - Atualiza um livro
 *   DELETE /api/livros/{id}         - Remove um livro
 */
@RestController
@RequestMapping("/api/livros")
@RequiredArgsConstructor
public class LivroController {

    private final LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroResponseDTO>> listarTodos() {
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @GetMapping("/busca/titulo")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorTitulo(@RequestParam("q") String titulo) {
        return ResponseEntity.ok(livroService.buscarPorTitulo(titulo));
    }

    @GetMapping("/busca/autor")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorAutor(@RequestParam("q") String autor) {
        return ResponseEntity.ok(livroService.buscarPorAutor(autor));
    }

    @GetMapping("/busca/genero")
    public ResponseEntity<List<LivroResponseDTO>> buscarPorGenero(@RequestParam("q") String genero) {
        return ResponseEntity.ok(livroService.buscarPorGenero(genero));
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> criar(@Valid @RequestBody LivroRequestDTO dto) {
        LivroResponseDTO criado = livroService.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(criado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroResponseDTO> atualizar(
            @PathVariable Long id,
            @Valid @RequestBody LivroRequestDTO dto) {
        return ResponseEntity.ok(livroService.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        livroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
