package br.com.fiap.autoatendimento.core.entity.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	private Integer idProduto;
	private String nome;
	private String descricao;
	private Double preco;
	private String imagem;
	private Boolean ativo;
	private Categoria categoria;

	public void desativar() {

		this.ativo = false;
	}
  
}
