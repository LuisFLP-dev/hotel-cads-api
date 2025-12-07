package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.services.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<CidadeDto> findById(@PathVariable Long id) {
        CidadeDto cidadeDto = cidadeService.findById(id);
        return ResponseEntity.ok(cidadeDto);
    }

    @GetMapping("/")
    public ResponseEntity<List<CidadeDto>> getAll() {
        List<CidadeDto> cidades = cidadeService.listAll().stream().map(c -> new CidadeDto(
                c.getIdCidade(), c.getNomeCidade(), c.getEstado().getidEstado()
        )).toList();
        return ResponseEntity.ok(cidades);
    }

    @PostMapping
    public ResponseEntity<CidadeDto> create(@Valid )


}
