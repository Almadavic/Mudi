package br.com.alura.mvc.mudi.repositories;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alura.mvc.mudi.model.Pedido;
import br.com.alura.mvc.mudi.model.StatusPedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido,Integer> {
	
	@Cacheable("pedidos") // Faz o cache quando solicitar esse tipo de operação
	List<Pedido>findByStatus(StatusPedido status,Pageable page);
	
	
	


}
