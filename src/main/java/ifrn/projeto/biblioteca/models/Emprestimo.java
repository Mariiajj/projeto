package ifrn.projeto.biblioteca.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Emprestimo {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String aluno;
	    private String livro;
	    private String dataEmprestimo;
	    private String dataEntrega;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getAluno() {
			return aluno;
		}
		public void setAluno(String aluno) {
			this.aluno = aluno;
		}
		public String getLivro() {
			return livro;
		}
		public void setLivro(String livro) {
			this.livro = livro;
		}
		public String getDataEmprestimo() {
			return dataEmprestimo;
		}
		public void setDataEmprestimo(String dataEmprestimo) {
			this.dataEmprestimo = dataEmprestimo;
		}
		public String getDataEntrega() {
			return dataEntrega;
		}
		public void setDataEntrega(String dataEntrega) {
			this.dataEntrega = dataEntrega;
		}
		
		@Override
		public String toString() {
			return "Emprestimo [id=" + id + ", aluno=" + aluno + ", livro=" + livro + ", dataEmprestimo="
					+ dataEmprestimo + ", dataEntrega=" + dataEntrega + "]";
		}
		

}
