package br.com.fiap.autoatendimento.core.entity.pagamento;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusPagamento {
    
    Integer idStatusPagamento;
    String nome;
    
}
