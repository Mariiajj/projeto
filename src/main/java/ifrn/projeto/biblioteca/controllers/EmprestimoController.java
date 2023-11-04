package ifrn.projeto.biblioteca.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ifrn.projeto.biblioteca.models.Aluno;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ifrn.projeto.biblioteca.models.Emprestimo;
import ifrn.projeto.biblioteca.models.Livro;
import ifrn.projeto.biblioteca.repositories.AlunoRepository;
import ifrn.projeto.biblioteca.repositories.EmprestimoRepository;
import ifrn.projeto.biblioteca.repositories.LivroRepository;

@Controller
@RequestMapping("/emprestimos")
public class EmprestimoController {
	
	@Autowired
	private EmprestimoRepository ep;
	@Autowired
	private LivroRepository lr;
	@Autowired
	private AlunoRepository al;

	@GetMapping("/form")
	public String form(Emprestimo emprestimo) {
		return "biblioteca/formEmprestimo";
	}

    @PostMapping  
	public String realizarEmprestimo(String titulo, String matricula, Emprestimo emprestimo, BindingResult result, RedirectAttributes attributes) {
    	
    	if (result.hasErrors()) {
	        return "biblioteca/formEmprestimo";
	    }
	    
	    System.out.println(titulo);
	    System.out.println(matricula);
	    
	    ep.save(emprestimo);
	    attributes.addFlashAttribute("mensagem", "Empréstimo salvo com sucesso!");
	    
	    Livro livro = emprestimo.getLivro();
	    if (livro == null) {
	    	result.rejectValue("livro.titulo", "error.titulo", "O livro não foi selecionado.");
	        return "biblioteca/formEmprestimo";
	    }

	    Livro livroIdentificado = lr.findByTitulo(emprestimo.getLivro().getTitulo());

	    if (livroIdentificado == null) {
	        result.rejectValue("livro.titulo", "error.titulo", "O cadastro do livro não foi encontrado no sistema.");
	        return "biblioteca/formEmprestimo";
	    }
	    
	    Aluno aluno = emprestimo.getAluno();
	    
	    if (aluno == null) {
	        result.rejectValue("aluno.matricula", "error.matricula", "O aluno(a) não foi selecionado.");
	        return "biblioteca/formEmprestimo";
	    }

	    Aluno alunoEncontrado = al.findByMatricula(aluno.getMatricula());

	    if (alunoEncontrado == null) {
	        result.rejectValue("aluno.matricula", "error.matricula", "O aluno(a) não possui cadastro no sistema.");
	        return "biblioteca/formEmprestimo";
	    }
	   
	    return "redirect:/emprestimos/form";  
	}  
	
        /*@GetMapping
    	public String listarEmprestimos() {
    		
    		List<Emprestimo> emprestimos = ep.findAll();
    		
    	}*/
    		
}

