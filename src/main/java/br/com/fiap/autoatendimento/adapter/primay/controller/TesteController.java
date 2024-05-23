package br.com.fiap.autoatendimento.adapter.primay.controller;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.*;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TesteController {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private PedidoRepository pedidoRepository;
    @Autowired
    private ProdutoRepository produtoRepository;
    @Autowired
    private StatusPedidoRepository statusPedidoRepository;

    @GetMapping("/testes")
    public String testes() {

        return "Hello World!";
    }

    @GetMapping("/categorias")
    public List<CategoriaEntity> listarCategorias() {

        return categoriaRepository.findAll();
    }

    @GetMapping("/clientes")
    public List<ClienteEntity> listarClientes() {

        return clienteRepository.findAll();
    }

    @GetMapping("/pedidos")
    public List<PedidoEntity> listarPedidos() {

        return pedidoRepository.findAll();
    }

    @GetMapping("/produtos")
    public List<ProdutoEntity> listarProdutos() {

        return produtoRepository.findAll();
    }

    @GetMapping("/status_pedido")
    public List<StatusPedidoEntity> listarStatusPedido() {

        return statusPedidoRepository.findAll();
    }

}
