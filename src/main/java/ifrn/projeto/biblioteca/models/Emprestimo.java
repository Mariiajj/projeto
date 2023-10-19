package ifrn.projeto.biblioteca.models;

public class Emprestimo {
	
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

}
