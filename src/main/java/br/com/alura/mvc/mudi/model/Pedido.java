package br.com.alura.mvc.mudi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="pedidos")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomeProduto;
	private Double valor;
	private Date data;
	private String urlProduto;
	private String urlImagem;
	private String descricao;
	@Enumerated(EnumType.STRING) // NO BANCO DE DADOS, CONVERTE DE ENUM PARA STRING
	private StatusPedido status;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JsonIgnore // Não carregar o user no formato JSON
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy="pedido",fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Oferta>ofertas = new ArrayList<>();

	public Pedido() {

	}

	public Pedido(String nomeProduto, Double valor, Date data, String urlProduto, String urlImagem, String descricao,User user) {
		this.nomeProduto = nomeProduto;
		this.valor = valor;
		this.data = data;
		this.urlProduto = urlProduto;
		this.urlImagem = urlImagem;
		this.descricao = descricao;
		this.user=user;
	}

	public Pedido(Integer id, String nomeProduto, Double valor, Date data, String urlProduto, String urlImagem,
			String descricao,User user) {
		this.id = id;
		this.nomeProduto = nomeProduto;
		this.valor = valor;
		this.data = data;
		this.urlProduto = urlProduto;
		this.urlImagem = urlImagem;
		this.descricao = descricao;
		this.user=user;
	}
	
	public void adicionarOferta(Oferta oferta) {
		ofertas.add(oferta);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setDataDaEntrega(Date data) {
		this.data = data;
	}

	public String getUrlProduto() {
		return urlProduto;
	}

	public void setUrlProduto(String urlProduto) {
		this.urlProduto = urlProduto;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public StatusPedido getStatus() {
		return status;
	}

	public void setStatus(StatusPedido status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", nomeProduto=" + nomeProduto + ", valor=" + valor + ", data=" + data
				+ ", urlProduto=" + urlProduto + ", urlImagem=" + urlImagem + ", descricao=" + descricao + ", status="
				+ status + ", user=" + user + "]";
	}
	
	

}
