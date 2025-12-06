package br.ifs.edu.cads.api.hotel.services;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.entity.Estado;
import br.ifs.edu.cads.api.hotel.repository.CidadeRepository;
import br.ifs.edu.cads.api.hotel.repository.EstadoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    private final CidadeRepository cidadeRepository;
    private final EstadoRepository estadoRepository = null;

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

    public CidadeDto findById(Long id){
        Optional<Cidade> cidade = cidadeRepository.findById(id);

        CidadeDto cidadeDto = null;
        if (cidade.get() != null){
            cidadeDto = toDto(cidade.get());
        }

        return cidadeDto;
    }

    public List<Cidade> listAll(){return cidadeRepository.findAll();}

    private Cidade fromDto(CidadeDto cidadeDto){
        Cidade cidade = new Cidade();
        cidade.setIdCidade(cidadeDto.idCidade());
        cidade.setNomeCidade(cidadeDto.nomeCidade());
        return cidade;

    }
}
