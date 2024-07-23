package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.CategoriaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {
    Optional<CategoriaEntity> findByNome(String nome);
}
