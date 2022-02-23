package br.com.alura.mvc.mudi.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.model.User;
import br.com.alura.mvc.mudi.services.PedidoService;
import br.com.alura.mvc.mudi.services.UserService;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
	
	@GetMapping
	public String retornaPagina() {
		return "redirect:/usuario/pedido";
	}
		
	
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private PedidoService pedidoService;

	@GetMapping("pedido")
	public String home(Model model, Principal principal) { // Principal -  busca o usuario logado
		User user = userService.findByUserName(principal.getName());
		List<Pedido> pedidos = user.getPedidos();
		model.addAttribute("pedidos", pedidos);
		return "usuario/home";
	}

	@GetMapping("pedido/{status}") // aguardando , aprovado , entregue - Status significa qualquer coisa q eu digitar dps do /usuario
	public String status(@PathVariable("status") String status, Model model, Principal principal) {
		User user = userService.findByUserName(principal.getName());
		List<Pedido> pedidos = statusMethod(user,status);
		model.addAttribute("pedidos", pedidos);
		model.addAttribute("status", status);
		return "usuario/home";
	}
	@GetMapping(value="ofertasDoPedido/{id}")
	public String buscarOfertasDoPedido(@PathVariable("id")Integer id,Model model) {
		Pedido pedido = pedidoService.findById(id);
		List<Oferta> ofertasPorPedido = pedido.getOfertas();
		model.addAttribute("ofertas",ofertasPorPedido);
		return "usuario/ofertasPorPedido";
	}

	@ExceptionHandler(IllegalArgumentException.class) // Caso procure por uma pagina inexistente, volte para usuario/pedido
	public String onError() {
		return "redirect:/usuario/pedido";
	}

	private List<Pedido> statusMethod(User user,String status) {
		List<Pedido> pedidos = new ArrayList<>();

		for (Pedido pedido : user.getPedidos()) {
			if (pedido.getStatus() == StatusPedido.valueOf(status.toUpperCase())) {
				pedidos.add(pedido);
			}
		}
		return pedidos;
	}

}
