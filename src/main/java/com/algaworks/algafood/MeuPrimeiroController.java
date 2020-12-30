package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.algaworks.algafood.di.modelo.Cliente;
import com.algaworks.algafood.di.service.AtivacaoClienteService;

//classe responsavel por requisições web
@Controller
public class MeuPrimeiroController {
	
	//injetando 
	private AtivacaoClienteService ativacaoClienteService;

	public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
		super();
		this.ativacaoClienteService = ativacaoClienteService;
		
		System.out.println("MEu primeiro Controller"+ativacaoClienteService);
	}


	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		Cliente joão = new Cliente("joao","joao@bol.com","98838383");
		ativacaoClienteService.ativar(joão);
		return "Hello Erick";
	}

}
