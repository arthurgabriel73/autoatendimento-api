package br.com.fiap.autoatendimento.domain.entities;

import java.util.List;

import br.com.fiap.autoatendimento.domain.entities.enums.StatusPedido;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "pedidos")
@Entity(name = "Pedido")
@Getter
@EqualsAndHashCode(of = "id")
public class Pedido {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "status")
  @Enumerated(EnumType.STRING)
  private StatusPedido status;

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "cliente_id")
  private Cliente cliente;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
    name = "pedidos_produtos",
    joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "produto_id", referencedColumnName = "id")
  )
  private List<Produto> produtos;

  public Pedido(Cliente cliente, List<Produto> produtos) {
    this.cliente = cliente;
    this.produtos = produtos;
    this.status = StatusPedido.RECEBIDO;
  }

  public void preparar() {
    this.status = StatusPedido.EM_PREPARACAO;
  }

  public void pronto() {
    this.status = StatusPedido.PRONTO;
  }
  
  public void finalizar() {
    this.status = StatusPedido.FINALIZADO;
  }

  public void cancelar() {
    this.status = StatusPedido.CANCELADO;
  }
  
  public void adicionarProduto(Produto produto) {
    produtos.add(produto);
  }

  public void removerProduto(Produto produto) {
    produtos.remove(produto);
  }

  public double getValorTotal() {
    double valorTotal = 0;
    for (Produto produto : produtos) {
      valorTotal += produto.getPreco();
    }
    return valorTotal;
  }
  
}
