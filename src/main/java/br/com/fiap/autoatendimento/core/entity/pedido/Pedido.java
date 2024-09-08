package br.com.fiap.autoatendimento.core.entity.pedido;

import br.com.fiap.autoatendimento.core.entity.cliente.Cliente;
import br.com.fiap.autoatendimento.core.entity.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.math.BigDecimal;

@Getter
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

    public void atualizarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;

        if (novoStatus.getNome().equalsIgnoreCase("FINALIZADO") ||
                novoStatus.getNome().equalsIgnoreCase("CANCELADO")) {
            this.dataHoraFim = LocalDateTime.now();
        }
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
        return produtos.stream()
                .map(Produto::getPreco)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String calcularTempoEspera() {
        final Duration duration = Objects.isNull(dataHoraFim) ?
                Duration.between(dataHoraInicio, LocalDateTime.now()) : Duration.between(dataHoraInicio, dataHoraFim);
        final Long minutes = duration.toMinutes();
        return minutes.toString() + " minutos";
    }
}
