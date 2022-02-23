package br.com.alura.mvc.mudi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;
import br.com.alura.mvc.mudi.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public void insert(Pedido pedido) {
		repository.save(pedido);
	}

	public List<Pedido> findAll() {
		return repository.findAll();
	}

	public Pedido findById(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		try {
			return pedido.orElseThrow(() -> new Exception());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Pedido> findByStatus(StatusPedido aguardando, Pageable page) {
		return repository.findByStatus(aguardando, page);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
	
	public void update(Integer id,Pedido pedido) {
		Pedido pedidoAtualizado = findById(id);
		pedidoAtualizado.setNomeProduto(pedido.getNomeProduto());
		//-----------OUTROS ATRIBUTOS TAMBÉM ALÉM DO NOME--------//
		insert(pedidoAtualizado);
		
	}

}
