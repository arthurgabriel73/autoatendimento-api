package br.com.fiap.autoatendimento.domain.model.produto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String imagem;
	private Boolean ativo;
	private Categoria categoria;
  
}
