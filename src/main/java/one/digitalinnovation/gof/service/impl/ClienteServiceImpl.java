package one.digitalinnovation.gof.service.impl;

import java.util.List;
import java.util.Optional;

import one.digitalinnovation.gof.dto.CreateClienteRequestDto;
import one.digitalinnovation.gof.dto.UpdateClienteRequestDto;
import one.digitalinnovation.gof.exception.ExceptionMsg;
import one.digitalinnovation.gof.service.ValidacaoCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Cliente;
import one.digitalinnovation.gof.repository.ClienteRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.repository.EnderecoRepository;
import one.digitalinnovation.gof.service.ClienteService;
import one.digitalinnovation.gof.service.ViaCepService;

/**
 * Implementação da <b>Strategy</b> {@link ClienteService}, a qual pode ser
 * injetada pelo Spring (via {@link Autowired}). Com isso, como essa classe é um
 * {@link Service}, ela será tratada como um <b>Singleton</b>.
 * 
 * @author falvojr
 */
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;

	private List<ValidacaoCliente> validacoes;

	@Autowired
	public ClienteServiceImpl(List<ValidacaoCliente> validacoes) {
		this.validacoes = validacoes;
	}

	@Override
	public List<Cliente> buscarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public Optional<Cliente> buscarPorId(Long id) {
		return clienteRepository.findById(id);
	}

	@Override
	public Cliente inserir(CreateClienteRequestDto clienteDto) {
		Cliente novoCliente = clienteDto.convertToModel();
		validarCliente(novoCliente);
		return salvarClienteComCep(novoCliente);
	}

	@Override
	public Cliente atualizar(Long id, UpdateClienteRequestDto cliente) {
		Cliente retornaCliente = verificaExistencia(id);
		Cliente atualizaCliente = retornaCliente.update(cliente.getNome(), cliente.getEndereco());
		validarCliente(atualizaCliente);
		return clienteRepository.save(atualizaCliente);
	}

	@Override
	public void deletar(Long id) {
		clienteRepository.deleteById(id);
	}

	private Cliente salvarClienteComCep(Cliente cliente) {
		String cep = cliente.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		Cliente clienteComEnderecoAtualizado = cliente.atualizarEndereco(endereco);
		return clienteRepository.save(clienteComEnderecoAtualizado);
	}

	public Cliente verificaExistencia(Long clienteId){
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);

		if(cliente.isEmpty()){

			throw new ExceptionMsg("Pessoa não encontrado");
		}

		return cliente.get();
	}

	private void validarCliente(Cliente cliente) {
		validacoes.forEach(validacao -> validacao.validar(cliente));
	}

}
