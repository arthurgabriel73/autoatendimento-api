package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
}
