package br.com.fiap.autoatendimento.dataprovider.database;

import java.util.Optional;

import br.com.fiap.autoatendimento.dataprovider.database.repository.CategoriaRepository;
import br.com.fiap.autoatendimento.core.gateway.CategoriaGateway;
import br.com.fiap.autoatendimento.core.entity.produto.Categoria;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;

@Named
@RequiredArgsConstructor
public class CategoriaDatabase implements CategoriaGateway {

    private final CategoriaRepository categoriaRepository;
    
    @Override
    public Optional<Categoria> buscarPorNome(String nome) {

        return categoriaRepository.findByNome(nome)
                .map(entity -> Categoria.builder()
                        .idCategoria(entity.getIdCategoria())
                        .nome(entity.getNome())
                        .build());
    }

}
