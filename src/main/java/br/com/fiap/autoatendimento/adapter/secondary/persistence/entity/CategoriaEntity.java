package br.com.fiap.autoatendimento.adapter.secondary.persistence.entity;

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
@Table(name = "categoria")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaEntity implements Serializable {

    @Id
    @Column(name = "id_categoria")
    private Integer idCategoria;

    @Column(name = "nome")
    private String nome;

}
