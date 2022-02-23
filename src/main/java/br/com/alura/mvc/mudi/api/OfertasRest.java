package br.com.alura.mvc.mudi.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.mvc.mudi.DTO.RequisicaoNovaOferta;
import br.com.alura.mvc.mudi.model.Oferta;
import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.services.PedidoService;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {
	
	@Autowired
	private PedidoService service;
	
	
	
	@PostMapping
	public void enviarOferta(@Valid @RequestBody RequisicaoNovaOferta novaOferta) {
		Pedido pedidoBuscado = service.findById(novaOferta.getPedidoId());
		Oferta nova = novaOferta.toOferta();
		
		nova.setPedido(pedidoBuscado);
		pedidoBuscado.adicionarOferta(nova);
		
		service.insert(pedidoBuscado);
		
	}

}
