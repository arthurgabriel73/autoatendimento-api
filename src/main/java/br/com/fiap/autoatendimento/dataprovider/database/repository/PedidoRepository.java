package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PedidoRepository extends JpaRepository<PedidoEntity, Integer> {

    @Query(
            "select p from PedidoEntity p " +
                    "where p.statusPedido.idStatusPedido not in (4, 5) " +
                    "order by p.statusPedido.idStatusPedido desc, p.idPedido asc"
    )
    List<PedidoEntity> findAllCustom();

}
