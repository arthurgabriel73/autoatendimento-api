package br.com.fiap.autoatendimento.core.entity.pagamento;

import br.com.fiap.autoatendimento.core.entity.pedido.Pedido;
import br.com.fiap.autoatendimento.core.exception.StatusFinalException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pagamento {
    
    private Integer id;
    private StatusPagamento status;
    private Pedido pedido;

    public Integer getIdPedido() {

        return pedido.getIdPedido();
    }

    public void atualizarStatus(StatusPagamento novoStatus) {
        if (!status.getNome().equalsIgnoreCase("PENDENTE")) {
            throw new StatusFinalException("Não é possível avançar de pagamento não pendente.");
        }
        this.status = novoStatus;
    }

}
