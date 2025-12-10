package br.ifs.edu.cads.api.hotel.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record EstadoDtoSemId(
        @NotBlank(message = "Nome da UF é obrigatorio")
        @Size(max = 2, message = "O nome da UF nao deve possuir mais de 2 caracteres.")
        String uf) {
}
