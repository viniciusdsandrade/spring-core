package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidadorHorarioAntecedencia implements ValidadorAgendamentoDeConsulta {

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        LocalDateTime dataConsulta = dados.dataHora();
        LocalDateTime dataHoraAtual = LocalDateTime.now();
        int diferencaEmMinutos = (int) Duration.between(dataHoraAtual, dataConsulta).toMinutes();

        if (diferencaEmMinutos < 30)
            throw new ValidacaoException("A consulta deve ser agendada com pelo menos 30 minutos de antecedência");

    }
}
