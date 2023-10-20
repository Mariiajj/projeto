package ifrn.projeto.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.projeto.biblioteca.models.Emprestimo;
import ifrn.projeto.biblioteca.repositories.EmprestimoRepository;

@Controller
public class BibliotecaController {
	
	@Autowired
	private EmprestimoRepository er;
	
	@RequestMapping("/emprestimos/form")
	public String form() {
		return "formEmprestimo";
	}

	@PostMapping("/emprestimos")
	public String realizar(Emprestimo emprestimo) {
		
		System.out.println(emprestimo);
		er.save(emprestimo);
		
		return "emprestimo-realizado";
	}
}
