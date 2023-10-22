package ifrn.projeto.biblioteca.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import ifrn.projeto.biblioteca.models.Emprestimo;
import ifrn.projeto.biblioteca.repositories.EmprestimoRepository;

@Controller
@RequestMapping("/emprestimos")
public class BibliotecaController {
	
	@Autowired
	private EmprestimoRepository er;
	
	@RequestMapping("/form")
	public String form() {
		return "biblioteca/formEmprestimo";
	}

	@PostMapping
	public String realizar(Emprestimo emprestimo) {
		
		System.out.println(emprestimo);
		er.save(emprestimo);
		
		return "biblioteca/emprestimo-realizado";
	}
	
	@GetMapping
	public ModelAndView listar() {
		List<Emprestimo> emprestimos = er.findAll();
		ModelAndView mv = new ModelAndView("biblioteca/lista");
		mv.addObject("emprestimos", emprestimos);
		return mv;
	}
	
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Emprestimo> opt = er.findById(id);
		
		if(opt.isEmpty()) {
			md.setViewName("redirect:/emprestimos");
			return md;
		}
		
		md.setViewName("biblioteca/detalhes");
		Emprestimo emprestimo = opt.get();
		md.addObject("emprestimo", emprestimo);
		
		return md;	
	}
}
