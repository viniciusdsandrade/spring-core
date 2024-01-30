package com.restful.api.dto.consulta;

import com.restful.api.entity.MotivoCancelamento;
import jakarta.validation.constraints.NotNull;

public record DadosCancelamentoConsulta(
        Long idConsulta,

        @NotNull
        MotivoCancelamento motivoCancelamento
) {
}
