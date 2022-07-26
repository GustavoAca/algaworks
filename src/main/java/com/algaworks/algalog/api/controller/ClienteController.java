package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.repository.ClienteRepository;
import com.algaworks.algalog.domain.service.CatalogoClienteService;

import lombok.AllArgsConstructor;

@AllArgsConstructor // mesmo sentido do autowired
@RestController
@RequestMapping("/clientes")
public class ClienteController {

//	@PersistenceContext
//	private EntityManager manager;

	private ClienteRepository clienteRepository;
	private CatalogoClienteService catalogoClienteService;
	

	@GetMapping("/{nome}")
	public List<Cliente> listar(@PathVariable String nome) {
		return clienteRepository.findByNomeContaining(nome);
	}
	

	@GetMapping
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {

		// metodo 3
		return clienteRepository.findById(clienteId).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

//		metodo 2
//		return clienteRepository.findById(clienteId)
//				.map(cliente -> ResponseEntity.ok(cliente)).orElse(ResponseEntity.notFound().build());

		// metodo 1
//	Optional<Cliente> cliente =	clienteRepository.findById(clienteId);
//		if(cliente.isPresent()) {
//			ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return catalogoClienteService.salvar(cliente);
	}
	
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@Valid  @PathVariable Long clienteId, 
			@RequestBody Cliente cliente){
		
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setId(clienteId);
		cliente = catalogoClienteService.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
		
	}
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> deletar(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		
		 catalogoClienteService.excluir(clienteId);
		 
		 return ResponseEntity.noContent().build(); //codigo 204
	}

//	@GetMapping("/clientes")
//	public List<Cliente> listar() {return manager.createQuery("from Cliente", Cliente.class).getResultList();}
}
