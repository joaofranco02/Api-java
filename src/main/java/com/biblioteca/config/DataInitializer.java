package com.biblioteca.config;

import com.biblioteca.model.Livro;
import com.biblioteca.repository.LivroRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuração de dados iniciais para popular o banco H2 ao iniciar a aplicação.
 * Facilita testes manuais sem precisar inserir dados via API.
 */
@Configuration
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(LivroRepository repository) {
        return args -> {
            log.info("Populando banco de dados com livros iniciais...");

            repository.save(Livro.builder()
                    .titulo("Dom Casmurro")
                    .autor("Machado de Assis")
                    .isbn("978-85-359-0277-1")
                    .editora("Editora Ática")
                    .anoPublicacao(1899)
                    .numeroPaginas(256)
                    .genero("Romance")
                    .quantidadeDisponivel(5)
                    .build());

            repository.save(Livro.builder()
                    .titulo("Grande Sertão: Veredas")
                    .autor("Guimarães Rosa")
                    .isbn("978-85-209-2685-1")
                    .editora("Nova Fronteira")
                    .anoPublicacao(1956)
                    .numeroPaginas(624)
                    .genero("Romance")
                    .quantidadeDisponivel(3)
                    .build());

            repository.save(Livro.builder()
                    .titulo("Clean Code")
                    .autor("Robert C. Martin")
                    .isbn("978-0-13-235088-4")
                    .editora("Prentice Hall")
                    .anoPublicacao(2008)
                    .numeroPaginas(464)
                    .genero("Tecnologia")
                    .quantidadeDisponivel(7)
                    .build());

            repository.save(Livro.builder()
                    .titulo("O Alquimista")
                    .autor("Paulo Coelho")
                    .isbn("978-85-325-0249-3")
                    .editora("Rocco")
                    .anoPublicacao(1988)
                    .numeroPaginas(208)
                    .genero("Ficção")
                    .quantidadeDisponivel(10)
                    .build());

            repository.save(Livro.builder()
                    .titulo("Effective Java")
                    .autor("Joshua Bloch")
                    .isbn("978-0-13-468599-1")
                    .editora("Addison-Wesley")
                    .anoPublicacao(2018)
                    .numeroPaginas(416)
                    .genero("Tecnologia")
                    .quantidadeDisponivel(4)
                    .build());

            log.info("{} livros inseridos com sucesso!", repository.count());
        };
    }
}
