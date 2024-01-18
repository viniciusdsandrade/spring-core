package med.voll.api.validacoes;

import med.voll.api.dto.consulta.DadosAgendamentoConsulta;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.stereotype.Component;

@Component
public class ValidadorMedicoAtivo implements ValidadorAgendamentoDeConsulta {

    private final MedicoRepository medicoRepository;

    public ValidadorMedicoAtivo(MedicoRepository medicoRepository) {
        this.medicoRepository = medicoRepository;
    }

    @Override
    public void validar(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() == null) return;

        boolean isMedicoAtivo = medicoRepository.findAtivoById(dados.idMedico());

        if (!isMedicoAtivo)
            throw new ValidacaoException("Médico não está ativo");
    }
}