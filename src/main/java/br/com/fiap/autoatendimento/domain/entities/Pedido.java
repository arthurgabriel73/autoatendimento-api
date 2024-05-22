package br.com.fiap.autoatendimento.domain.entities;

import java.util.List;

import br.com.fiap.autoatendimento.domain.entities.enums.StatusPedido;
import lombok.Getter;

@Getter
public class Pedido {
  private Cliente cliente;
  private List<Produto> produtos;
  private StatusPedido status;

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
