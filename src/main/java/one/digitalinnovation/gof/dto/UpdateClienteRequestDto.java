package one.digitalinnovation.gof.dto;

import one.digitalinnovation.gof.model.Endereco;

public class UpdateClienteRequestDto {

    private String nome;
    private Endereco endereco;

    public UpdateClienteRequestDto() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }
}
