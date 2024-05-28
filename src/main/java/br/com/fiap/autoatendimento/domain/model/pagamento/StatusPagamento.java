package br.com.fiap.autoatendimento.domain.model.pagamento;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class StatusPagamento {
    
    Integer idStatusPagamento;
    String nome;
    
}
