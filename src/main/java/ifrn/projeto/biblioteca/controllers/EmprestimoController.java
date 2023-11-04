package ifrn.projeto.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ifrn.projeto.biblioteca.models.Aluno;

import org.springframework.web.servlet.ModelAndView;
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
	
	    attributes.addFlashAttribute("mensagem", "Empréstimo salvo com sucesso!");
	    
	    Livro livro = lr.findByTitulo(titulo);
	    if (livro == null) {
	    	result.rejectValue("livro.titulo", "error.titulo", "O livro não foi selecionado.");
	        return "biblioteca/formEmprestimo";
	    }

	    //Livro livroIdentificado = lr.findByTitulo(emprestimo.getLivro().getTitulo());

	    //if (livroIdentificado == null) {
	        //result.rejectValue("livro.titulo", "error.titulo", "O cadastro do livro não foi encontrado no sistema.");
	        //return "biblioteca/formEmprestimo";
	    //}
	    
	    Aluno aluno = al.findByMatricula(matricula);
	    System.out.println(aluno);
	    
	    if (aluno == null) {
	        result.rejectValue("aluno.matricula", "error.matricula", "O aluno(a) não foi selecionado.");
	        return "biblioteca/formEmprestimo";
	    }
	    
	    long emprestimosEfetuados = ep.countByAluno(aluno);
	    if (emprestimosEfetuados >= 3) {
	        result.rejectValue("aluno.matricula", "error.matricula", "O aluno excedeu o limite de empréstimos de 3 livros.");
	        return "biblioteca/formEmprestimo";
	    }
	    
	    Emprestimo emp = new Emprestimo();
	    emp.setLivro(livro);
	    emp.setAluno(aluno);
	    ep.save(emp);
	   
	    return "redirect:/emprestimos/form";  
	}  
	
    @GetMapping
	public ModelAndView listar() {
		List<Emprestimo> emprestimos = ep.findAll();
		ModelAndView mv = new ModelAndView("biblioteca/listaEmprestimo");
		mv.addObject("emprestimos", emprestimos);
		return mv;
	}	
    
    @GetMapping("/{id}/remover")
	public String apagarEmprestimo(@PathVariable Long id) {
		
		Optional<Emprestimo> opt = ep.findById(id);
		
		if(!opt.isEmpty()) {  
			Emprestimo emprestimo = opt.get();
			ep.delete(emprestimo);
		}
		
		return "redirect:/emprestimos";
	}
}

