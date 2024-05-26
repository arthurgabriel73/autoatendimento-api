package br.com.fiap.autoatendimento.adapter.secondary.persistence;

import java.util.Optional;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.repository.CategoriaRepository;
import br.com.fiap.autoatendimento.application.port.out.CategoriaPortOut;
import br.com.fiap.autoatendimento.domain.model.produto.Categoria;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CategoriaDao implements CategoriaPortOut {

    private final CategoriaRepository categoriaRepository;
    
    @Override
    public Optional<Categoria> buscarPorNomeCategoria(String nome) {

        return categoriaRepository.findByNome(nome).map(entity ->
                Categoria.builder()
                        .idCategoria(entity.getIdCategoria())
                        .nome(entity.getNome())
                        .build());

    }

}
