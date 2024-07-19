package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.dto.ClienteResponseDto;
import one.digitalinnovation.gof.dto.CreateClienteRequestDto;
import one.digitalinnovation.gof.dto.UpdateClienteRequestDto;
import one.digitalinnovation.gof.model.Cliente;

import java.util.List;
import java.util.Optional;

/**
 * Interface que define o padrão <b>Strategy</b> no domínio de cliente. Com
 * isso, se necessário, podemos ter multiplas implementações dessa mesma
 * interface.
 * 
 * @author falvojr
 */
public interface ClienteService {

	List<Cliente> buscarTodos();

	Optional<Cliente> buscarPorId(Long id);

	Cliente inserir(CreateClienteRequestDto cliente);

	Cliente atualizar(Long id, UpdateClienteRequestDto cliente);

	void deletar(Long id);

}
