package com.restful.api.repository;

import com.restful.api.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {


    boolean existsByPacienteIdAndDataHoraBetween(Long idPaciente, LocalDateTime dataHoraInicio, LocalDateTime dataHoraFim);

    boolean existsByMedicoIdAndDataHoraAndMotivoCancelamentoIsNull(Long idMedico, LocalDateTime dataHora);
}


