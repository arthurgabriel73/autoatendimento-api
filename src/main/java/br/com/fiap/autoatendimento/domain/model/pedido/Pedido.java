package br.com.fiap.autoatendimento.domain.model.pedido;

import java.util.ArrayList;
import java.util.List;

import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	private Integer idPedido;
	private Cliente cliente;
	private List<Produto> produtos;
	private StatusPedido status;

	public void atualizarStatus(StatusPedido status) {
		this.status = status;
	}

	public void adicionarProduto(Produto produto) {

		if (produtos == null) {
			produtos = new ArrayList<>();
		}

		produtos.add(produto);
	}

	public void removerProduto(Produto produto) {

		if (produtos != null) {
			produtos.remove(produto);
		}

	}

	public double calcularValorTotal() {

		return produtos.stream()
			.mapToDouble(Produto::getPreco)
			.sum();
	}
  
}
