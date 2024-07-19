package one.digitalinnovation.gof.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Cliente {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String nome;
	@ManyToOne
	private Endereco endereco;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public Cliente(String nome, Endereco endereco) {
		this.nome = nome;
		this.endereco = endereco;
	}

	public Cliente atualizarEndereco(Endereco endereco) {
		return new Cliente(this.nome, endereco);
	}

	public Cliente update(String nome, Endereco endereco) {
		return new Cliente(nome, endereco);
	}

	public Cliente() {
	}
}
