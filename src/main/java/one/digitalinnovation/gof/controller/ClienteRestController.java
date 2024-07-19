package one.digitalinnovation.gof.controller;

import one.digitalinnovation.gof.dto.ClienteResponseDto;
import one.digitalinnovation.gof.dto.CreateClienteRequestDto;
import one.digitalinnovation.gof.dto.UpdateClienteRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.service.ClienteService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Esse {@link RestController} representa nossa <b>Facade</b>, pois abstrai toda
 * a complexidade de integrações (Banco de Dados H2 e API do ViaCEP) em uma
 * interface simples e coesa (API REST).
 * @author falvojr
 */
@RestController
@RequestMapping("clientes")
public class ClienteRestController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteResponseDto>> buscarTodos() {
		List<ClienteResponseDto> clientes = clienteService.buscarTodos().stream()
				.map(cliente -> new ClienteResponseDto(cliente)).collect(Collectors.toList());
		return ResponseEntity.ok(clientes);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> buscarPorId(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.buscarPorId(id);
		if(!cliente.isPresent()){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente não localizado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(cliente);
	}

	@PostMapping
	public ResponseEntity<ClienteResponseDto> inserir(@RequestBody CreateClienteRequestDto cliente) {
		ClienteResponseDto novoCliente = new ClienteResponseDto(clienteService.inserir(cliente));
		return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponseDto> atualizar(@PathVariable Long id, @RequestBody UpdateClienteRequestDto cliente) {
		ClienteResponseDto atualizaCliente = new ClienteResponseDto(clienteService.atualizar(id, cliente));
		return ResponseEntity.status(HttpStatus.OK).body(atualizaCliente);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		clienteService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
