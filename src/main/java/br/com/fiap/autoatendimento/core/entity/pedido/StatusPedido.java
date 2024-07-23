package br.com.fiap.autoatendimento.core.entity.pedido;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusPedido {

	Integer idStatusPedido;
	String nome;
  
}
