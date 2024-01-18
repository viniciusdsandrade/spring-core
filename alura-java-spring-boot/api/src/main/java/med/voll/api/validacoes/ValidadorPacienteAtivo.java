package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorPacienteAtivo implements ValidadorAgendamentoDeConsulta {

    private final PacienteRepository pacienteRepository;

    public ValidadorPacienteAtivo(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        boolean isPacienteAtivo = pacienteRepository.findAtivoById(dados.idPaciente());

        if (!isPacienteAtivo)
            throw new ValidacaoException("Paciente não está ativo");
    }
}
