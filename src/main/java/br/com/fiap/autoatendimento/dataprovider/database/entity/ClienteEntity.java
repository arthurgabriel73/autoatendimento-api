package br.com.fiap.autoatendimento.dataprovider.database.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "cliente")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity implements Serializable {

    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "email")
    private String email;

}
