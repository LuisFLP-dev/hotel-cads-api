package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.dto.CidadeDtoRead;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.services.CidadeService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<CidadeDto> delete(@PathVariable Long id) {
        cidadeService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<CidadeDtoRead>> getAll() {
        List<CidadeDtoRead> cidades = cidadeService.listAll().stream().map(c -> new CidadeDtoRead(
                c.getIdCidade(), c.getNomeCidade(), c.getEstado().getUf()
        )).toList();
        return ResponseEntity.ok(cidades);
    }

    @GetMapping("/f_Estados/{idEstado}")
    public ResponseEntity<List<CidadeDtoRead>> getByEstado(@PathVariable Long idEstado) {
        List<CidadeDtoRead> cidades = cidadeService.listByEstado(idEstado).stream().
                map(c -> new CidadeDtoRead(
                        c.getIdCidade(),
                        c.getNomeCidade(),
                        c.getEstado().getUf()
                )).toList();
        return ResponseEntity.ok(cidades);
    }

    @PostMapping
    public ResponseEntity<CidadeDto> create(@Valid @RequestBody CidadeDto cidadeDto) {
        CidadeDto createdCidade = cidadeService.save(cidadeDto);
        URI location = URI.create("/api/cidades/" + createdCidade.idCidade());
        return ResponseEntity.created(location).body(createdCidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CidadeDto> update(@PathVariable Long id, @RequestBody CidadeDto cidadeDto) {
        CidadeDto updatedCidade = cidadeService.updateCidade(id, cidadeDto);
        return ResponseEntity.ok(updatedCidade);
    }


}
