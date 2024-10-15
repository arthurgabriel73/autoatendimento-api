package br.com.fiap.autoatendimento.dataprovider.database.repository;

import br.com.fiap.autoatendimento.dataprovider.database.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {

    List<ClienteEntity> findByEmail(String email);
}
