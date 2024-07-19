package one.digitalinnovation.gof.dto;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.model.Endereco;

public class ClienteResponseDto {

    private String nome;
    private Endereco endereco;

    public ClienteResponseDto(Cliente cliente) {
        this.nome = cliente.getNome();
        this.endereco = cliente.getEndereco();
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
