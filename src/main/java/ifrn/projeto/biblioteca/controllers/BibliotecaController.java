package ifrn.projeto.biblioteca.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BibliotecaController {
	
	@RequestMapping("/emprestimos/form")
	public String form() {
		return "formEmprestimo";
	}

}
