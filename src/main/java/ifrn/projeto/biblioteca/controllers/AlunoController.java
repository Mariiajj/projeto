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

import ifrn.projeto.biblioteca.models.Aluno;
import ifrn.projeto.biblioteca.repositories.AlunoRepository;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoRepository al;
	
	@GetMapping("/form")
	public String form(Aluno aluno) {
		return "biblioteca/formAluno";
	}
	
	@PostMapping
	public String salvar(Aluno aluno) {
		
		System.out.println(aluno);
		al.save(aluno);
		
		return "redirect:/alunos";
		//return "biblioteca/cadastro-realizado";
	}
	
	@GetMapping
	public ModelAndView listar() {
		List<Aluno> alunos = al.findAll();
		ModelAndView mv = new ModelAndView("biblioteca/lista");
		mv.addObject("alunos", alunos);
		return mv;
	}
    
	@GetMapping("/{id}")
	public ModelAndView detalhar(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Aluno> opt = al.findById(id);

		if(opt.isEmpty()) {
			md.setViewName("redirect:/alunos");
			return md;
		}

		md.setViewName("biblioteca/detalhes");
		Aluno aluno = opt.get();
		md.addObject("aluno", aluno);

		return md;	
	}
	
	@GetMapping("/{id}/selecionar")
	public ModelAndView selecionarAluno(@PathVariable Long id) {
		ModelAndView md = new ModelAndView();
		Optional<Aluno> opt = al.findById(id);
		if(opt.isEmpty()) {
			md.setViewName("redirect:/alunos");
			return md;
		}
		
		Aluno aluno = opt.get();
		md.setViewName("biblioteca/formAluno");
		md.addObject("aluno", aluno);
		
		return md;
		
	}
	
	@GetMapping("/{id}/deletar")
	public String apagarAluno(@PathVariable Long id) {
		
		Optional<Aluno> opt = al.findById(id);
		
		if(!opt.isEmpty()) {  
			Aluno aluno = opt.get();
			al.delete(aluno);
		}
		
		return "redirect:/alunos";
	}
}

