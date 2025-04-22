package com.example.hermes.api_hermes_store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do item é obrigatório")
    private String nome;

    @NotBlank(message = "Tipo do item é obrigatório")
    private String tipo; // Ex: Arma, Armadura, Poção, Acessório

    @NotBlank(message = "Raridade do item é obrigatória")
    private String raridade; // Ex: Comum, Raro, Épico, Lendário

    @NotNull(message = "Preço do item é obrigatório")
    @Positive(message = "Preço deve ser um valor positivo")
    private Double preco;

    @ManyToOne
    @NotNull(message = "O item deve ter um dono")
    private Character dono; // Relacionamento com Personagem (dono do item)

}
