package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnamneseDTO;
import com.orbitech.npvet.service.AnamneseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/anamnese")
public class AnamneseController {

    @Autowired
    private AnamneseService anamneseService;

    @GetMapping("/{id}")
    public ResponseEntity<AnamneseDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(anamneseService.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnamneseDTO>> getAll() {
        return ResponseEntity.ok(anamneseService.getAll());
    }

    @GetMapping("/tutor/{cpf}")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpf(@PathVariable("cpf") String cpf) {
        return ResponseEntity.ok(anamneseService.getByTutorCpf(cpf));
    }

    @GetMapping("/tutor/{cpf}/animal/{nome}")
    public ResponseEntity<List<AnamneseDTO>> getByTutorCpfAndAnimal(@PathVariable("cpf") String cpf,
                                                    @PathVariable("nome") String nome) {
        return ResponseEntity.ok(anamneseService.getByTutorCpfAndAnimal(cpf,nome));
    }

    @PostMapping("/post")
    public ResponseEntity<AnamneseDTO> create(@RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.create(anamneseDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnamneseDTO> update(@PathVariable Long id,
                                         @RequestBody @Validated AnamneseDTO anamneseDTO) {
            return ResponseEntity.ok(anamneseService.update(id, anamneseDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AnamneseDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(anamneseService.delete(id));
    }
    @PostMapping("/activate/{id}")
    public ResponseEntity<AnamneseDTO> activate(@PathVariable("id") Long id){
        return ResponseEntity.ok(anamneseService.activate(id));
    }


}
