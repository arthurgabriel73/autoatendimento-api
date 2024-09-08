package br.com.fiap.autoatendimento.core.entity.produto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

	private Integer idProduto;
	private String nome;
	private String descricao;
	private BigDecimal preco;
	private String imagem;
	private Boolean ativo;
	private Categoria categoria;

	public void desativar() {
		this.ativo = false;
	}

	public boolean isAtivo() {
		return this.ativo;
	}
  
}
