package com.orbitech.npvet.ControllerTest;

import com.orbitech.npvet.controller.AnamneseController;
import com.orbitech.npvet.dto.*;
import com.orbitech.npvet.entity.*;
import com.orbitech.npvet.repository.AnamneseHistoricoRepository;
import com.orbitech.npvet.repository.AnamnesePerguntaRepository;
import com.orbitech.npvet.repository.AnamneseRepository;
import com.orbitech.npvet.service.AnamneseService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class AnamneseControllerTest {

    @MockBean
    private AnamneseRepository anamneseRepository;

    @MockBean
    private AnamneseHistoricoRepository anamneseHistoricoRepository;

    @MockBean
    private AnamnesePerguntaRepository anamnesePerguntaRepository;

    @Autowired
    private AnamneseService anamneseService;

    @Autowired
    private AnamneseController anamneseController;

    @Autowired
    private ModelMapper modelMapper;

    AnimalDTO animalDTO = new AnimalDTO();
    Animal animal = new Animal();
    TutorDTO tutorDTO = new TutorDTO();
    Tutor tutor = new Tutor();
    UsuarioDTO veterinarioDTO = new UsuarioDTO();
    Usuario veterinario = new Usuario();
    AnamneseHistoricoDTO anamneseHistoricoDTO = new AnamneseHistoricoDTO();
    AnamneseHistorico anamneseHistorico = new AnamneseHistorico();
    AnamneseDTO anamneseDTO = new AnamneseDTO();
    Anamnese anamnese = new Anamnese();
    AnamnesePergunta anamnesePergunta = new AnamnesePergunta();
    AnamnesePerguntaDTO anamnesePerguntaDTO = new AnamnesePerguntaDTO();
    List<AnamneseDTO> anamneseDTOList = new ArrayList<>();
    List<Anamnese> anamneseList = new ArrayList<>();
    List<AnamneseHistoricoDTO> anamneseHistoricoDTOs = new ArrayList<>();
    List<AnamneseHistorico> anamneseHistoricos = new ArrayList<>();

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);

        // Animal DTO e Entity
        animalDTO.setId(1L);
        animalDTO.setNome("Buddy");

        animal.setId(1L);
        animal.setNome("Buddy");

        // Tutor DTO e Entity
        tutorDTO.setId(1L);
        tutorDTO.setCpf("123");

        tutor.setId(1L);
        tutor.setCpf("123");

        // Veterinario DTO e Entity
        veterinarioDTO.setId(1L);
        veterinarioDTO.setNome("Dr. Smith");

        veterinario.setId(1L);
        veterinario.setNome("Dr. Smith");

        // AnamneseHistorico DTO e Entity
        anamneseHistoricoDTO.setId(1L);
//        anamneseHistoricoDTO.setAnamnese(anamneseDTO);
        anamneseHistoricoDTO.setProgressoMedico("Medical Progress Sample One.");

        anamneseHistorico.setId(1L);
//        anamneseHistorico.setAnamnese(anamnese);
        anamneseHistorico.setProgressoMedico("Medical Progress Sample One.");

        // Setting AnamneseHistorico Lists
        anamneseHistoricoDTOs.add(anamneseHistoricoDTO);
        anamneseHistoricos.add(anamneseHistorico);

        // Creating the anamneses
        anamneseDTO.setId(1L);
        anamneseDTO.setAnimalDTO(animalDTO);
        anamneseDTO.setTutorDTO(tutorDTO);
        anamneseDTO.setVeterinarioDTO(veterinarioDTO);
        anamneseDTO.setHistoricoProgressoMedico(anamneseHistoricoDTOs);

        anamnese.setId(1L);
        anamnese.setAnimal(animal);
        anamnese.setTutor(tutor);
        anamnese.setVeterinario(veterinario);
        anamnese.setHistoricoProgressoMedico(anamneseHistoricos);

        // Setting Values of Anamnese List
        anamneseDTOList.add(anamneseDTO);
        anamneseList.add(anamnese);

        when(anamneseRepository.findById(1L)).thenReturn(Optional.of(anamnese));
        when(anamneseRepository.findAll()).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpf("123")).thenReturn(anamneseList);
        when(anamneseRepository.findByTutorCpfAndAnimal("123","Buddy")).thenReturn(anamneseList);
        when(anamneseRepository.save(Mockito.any(Anamnese.class))).thenReturn(anamnese);
        when(anamneseHistoricoRepository.save(Mockito.any(AnamneseHistorico.class))).thenReturn(anamneseHistorico);
    }

    @Test
    void getByIdTest(){
        ResponseEntity<AnamneseDTO> response = anamneseController.getById(1L);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getAllTest(){
        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getAll();
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    void getByTutorCpfTest() {
        ResponseEntity<List<AnamneseDTO>> response = anamneseController.getByTutorCpf("123");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnamneseDTO> responseBody = response.getBody();
        System.out.println(responseBody);
        assertEquals(1, responseBody.size());
    }


    @Test
    void getByTutorCpfAndAnimal(){
        ResponseEntity<List<AnamneseDTO>> response =
                anamneseController.getByTutorCpfAndAnimal("123", "Buddy");
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        List<AnamneseDTO> responseBody = response.getBody();
    }

    @Test
    void createTest(){
        ResponseEntity<AnamneseDTO> response = anamneseController.create(anamneseDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    }

//    @Test
//    @DisplayName("Teste de adição de Perguntas e Respostas à Anamnese")
//    void addQuestionAnswerToAnamneseTest(){
//        anamnesePerguntaDTO.setAnamneseDTO(anamneseDTO);
//        anamnesePerguntaDTO.setPerguntaDTO(new PerguntaDTO());
//        anamnesePerguntaDTO.setResposta("resposta sample");

////        ResponseEntity<AnamnesePerguntaDTO> response =
//////                anamneseController.addQuestionAnswerToAnamnese(1L,anamnesePerguntaDTO);
////
////        assertNotNull(response);
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

//    }

//    @Test
//    @DisplayName("Teste de adição de Progresso Médico")
//    void addProgressoMedicoTest(){
//        ResponseEntity<AnamneseHistoricoDTO> response =
//                anamneseController.addProgressoMedico(1L,anamneseHistoricoDTO);
//
//        assertNotNull(response);
//        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
//    }

    @Test
    void updateTest(){
        anamnese.setId(1L);
        anamneseDTO.setId(1L);
        ResponseEntity<AnamneseDTO> response = anamneseController.update(1L,anamneseDTO);
        assertNotNull(response);
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        assertThat(anamneseDTO).usingRecursiveComparison().isEqualTo(response.getBody());
    }

//    @Test
//    void deleteTest(){
//        ResponseEntity<String> controllerResponse = anamneseController.delete(1L);
//        assertEquals(HttpStatus.OK, controllerResponse.getStatusCode());
//        assertEquals("Anamnese excluída com sucesso!", controllerResponse.getBody());
//    }

}
