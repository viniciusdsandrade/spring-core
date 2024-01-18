package med.voll.api.repository;

import med.voll.api.entity.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);

    @Query(value = "SELECT m.ativo " +
            "FROM tb_pacientes m " +
            "WHERE m.id = :idPaciente", nativeQuery = true)
    Boolean findAtivoById(Long idPaciente);
}