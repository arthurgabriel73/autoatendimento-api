package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import java.util.Optional;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPagamentoEntity;

public interface StatusPagamentoRepository {
    
    public Optional<StatusPagamentoEntity> findByNome(String nome);

}
