package br.ifs.edu.cads.api.hotel.controller;

import br.ifs.edu.cads.api.hotel.dto.EstadoDto;
import br.ifs.edu.cads.api.hotel.dto.EstadoDtoSemId;
import br.ifs.edu.cads.api.hotel.services.EstadoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {

    private final EstadoService estadoService;

    public EstadoController(EstadoService estadoService){
        this.estadoService = estadoService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadoDto> findById(@PathVariable Long id){
        EstadoDto estadoDto = estadoService.findById(id);
        return ResponseEntity.ok(estadoDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<EstadoDto> delete(@PathVariable Long id){
        estadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<List<EstadoDto>> getAll(){
        List<EstadoDto> estados = estadoService.listAll().stream().map(c -> new EstadoDto(
                c.getidEstado(), c.getUf()
        )).toList();
        return ResponseEntity.ok(estados);
    }

    @PostMapping ResponseEntity<EstadoDto> create(@Valid @RequestBody EstadoDtoSemId estadoDto){
        EstadoDto createdEstado = estadoService.save(estadoDto);
        URI location = URI.create("/api/estados/" + createdEstado.idEstado());
        return ResponseEntity.created(location).body(createdEstado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadoDto> update(@PathVariable Long id, @RequestBody EstadoDto estadoDto){
        EstadoDto estadoAtualizado = estadoService.updateEstado(id, estadoDto);
        return ResponseEntity.ok(estadoAtualizado);
    }
}
