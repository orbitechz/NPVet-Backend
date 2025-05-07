package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.service.AnamneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/npvet-api/anamnese")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<AnamneseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(anamneseService.getById(id));
    }

    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnamneseDTO>> getAll() {
        return ResponseEntity.ok(anamneseService.getAll());
    }

    @GetMapping("/tutor/{cpf}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(anamneseService.getByTutorCpf(cpf));
    }

    @GetMapping("/tutor/{cpf}/animal/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpfAndAnimal(@PathVariable("cpf") String cpf,
                                                    @PathVariable("nome") String nome) {
        return ResponseEntity.ok(anamneseService.getByTutorCpfAndAnimal(cpf,nome));
    }

    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<AnamneseDTO> create(@AuthenticationPrincipal Usuario usuario, @RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.create(anamneseDTO, usuario));
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'ADMINISTRADOR')")
    public ResponseEntity<AnamneseDTO> update(@AuthenticationPrincipal Usuario usuario, @PathVariable Long id,
                                         @RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.update(id, anamneseDTO, usuario));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<AnamneseDTO> delete(@AuthenticationPrincipal Usuario usuario, @PathVariable("id") Long id){
        return ResponseEntity.ok(anamneseService.delete(id, usuario));
    }
    @PostMapping("/activate/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<AnamneseDTO> activate(@AuthenticationPrincipal Usuario usuario, @PathVariable("id") Long id){
        return ResponseEntity.ok(anamneseService.activate(id, usuario));
    }


}
