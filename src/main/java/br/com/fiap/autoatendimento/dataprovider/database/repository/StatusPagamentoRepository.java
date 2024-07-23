package br.com.fiap.autoatendimento.dataprovider.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.StatusPagamentoEntity;

public interface StatusPagamentoRepository extends JpaRepository<StatusPagamentoEntity, Integer> {
    Optional<StatusPagamentoEntity> findByNome(String nome);
}
