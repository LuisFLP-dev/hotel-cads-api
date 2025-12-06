package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.CidadeDto;
import br.ifs.edu.cads.api.hotel.entity.Cidade;
import br.ifs.edu.cads.api.hotel.services.CidadeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<Cidade>> listAll() {
        List<Cidade> cidades = cidadeService.listAll();
        return ResponseEntity.ok(cidades);
    }


}
