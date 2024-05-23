package br.com.fiap.autoatendimento.adapter.secondary.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity implements Serializable {

    @Id
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne
    @JoinColumn(name = "cpf")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_status_pedido")
    private StatusPedidoEntity statusPedido;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<ProdutoEntity> produtos;

}
