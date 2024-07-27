package br.com.fiap.autoatendimento.dataprovider.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "pedido")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pedido")
    private Integer idPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cpf")
    private ClienteEntity cliente;

    @ManyToOne
    @JoinColumn(name = "id_status_pedido")
    private StatusPedidoEntity statusPedido;

    @Column(name = "data_hora_inicio")
    private Timestamp dataHoraInicio;

    @Column(name = "data_hora_fim")
    private Timestamp dataHoraFim;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
            joinColumns = @JoinColumn(name = "id_pedido"),
            inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<ProdutoEntity> produtos;

}
