package br.com.fiap.autoatendimento.domain.model.pedido;

import br.com.fiap.autoatendimento.domain.model.cliente.Cliente;
import br.com.fiap.autoatendimento.domain.model.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {

	private Integer idPedido;
	private Cliente cliente;
	private List<Produto> produtos;
	private StatusPedido status;
	private LocalDateTime dataHoraInicio;
	private LocalDateTime dataHoraFim;

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

	public String calcularTempoEspera() {

		final Duration duration = Objects.isNull(dataHoraFim) ?
				Duration.between(dataHoraInicio, LocalDateTime.now()) : Duration.between(dataHoraInicio, dataHoraFim);
		final Long minutes = duration.toMinutes();

		return minutes.toString() + " minutos";
	}

}
