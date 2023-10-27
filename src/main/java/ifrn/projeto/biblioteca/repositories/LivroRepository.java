package ifrn.projeto.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.biblioteca.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	Livro findByTitulo(String titulo);

}
