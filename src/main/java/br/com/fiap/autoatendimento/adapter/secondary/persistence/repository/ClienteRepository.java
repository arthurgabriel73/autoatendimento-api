package br.com.fiap.autoatendimento.adapter.secondary.persistence.repository;

import br.com.fiap.autoatendimento.adapter.secondary.persistence.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteEntity, String> {
}
