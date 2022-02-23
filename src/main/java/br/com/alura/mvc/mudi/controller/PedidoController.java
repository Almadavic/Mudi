package br.com.alura.mvc.mudi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.DTO.RequisicaoNovoPedido;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.services.UserService;

@Controller
@RequestMapping("/pedido")
public class PedidoController {


	
	@Autowired
	private UserService service;

	@GetMapping("/formulario")
	public String formulario(RequisicaoNovoPedido requisicao) {
		return "pedido/formulario";
	}

	@PostMapping("/novo")
	public String novo(@Valid RequisicaoNovoPedido requisicao,BindingResult result) { //BindingResult - > resultado
		// Valid - > Certificar que os atributos estão preenchidos.
		if(result.hasErrors()) {   
			return "pedido/formulario"; // se der erro, retorne para a pagina fo formulario
		}
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName(); // Busca o nome do usuario logado
		User user = service.findByUserName(username);
		Pedido pedido = requisicao.toPedido(); // DTO , transforma o RequisicaoNovoPedido em Pedido
		pedido.setUser(user);
		user.adicionarPedido(pedido);
	    service.insert(user);
		return "redirect:/usuario/pedido";
	}
	
	
	}


