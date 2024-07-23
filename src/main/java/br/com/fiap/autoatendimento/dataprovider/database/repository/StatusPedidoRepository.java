package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.StatusPedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPedidoRepository extends JpaRepository<StatusPedidoEntity, Integer> {
}
