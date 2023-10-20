package ifrn.projeto.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ifrn.projeto.biblioteca.models.Emprestimo;

@Controller
public class BibliotecaController {
	
	@RequestMapping("/emprestimos/form")
	public String form() {
		return "formEmprestimo";
	}

	@PostMapping("/emprestimos")
	public String realizar(Emprestimo emprestimo) {
		
		System.out.println(emprestimo);
		
		return "emprestimo-realizado";
	}
}
