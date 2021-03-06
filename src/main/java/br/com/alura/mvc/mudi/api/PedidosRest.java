package br.com.alura.mvc.mudi.api;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.services.PedidoService;

@RestController
@RequestMapping("/api/pedidos")
public class PedidosRest {

	@Autowired
	private PedidoService service;

	@GetMapping("/aguardando")
	public List<Pedido> getPedidosAguardandoOfertas(Principal principal) {

		Sort sort = Sort.by("id").descending();

		PageRequest pr = PageRequest.of(0, 10, sort);
		List<Pedido> pedido = service.findByStatus(StatusPedido.AGUARDANDO, pr);

		pedido.removeIf(p -> p.getUser().getUsername().equals(principal.getName()));
		return pedido;
	}

}
