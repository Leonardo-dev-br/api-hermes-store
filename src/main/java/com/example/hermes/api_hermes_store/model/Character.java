package com.example.hermes.api_hermes_store.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String nameCharacter;

    @NotBlank(message = "Campo obrigatório")
    private String classCharacter;

    @NotNull(message = "Campo obrigatório")
    @Min(value = 1, message = "O nível deve ser no mínimo 1")
    @Max(value = 99, message = "O nível deve ser no máximo 99")
    private int level;

    @NotNull(message = "As moedas são obrigatórias")
    @Min(value = 0, message = "As moedas devem ser um valor positivo")
    private double coins;
}
