package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPagamentoEntity;

public interface StatusPagamentoRepository extends JpaRepository<StatusPagamentoEntity, Integer> {
    
    public Optional<StatusPagamentoEntity> findByNome(String nome);

}
