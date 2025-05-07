package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.dto.PerguntaDTO;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.service.PerguntaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/npvet-api/pergunta")
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
    public ResponseEntity<PerguntaDTO> create(@AuthenticationPrincipal Usuario usuarioAutenticado, @RequestBody @Validated PerguntaDTO perguntaDTO) {
        return ResponseEntity.ok(perguntaService.create(perguntaDTO, usuarioAutenticado));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PerguntaDTO> update(@AuthenticationPrincipal Usuario usuarioAutenticado, @PathVariable Long id, @RequestBody @Validated PerguntaDTO perguntaDTO) {
        return ResponseEntity.ok(perguntaService.update(id, perguntaDTO, usuarioAutenticado));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<PerguntaDTO> delete(@AuthenticationPrincipal Usuario usuarioAutenticado, @PathVariable("id") Long id, PerguntaDTO perguntaDTO){
        return ResponseEntity.ok(perguntaService.delete(id, usuarioAutenticado));
    }
    @PostMapping("/activate/{id}")
    public ResponseEntity<PerguntaDTO> activate(@AuthenticationPrincipal Usuario usuarioAutenticado, @PathVariable("id") Long id){
        return ResponseEntity.ok(perguntaService.activate(id, usuarioAutenticado));
    }
}
