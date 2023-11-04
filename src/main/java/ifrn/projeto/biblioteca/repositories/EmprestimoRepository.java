package ifrn.projeto.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.biblioteca.models.Aluno;
import ifrn.projeto.biblioteca.models.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{

	long countByAluno(Aluno aluno);

}
