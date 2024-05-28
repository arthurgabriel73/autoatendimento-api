package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PagamentoEntity;
import java.util.Optional;


public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {
    
    public Optional<PagamentoEntity> findByPedidoIdPedido(Integer idPedido);
    
}
