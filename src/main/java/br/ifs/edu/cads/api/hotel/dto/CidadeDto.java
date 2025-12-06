package br.ifs.edu.cads.api.hotel.dto;

import br.ifs.edu.cads.api.hotel.entity.Estado;
import jakarta.persistence.*;


public record CidadeDto(Long idCidade, String nomeCidade, Long estado) {
}
