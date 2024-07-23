package br.com.fiap.autoatendimento.core.gateway;

import java.util.Optional;

import br.com.fiap.autoatendimento.core.entity.produto.Categoria;

public interface CategoriaGateway {
    Optional<Categoria> buscarPorNome(String nome);
}
