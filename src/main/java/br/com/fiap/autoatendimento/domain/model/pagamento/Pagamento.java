package br.com.fiap.autoatendimento.domain.model.pagamento;

import br.com.fiap.autoatendimento.domain.model.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
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

}
