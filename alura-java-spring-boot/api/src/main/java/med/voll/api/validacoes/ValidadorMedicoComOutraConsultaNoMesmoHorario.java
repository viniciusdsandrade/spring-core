package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoComOutraConsultaNoMesmoHorario implements ValidadorAgendamentoDeConsulta {

    private final ConsultaRepository consultaRepository;

    public ValidadorMedicoComOutraConsultaNoMesmoHorario(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) return;

        boolean existeConsultaNoMesmoHorario = consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.dataHora());

        if (existeConsultaNoMesmoHorario)
            throw new ValidacaoException("Médico já possui consulta agendada no mesmo horário");
    }
}