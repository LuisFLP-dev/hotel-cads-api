package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.Estado;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record CidadeDto(
        Long idCidade,

        @NotBlank(message = "Nome da cidade é obrigatorio")
        @Size(max = 255, message = "O nome da cidade nao deve possuir mais de 255 caracteres.")
        String nomeCidade,

        Long estado) {
}
