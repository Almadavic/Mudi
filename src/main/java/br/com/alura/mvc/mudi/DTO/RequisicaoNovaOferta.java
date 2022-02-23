package br.com.alura.mvc.mudi.DTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import br.com.alura.mvc.mudi.model.Oferta;

public class RequisicaoNovaOferta {

	private Integer pedidoId;

	@Pattern(regexp="^\\d+(\\.\\d+{2})$?")
	@NotNull
	private String valor;

	@Pattern(regexp="^\\d{2}/\\d{2}/\\d{4}$")
	@NotNull
	private String dataEntrega;
	private String comentario;

	public RequisicaoNovaOferta() {

	}

	public RequisicaoNovaOferta(String valor, String dataEntrega, String comentario) {
		this.valor = valor;
		this.dataEntrega = dataEntrega;
		this.comentario = comentario;
	}

	public RequisicaoNovaOferta(Integer pedidoId, String valor, String dataEntrega, String comentario) {
		this.pedidoId = pedidoId;
		this.valor = valor;
		this.dataEntrega = dataEntrega;
		this.comentario = comentario;
	}

	public Integer getPedidoId() {
		return pedidoId;
	}

	public void setPedidoId(Integer pedidoId) {
		this.pedidoId = pedidoId;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(String dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Oferta toOferta() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Oferta oferta = new Oferta();
		oferta.setComentario(comentario);
		try {
			oferta.setDataEntrega(sdf.parse(dataEntrega));
			oferta.setValor(Double.parseDouble(valor));
			return oferta;
		} catch (ParseException e) {
			System.out.println("Erro : " + e.getMessage());
		}
		return null;
	}

}
