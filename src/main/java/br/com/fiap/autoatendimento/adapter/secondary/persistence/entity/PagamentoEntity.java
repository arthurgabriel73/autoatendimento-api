package br.com.fiap.autoatendimento.adapter.secondary.persistence.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pagamento")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pagamento")
    private Integer idPagamento;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_status_pagamento", updatable = false, nullable = false)
    private StatusPagamentoEntity statusPagamento;

    @OneToOne(optional = false)
    @JoinColumn(name = "id_pedido", unique = true, updatable = false, nullable = false)
    private PedidoEntity pedido;
    
}
