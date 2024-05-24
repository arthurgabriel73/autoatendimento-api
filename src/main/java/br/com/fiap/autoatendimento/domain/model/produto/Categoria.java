package br.com.fiap.autoatendimento.domain.model.produto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Categoria {

	Integer idCategoria;
	String nome;

}
