package one.digitalinnovation.gof.dto;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;

public class CreateClienteRequestDto {

    private String nome;
    private Endereco endereco;

    public CreateClienteRequestDto(String nome, Endereco endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public Cliente convertToModel(){
        return new Cliente(nome, endereco);
    }
}
