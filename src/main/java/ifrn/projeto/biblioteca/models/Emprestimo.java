package ifrn.projeto.biblioteca.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;

@Entity
public class Emprestimo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Livro titulo;

    @ManyToOne
    private Aluno matricula;
    private String andamento;
   
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate dataEmprestimo;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private  LocalDate dataEntrega;
   
    private Long diasD;
    
    @PrePersist
    public void prePersist() {
    this.dataEmprestimo = LocalDate.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Livro getTitulo() {
		return titulo;
	}

	public void setTitulo(Livro titulo) {
		this.titulo = titulo;
	}

	public Aluno getMatricula() {
		return matricula;
	}

	public void setMatricula(Aluno matricula) {
		this.matricula = matricula;
	}

	public String getAndamento() {
		return andamento;
	}

	public void setAndamento(String andamento) {
		this.andamento = andamento;
	}

	public LocalDate getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(LocalDate dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public LocalDate getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(LocalDate dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public Long getDiasD() {
		return diasD;
	}

	public void setDiasD(Long diasD) {
		this.diasD = diasD;
	}

	@Override
	public String toString() {
		return "Emprestimo [id=" + id + ", titulo=" + titulo + ", matricula=" + matricula + ", andamento=" + andamento
				+ ", dataEmprestimo=" + dataEmprestimo + ", dataEntrega=" + dataEntrega + ", diasD=" + diasD + "]";
	}
    
    
}