package com.orbitech.npvet.controller;

import com.orbitech.npvet.dto.ConsultaDTO;
import com.orbitech.npvet.entity.Status;
import com.orbitech.npvet.entity.Usuario;
import com.orbitech.npvet.service.ConsultaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/npvet-api/consulta")
@PreAuthorize("hasAuthority('ADMINISTRADOR')")
public class ConsultaController {
    @Autowired
    private ConsultaService service;

    @GetMapping("/report")
    public ResponseEntity<List<ConsultaDTO>> getFilteredConsultas(
            @RequestParam LocalDateTime startDate,
            @RequestParam LocalDateTime endDate,
            @RequestParam(required = false) Long animalId,
            @RequestParam(required = false) Status status
            ){
        return ResponseEntity.ok(service.getFilteredConsultas(startDate,
                endDate,
                animalId,
                status
                ));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ConsultaDTO>getById(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(service.getById(id));
    }
    @GetMapping("/all")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getAll(){
        return ResponseEntity.ok(service.getAll());
    }
    @PostMapping("/post")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ConsultaDTO>create(@AuthenticationPrincipal Usuario usuario, @RequestBody @Validated ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.create(consultaDTO, usuario));
    }
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<ConsultaDTO>update(@AuthenticationPrincipal Usuario usuario, @PathVariable("id") final long id,@RequestBody @Validated ConsultaDTO consultaDTO){
        return ResponseEntity.ok(service.update(id, consultaDTO, usuario));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('ADMINISTRADOR')")
    public ResponseEntity<String>delete(@AuthenticationPrincipal Usuario usuario, @PathVariable("id") final long id){
        service.delete(id, usuario);
        return ResponseEntity.ok(String.format("Consulta com id [%s] deletada com sucesso.",id));
    }

    @GetMapping("/animal/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getAnimalByName(@PathVariable("nome")String nome){
        return ResponseEntity.ok(service.getAnimalByName(nome));
    }

    @GetMapping("/animal/id/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getAnimalById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getAnimalById(id));
    }

    @GetMapping("/veterinario/nome/{nome}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getVeterinarioByNome(@PathVariable("nome")String nome){
        return ResponseEntity.ok(service.getVeterinarioByName(nome));
    }

    @GetMapping("/veterinario/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getVeterinarioById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getVeterinarioById(id));
    }

    @GetMapping("/anamnese/{id}")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getAnamneseById(@PathVariable("id")Long id){
        return ResponseEntity.ok(service.getAnamneseId(id));
    }
    @GetMapping("/em-andamento")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getConsultasEmAndamento(){
        List<ConsultaDTO>response = service.getConsultasEmAndamento();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/concluida")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getConsultasConcluida(){
        List<ConsultaDTO>response = service.getConsultasConcluida();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/cancelada")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getConsultasCancelada(){
        List<ConsultaDTO>response = service.getConsultasCancelada();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/agendada")
    @PreAuthorize("hasAnyAuthority('SECRETARIA', 'MEDICO', 'ADMINISTRADOR')")
    public ResponseEntity<List<ConsultaDTO>>getConsultasAgendadas() {
        List<ConsultaDTO> response = service.getConsultaAgendada();
        return ResponseEntity.ok(response);
    }

}
