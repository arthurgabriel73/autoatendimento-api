package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
