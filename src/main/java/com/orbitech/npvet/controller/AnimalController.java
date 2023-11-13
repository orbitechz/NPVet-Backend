package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.AnimalDTO;
import com.orbitech.npvet.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animal")
@CrossOrigin("http://localhost:4200")
public class AnimalController {

    @Autowired
    private AnimalService service;

    @GetMapping("/id/{id}")
    public ResponseEntity<AnimalDTO> getById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AnimalDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }


    @GetMapping("/nome/{nome}")
    public ResponseEntity<List<AnimalDTO>> getByNome(@PathVariable("nome") String nome) {
        return ResponseEntity.ok(service.getAllByNome(nome));
    }

    @GetMapping("/tutor/{tutorId}/nome/{nome}")
    public ResponseEntity<AnimalDTO> getAnimalByTutorAndName(
            @PathVariable("tutorId") Long tutorId,
            @PathVariable("nome") String nome
    ) {
        AnimalDTO animals = service.getByTutorAndName(tutorId, nome);
        return ResponseEntity.ok(animals);
    }


    @GetMapping("/tutor/{tutorId}")
    public ResponseEntity<List<AnimalDTO>> getAllAnimalsByTutor(@PathVariable("tutorId") Long tutorId) {
        List<AnimalDTO> animals = service.getAllByTutor(tutorId);
        return ResponseEntity.ok(animals);
    }


    @GetMapping("/raca/{raca}")
    public ResponseEntity<List<AnimalDTO>> getByRaca(@PathVariable("raca") String raca) {
        return ResponseEntity.ok(service.getAllByRaca(raca));
    }

    @GetMapping("/especie/{especie}")
    public ResponseEntity<List<AnimalDTO>> getByEspecie(@PathVariable("especie") String especie) {
        return ResponseEntity.ok(service.getAllByEspecie(especie));
    }
    @GetMapping("/tutor/get/{id}")
    public ResponseEntity<List<AnimalDTO>> getAllByTutorId(@PathVariable("id") Long id) {
        return ResponseEntity.ok(service.getAllByTutorId(id));
    }
    @GetMapping("/all/desativados")
    public ResponseEntity<List<AnimalDTO>> getAllDesativados(){
        return ResponseEntity.ok(service.getAllDesativado());
    }

    @GetMapping("/all/ativos")
    public ResponseEntity<List<AnimalDTO>> getAllAtivos(){
        return ResponseEntity.ok(service.getAllAtivo());
    }

    @PostMapping("/post")
    public ResponseEntity<AnimalDTO> create(@RequestBody @Validated AnimalDTO animalDTO){
        return ResponseEntity.ok(service.create(animalDTO));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<AnimalDTO> update(@PathVariable("id") Long id, @RequestBody @Validated AnimalDTO animalDTO){
        return ResponseEntity.ok(service.update(id, animalDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<AnimalDTO> delete(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.delete(id));
    }

    @PostMapping("/activate/{id}")
    public ResponseEntity<AnimalDTO> activate(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.activate(id));
    }

}
