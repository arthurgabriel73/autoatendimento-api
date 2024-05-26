package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    @Query("SELECT p FROM ProdutoEntity p WHERE p.categoria.nome = ?1")
    List<ProdutoEntity> findByCategoriaNome(String categoria);
}
