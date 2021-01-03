package com.algaworks.algafood.di.notificacao;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Qualifier;
@Retention(RetentionPolicy.RUNTIME) //quanto TEMPO o tipo de notificador deve PERMANECER onde for usada/ RetentionPolicy.RUNTIME (lida em tempo de execução)
@Qualifier
public @interface TipoDoNotificador {
	//value - valor padrao quando especifica numa anotação
	NivelUrgencia value();

}
