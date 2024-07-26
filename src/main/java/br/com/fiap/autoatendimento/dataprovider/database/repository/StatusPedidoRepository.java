package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.StatusPedidoEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusPedidoRepository extends JpaRepository<StatusPedidoEntity, Integer> {
    Optional<StatusPedidoEntity> findByNome(String nome);
}
