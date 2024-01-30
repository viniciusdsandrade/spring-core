package com.restful.api.repository;

import com.restful.api.dto.endereco.DadosEndereco;
import com.restful.api.dto.paciente.DadosDetalhamentoPaciente;
import com.restful.api.entity.Endereco;
import com.restful.api.entity.Paciente;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class PacienteRepositoryTest {

    @Autowired
    private PacienteRepository pacienteRepository;

    @BeforeEach
    void setUp() {
        pacienteRepository.deleteAll();
    }


    @Test
    @DisplayName("Deve salvar um paciente")
    void givenPaciente_whenSave_thenReturnSavedPaciente() {
        // Given
        DadosEndereco dadosEndereco = new DadosEndereco(
                "Rua dos Bobos",
                "Jardim das Flores",
                "13480000",
                "Limeira",
                "SP",
                "Casa",
                "123"
        );

        Paciente paciente = Paciente.builder()
                .nome("Vinícius dos Santos Andrade")
                .email("vinicius_andrade2010@hotmail.com")
                .telefone("19974133884")
                .cpf("44784160876")
                .endereco(new Endereco(dadosEndereco))
                .build();

        // When
        Paciente savedPaciente = pacienteRepository.save(paciente);

        // Then
        Assertions.assertThat(savedPaciente).isNotNull();
        Assertions.assertThat(savedPaciente.getId()).isNotNull();

        // Additional assertions for equality
        Assertions.assertThat(savedPaciente.getNome()).isEqualTo(paciente.getNome());
        Assertions.assertThat(savedPaciente.getEmail()).isEqualTo(paciente.getEmail());
        Assertions.assertThat(savedPaciente.getTelefone()).isEqualTo(paciente.getTelefone());
        Assertions.assertThat(savedPaciente.getCpf()).isEqualTo(paciente.getCpf());
        Assertions.assertThat(savedPaciente.getEndereco()).isEqualTo(paciente.getEndereco());

        //imprimir
        System.out.println("Paciente: " + savedPaciente);
    }

    @Test
    @DisplayName("Deve salvar vários pacientes")
    void givenMultiplePacientes_whenSave_thenAllPacientesSaved() {
        //Given

        List<Paciente> pacientes = List.of(
                Paciente.builder()
                        .nome("Vinícius dos Santos Andrade")
                        .email("")
                        .build());

        //When
        List<Paciente> savedPacientes = pacienteRepository.saveAll(pacientes);

        //Then
        Assertions.assertThat(savedPacientes).isNotEmpty();
        savedPacientes.forEach(paciente -> {
            Assertions.assertThat(paciente.getId()).isNotNull();
            Assertions.assertThat(paciente.getNome()).isNotNull();
        });
    }

    @Test
    @DisplayName("Deve encontrar um paciente pelo nome")
    void givenPacienteName_whenFindByName_thenReturnPaciente() {
    }

    @Test
    @DisplayName("Deve encontrar um paciente pelo ID")
    void givenPacienteId_whenFindById_thenReturnPaciente() {
    }

    @Test
    @DisplayName("Deve excluir um paciente")
    void givenPacienteId_whenDelete_thenPacienteNotExists() {
    }

    @Test
    @DisplayName("Deve recuperar todos os pacientes salvos")
    void givenSavedPacientes_whenFindAll_thenReturnAllPacientes() {
        //Given
        List<Paciente> pacientes = List.of(
                Paciente.builder()
                        .nome("Vinícius dos Santos Andrade")
                        .email("")
                        .build());

        //When
        pacienteRepository.saveAll(pacientes);

        //Then
        Assertions.assertThat(pacienteRepository.findAll()).isNotEmpty();
    }

    @Test
    @DisplayName("Deve excluir todos os pacientes salvos")
    void givenSavedPacientes_whenDeleteAll_thenNoPacientesLeft() {
        //Given
        List<Paciente> pacientes = List.of(
                Paciente.builder()
                        .nome("Vinícius dos Santos Andrade")
                        .email("")
                        .build());

        //When
        pacienteRepository.saveAll(pacientes);
        pacienteRepository.deleteAll();

        //Then
        Assertions.assertThat(pacienteRepository.findAll()).isEmpty();
    }
}