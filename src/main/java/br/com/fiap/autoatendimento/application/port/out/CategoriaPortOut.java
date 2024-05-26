package br.com.fiap.autoatendimento.application.port.out;

import java.util.Optional;

import br.com.fiap.autoatendimento.domain.model.produto.Categoria;

public interface CategoriaPortOut {
    Optional<Categoria> buscarPorNome(String nome);
}
