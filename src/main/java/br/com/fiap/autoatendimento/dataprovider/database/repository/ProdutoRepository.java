package br.com.fiap.autoatendimento.dataprovider.database.repository;
import br.com.fiap.autoatendimento.dataprovider.database.entity.ProdutoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {
    List<ProdutoEntity> findByCategoriaNome(String categoria);
}
