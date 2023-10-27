package ifrn.projeto.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import ifrn.projeto.biblioteca.models.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	Aluno findByMatricula(String matricula);

}
