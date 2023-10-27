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

import ifrn.projeto.biblioteca.models.Livro;
import ifrn.projeto.biblioteca.repositories.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
	
	@Autowired
	private LivroRepository lr;
		
		@GetMapping("/form")
		public String form(Livro Livro) {
			return "biblioteca/formLivro";
		}

		@PostMapping
		public String salvar(Livro livro) {
			
			System.out.println(livro);
			lr.save(livro);
			
			return "redirect:/livros";
			//return "biblioteca/cadastro-realizado";
		}
		
		@GetMapping
		public ModelAndView listar() {
			List<Livro> livros = lr.findAll();
			ModelAndView mv = new ModelAndView("biblioteca/listaLivro");
			mv.addObject("livros", livros);
			return mv;
		}
		
		@GetMapping("/{id}/selecionar")
		public ModelAndView selecionarLivro(@PathVariable Long id) {
			ModelAndView md = new ModelAndView();
			Optional<Livro> opt = lr.findById(id);
			if(opt.isEmpty()) {
				md.setViewName("redirect:/livros");
				return md;
			}
		
			Livro livro = opt.get();
			md.setViewName("biblioteca/formLivro");
			md.addObject("livro", livro);
			
			return md;
			
		}
		
		@GetMapping("/{id}/deletar")
		public String apagarLivro(@PathVariable Long id) {
			
			Optional<Livro> opt = lr.findById(id);
			
			if(!opt.isEmpty()) {  
				Livro livro = opt.get();
				lr.delete(livro);
			}
			
			return "redirect:/livros";
		}
}
