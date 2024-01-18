package med.voll.api.repository;

import med.voll.api.entity.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByMedicoIdAndData(Long idMedico, LocalDateTime dateTime);

    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime startTime, LocalDateTime endTime);
}