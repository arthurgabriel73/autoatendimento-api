package br.com.fiap.autoatendimento.domain.model.pedido;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusPedido {

	Integer idStatusPedido;
	String nome;
  
}
