package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//classe responsavel por requisições web
@Controller
public class MeuPrimeiroController {
	
	@GetMapping("/hello")
	@ResponseBody
	public String hello() {
		return "Hello Erick";
	}

}
