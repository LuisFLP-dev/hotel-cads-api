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
    private final EstadoRepository estadoRepository = null;

    public CidadeService(CidadeRepository cidadeRepository) {
        this.cidadeRepository = cidadeRepository;
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
        cidade.get();
        cidadeDto = toDto(cidade.get());

        return cidadeDto;
    }

    public CidadeDto delete(Long id) {
        Optional<Cidade> cidadeDeletada = cidadeRepository.findById(id);
        CidadeDto cidadeDto = null;
        cidadeDeletada.get();
        cidadeDto = toDto(cidadeDeletada.get());
        cidadeRepository.deleteById(id);
        return cidadeDto;
    }

    public List<Cidade> listAll() {
        return cidadeRepository.findAll();
    }

    private Cidade fromDto(CidadeDto cidadeDto) {
        Cidade cidade = new Cidade();
        cidade.setIdCidade(cidadeDto.idCidade());
        cidade.setNomeCidade(cidadeDto.nomeCidade());
        return cidade;

    }

    @Transactional
    public CidadeDto save(CidadeDto cidadeDto) {
        Cidade cidade = fromDto(cidadeDto);
        Cidade SaveCidade = cidadeRepository.save(cidade);
        return toDto(SaveCidade);

    }

    @Transactional
    public Cidade updateCidade(Long id, Cidade cidade) {
        cidade.setIdCidade(id);
        return cidadeRepository.save(cidade);
    }
}
