package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        boolean domingo = dados.dataHora().getDayOfWeek().getValue() == 7;
        boolean antesDaAbertura = dados.dataHora().getHour() < 7;
        boolean depoisDoFechamento = dados.dataHora().getHour() > 18;

        if (domingo || antesDaAbertura || depoisDoFechamento)
            throw new ValidacaoException("Horário fora do expediente da clínica");

    }
}
