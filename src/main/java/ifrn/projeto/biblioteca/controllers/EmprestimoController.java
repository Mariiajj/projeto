package ifrn.projeto.biblioteca.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ifrn.projeto.biblioteca.models.Aluno;
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
	public String realizarEmprestimo(@Validated Emprestimo emprestimo, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			return "biblioteca/formEmprestimo";
		}
        
		Aluno aluno = al.findByMatricula(emprestimo.getMatricula().getMatricula());
	    Livro livro = lr.findByTitulo(emprestimo.getTitulo().getTitulo());
	    if (livro == null) {
	        result.rejectValue("titulo.titulo", "error.titulo", "Erro: Livro não encontrado");
	        return form(emprestimo);
	    }
	    
		return "redirect:/emprestimos/form";
	}
	
    
	@GetMapping
	public ModelAndView listarEmprestimos() {
		List<Emprestimo> emprestimos = ep.findAll();
		ModelAndView mv = new ModelAndView("biblioteca/listaEmprestimo");
		mv.addObject("emprestimos", emprestimos);
		return mv;
	}

}
