package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pergunta")
public class PerguntaController {

    @Autowired
    public PerguntaService perguntaService;

    @GetMapping("/{id}")
    public ResponseEntity<PerguntaDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(perguntaService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PerguntaDTO>> getAll() {
        return ResponseEntity.ok(perguntaService.getAll());
    }

    @PostMapping("/post")
    public ResponseEntity<PerguntaDTO> create(@RequestBody @Validated PerguntaDTO perguntaDTO) {
        return ResponseEntity.ok(perguntaService.create(perguntaDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PerguntaDTO> update(@PathVariable Long id, @RequestBody @Validated PerguntaDTO perguntaDTO) {
        return ResponseEntity.ok(perguntaService.update(id, perguntaDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PerguntaDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(perguntaService.delete(id));
    }
    @PostMapping("/activate/{id}")
    public ResponseEntity<PerguntaDTO> activate(@PathVariable("id") Long id){
        return ResponseEntity.ok(perguntaService.activate(id));
    }
}
