package med.voll.api.repository;

import med.voll.api.entity.Especialidade;
import med.voll.api.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT * FROM tb_medicos m " +
            "WHERE m.especialidade = :especialidade " +
            "AND m.ativo = TRUE  " +
            "AND m.id NOT IN ( " +
            "    SELECT c.medico_id FROM tb_consulta c " +
            "    WHERE c.data = :localDateTime " +
            ") " +
            "ORDER BY RAND() " +
            "LIMIT 1", nativeQuery = true)
    Optional<Medico> findFirstByEspecialidadeAndHorarioDisponivel(
            @Param("especialidade") Especialidade especialidade,
            @Param("localDateTime") LocalDateTime localDateTime
    );

    @Query(value = "SELECT m.ativo " +
            "FROM tb_medicos m " +
            "WHERE m.id = :idMedico", nativeQuery = true)
    Boolean findAtivoById(Long idMedico);
}