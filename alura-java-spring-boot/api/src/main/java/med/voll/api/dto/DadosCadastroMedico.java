package med.voll.api.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.entity.Especialidade;

public record DadosCadastroMedico(

        @NotBlank
        String nome,

        @Email
        @NotBlank
        String email,

        @NotBlank
        @Pattern(regexp = "^\\(\\d{2}\\)\\s9\\d{4,5}-\\d{4}$")
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        Especialidade especialidade,

        @NotNull
        @Valid
        DadosEndereco endereco
) {
}
