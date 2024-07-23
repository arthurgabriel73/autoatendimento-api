package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {
}
