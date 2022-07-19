package com.algaworks.algalog.controller;

import com.algaworks.algalog.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;


@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {

		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Joao");
		cliente1.setTelefone("31 99999-1111");
		cliente1.setEmail("joaodascouve@algaworks.com");

		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("MAria");
		cliente2.setTelefone("11 98888-1111");
		cliente2.setEmail("mariadascouve@algaworks.com");

		return Arrays.asList(cliente1, cliente2);
	}
}
