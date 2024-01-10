package med.voll.api.entity.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;

import java.time.Duration;

public class ValidadorHorarioAntecedencia {

    public void validar(DadosAgendamentoConsulta dados) {
        var dataConsulta = dados.dataHora();
        var horaAtual = dataConsulta.toLocalTime();
        var diferencaMinutos = Duration.between(horaAtual, dataConsulta.toLocalTime()).toMinutes();

        if (diferencaMinutos < 30)
            throw new ValidacaoException("A consulta deve ser agendada com no mínimo 30 minutos de antecedência");
    }
}
