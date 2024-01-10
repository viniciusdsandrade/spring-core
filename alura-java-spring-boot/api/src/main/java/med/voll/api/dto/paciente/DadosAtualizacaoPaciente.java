package med.voll.api.dto.paciente;

import jakarta.validation.constraints.NotNull;
import med.voll.api.dto.endereco.DadosEndereco;
import med.voll.api.entity.Endereco;
import med.voll.api.entity.Paciente;

public record DadosAtualizacaoPaciente(
        @NotNull
        Long id,
        String nome,
        String telefone,
        Endereco endereco
) {
}