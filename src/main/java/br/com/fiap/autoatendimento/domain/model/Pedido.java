package br.com.fiap.autoatendimento.domain.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// Aggregate Root
public class Pedido {

	private StatusPedido status;
	private Cliente cliente;
	private List<Produto> produtos;

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
