package med.voll.api.dto.endereco;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DadosEndereco(
        @NotBlank
        String logradouro,

        @NotBlank
        String bairro,

        @NotBlank
        String cidade,

        @NotBlank
        @Pattern(regexp = "\\d{5}-\\d{3}")
        String cep,

        @NotBlank
        String uf,
        
        String complemento,
        
        String numero
) {

}