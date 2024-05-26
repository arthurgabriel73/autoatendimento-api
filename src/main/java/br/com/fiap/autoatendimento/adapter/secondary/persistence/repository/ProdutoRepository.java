package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;
import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ProdutoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByCategoriaNome(String categoria);
    
}
