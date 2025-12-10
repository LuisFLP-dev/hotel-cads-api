package br.ifs.edu.cads.api.hotel.services;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.dto.EstadoDtoSemId;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {


    private final EstadoRepository estadoRepository;

    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    private EstadoDto toDto(Estado estado) {
        return new EstadoDto(estado.getidEstado(), estado.getUf());
    }

    public EstadoDto findById(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        EstadoDto estadoDto = null;
        if (estado.get() != null) {
            estadoDto = toDto(estado.get());
        }

        return estadoDto;
    }

    public EstadoDto delete(Long id) {
        Optional<Estado> estadoDeletado = estadoRepository.findById(id);
        EstadoDto estadoDto = null;
        if (estadoDeletado.get() != null) {
            estadoDto = toDto(estadoDeletado.get());
            estadoRepository.deleteById(id);
        }

        return estadoDto;
    }

    public List<Estado> listAll() {
        return estadoRepository.findAll();
    }

    private Estado fromDto(EstadoDtoSemId estadoDtoSemId) {
        Estado estado = new Estado();
        estado.setUf(estadoDtoSemId.uf());
        return estado;
    }

    @Transactional
    public EstadoDto save(EstadoDtoSemId estadoDtoSemId) {
        Estado estado = fromDto(estadoDtoSemId);
        estado.setUf(estadoDtoSemId.uf());
        Estado estadoSalvo = estadoRepository.save(estado);
        return toDto(estadoSalvo);
    }

    @Transactional
    public EstadoDto updateEstado(Long id, EstadoDto estadoDto) {
        Estado estado = estadoRepository.findById(id).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        estado.setUf(estadoDto.uf());

        Estado estadoAtualizado = estadoRepository.save(estado);
        return toDto(estadoAtualizado);
    }


}
