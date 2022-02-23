package br.com.alura.mvc.mudi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private String username;
	private String password;
	private Boolean enabled;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy="user", fetch = FetchType.EAGER) // Eager necessario para carregar os pedidos do usuario
	private List<Pedido>pedidos = new ArrayList<>();
	
	public User() {
		
	}

	public User(String username, String password, Boolean enabled) {
		this.username = username;
		this.password = password;
		this.enabled = enabled;
	}
	
	public void adicionarPedido(Pedido pedido) {
		pedidos.add(pedido);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	@Override
	public String toString() {
		return "Username=" + username + ", password=" + password + ", enabled=" + enabled;			
	}
	
	

}
