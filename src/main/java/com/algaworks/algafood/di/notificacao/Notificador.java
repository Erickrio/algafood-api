package com.algaworks.algafood.di.notificacao;

import com.algaworks.algafood.di.modelo.Cliente;
//interface contratos - add novas implementações(whatapp,telegram,msg)
public interface Notificador {

	void notificar(Cliente cliente, String mensagem);

}