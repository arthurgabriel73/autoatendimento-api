package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.CategoriaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

    Optional<CategoriaEntity> findByNome(String nome);

}
