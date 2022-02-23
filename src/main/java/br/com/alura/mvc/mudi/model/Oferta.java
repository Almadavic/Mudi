package br.com.alura.mvc.mudi.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="ofertas")
public class Oferta implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private Double valor;
	private Date dataEntrega;
	private String comentario;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Pedido pedido;
	
	public Oferta() {
		
	}

	public Oferta(Double valor, Date dataEntrega, String comentario,Pedido pedido) {
		this.valor = valor;
		this.dataEntrega = dataEntrega;
		this.comentario = comentario;
		this.pedido=pedido;
	}

	public Oferta(Integer id, Double valor, Date dataEntrega, String comentario,Pedido pedido) {
		this.id = id;
		this.valor = valor;
		this.dataEntrega = dataEntrega;
		this.comentario = comentario;
		this.pedido=pedido;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	
	
	
}
