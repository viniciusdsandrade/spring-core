package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidadorPacienteSemOutraConsultaNoDia implements ValidadorAgendamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidadorPacienteSemOutraConsultaNoDia(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime primeiroHorario = dados.dataHora().withHour(7);
        LocalDateTime ultimoHorario = dados.dataHora().withHour(18);

        boolean existeConsultaNoMesmoDia = consultaRepository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario);

        if (existeConsultaNoMesmoDia)
            throw new ValidacaoException("Paciente já possui consulta agendada no mesmo dia");


    }
}
