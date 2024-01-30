package com.restful.api.repository;

import com.restful.api.entity.enums.Especialidade;
import com.restful.api.entity.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    boolean existsByEmail(String email);

    boolean existsByCrm(String crm);

    @Query("SELECT m FROM Medico m WHERE " +
            "m.especialidade = :especialidade AND " +
            "m.ativo = true AND " +
            "m.id NOT IN (" +
            "SELECT c.medico.id " +
            "FROM Consulta c " +
            "WHERE c.dataHora = :dataHora " +
            "AND c.medico.especialidade IS NULL) " +
            "ORDER BY RAND() " +
            "LIMIT 1")
    Medico findFirstByEspecialidadeAndAtivoAndIdNotIn(
            @Param("especialidade") Especialidade especialidade,
            @Param("dataHora") LocalDateTime dataHora
    );

    @Query("""
                SELECT m.ativo 
                FROM Medico m 
                WHERE m.id = :idMedico
            """)
    Boolean findAtivoById(Long idMedico);

}