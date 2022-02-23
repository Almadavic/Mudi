package br.com.alura.mvc.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cadastro")
public class CadastroController {
	
	public String cadastroUsuario() {
		return "/cadastro";
	}

}
