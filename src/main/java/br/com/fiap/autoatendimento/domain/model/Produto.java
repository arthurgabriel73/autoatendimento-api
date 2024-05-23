package br.com.fiap.autoatendimento.domain.model;

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
	private Double preco;
	private String imagem;
	private Boolean disponivel;
	private Categoria categoria;
  
}
