package ifrn.projeto.biblioteca.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import ifrn.projeto.biblioteca.models.Emprestimo;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	 Optional<Emprestimo> findById(Long id);

}
