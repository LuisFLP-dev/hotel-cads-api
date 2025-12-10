package br.ifs.edu.cads.api.hotel.services;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository;

    public CidadeService(CidadeRepository cidadeRepository, EstadoRepository estadoRepository) {
        this.cidadeRepository = cidadeRepository;
        this.estadoRepository = estadoRepository;
    }

    private CidadeDto toDto(Cidade cidade) {
        Long estadoId = cidade.getEstado() != null ? cidade.getEstado().getidEstado() : null;

        return new CidadeDto(
                cidade.getIdCidade(),
                cidade.getNomeCidade(),
                estadoId
        );

    }

    public CidadeDto findById(Long id) {
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        CidadeDto cidadeDto = null;
        if (cidade.get() != null) {
            cidadeDto = toDto(cidade.get());
        }
        return cidadeDto;
    }

    public CidadeDto delete(Long id) {
        Optional<Cidade> cidadeDeletada = cidadeRepository.findById(id);
        CidadeDto cidadeDto = null;
        if (cidadeDeletada.get() != null) {
            cidadeDto = toDto(cidadeDeletada.get());
            cidadeRepository.deleteById(id);
        }
        return cidadeDto;
    }

    public List<Cidade> listAll() {
        return cidadeRepository.findAll();
    }

    public List<Cidade> listByEstado(Long idEstado) {
        estadoRepository.findById(idEstado).orElseThrow(() -> new RuntimeException("Estado nao encontrado"));

        return cidadeRepository.findByEstado_IdEstado(idEstado);
    }


    private Cidade fromDto(CidadeDto cidadeDto) {
        Cidade cidade = new Cidade();
        cidade.setIdCidade(cidadeDto.idCidade());
        cidade.setNomeCidade(cidadeDto.nomeCidade());
        Estado estado = estadoRepository.findById(cidadeDto.estado())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        cidade.setEstado(estado);
        return cidade;

    }

    @Transactional
    public CidadeDto save(CidadeDto cidadeDto) {
        Cidade cidade = fromDto(cidadeDto);
        Cidade cidadeSalva = cidadeRepository.save(cidade);
        return toDto(cidadeSalva);

    }

    @Transactional
    public CidadeDto updateCidade(Long id, CidadeDto cidadeDto) {
        Cidade cidade = cidadeRepository.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        cidade.setNomeCidade(cidadeDto.nomeCidade());

        if (cidadeDto.estado() != null) {
            Estado estado = estadoRepository.findById(cidadeDto.estado()).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            cidade.setEstado(estado);
        }

        Cidade cidadeAtualizada = cidadeRepository.save(cidade);
        return toDto(cidadeAtualizada);
    }
}
