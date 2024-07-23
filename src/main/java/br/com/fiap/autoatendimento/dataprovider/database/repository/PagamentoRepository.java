package br.com.fiap.autoatendimento.dataprovider.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.PagamentoEntity;
import java.util.Optional;


public interface PagamentoRepository extends JpaRepository<PagamentoEntity, Integer> {
    Optional<PagamentoEntity> findByPedidoIdPedido(Integer idPedido);
}
