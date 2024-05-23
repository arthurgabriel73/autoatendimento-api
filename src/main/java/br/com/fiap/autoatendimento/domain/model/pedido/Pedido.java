package br.com.fiap.autoatendimento.domain.model.pedido;

import java.math.BigDecimal;
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

	public BigDecimal calcularValorTotal() {
		BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal amt : produtos.stream().map(Produto::getPreco).toList()) {
            sum = sum.add(amt);
        }
		return sum;
	}
  
}
