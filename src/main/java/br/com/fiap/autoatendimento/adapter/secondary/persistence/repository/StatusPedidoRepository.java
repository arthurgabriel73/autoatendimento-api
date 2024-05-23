package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.StatusPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPedidoRepository extends JpaRepository<StatusPedidoEntity, Integer> {
}
