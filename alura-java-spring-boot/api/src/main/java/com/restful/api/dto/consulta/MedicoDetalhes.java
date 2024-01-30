package com.restful.api.dto.consulta;

import com.restful.api.entity.Especialidade;
import com.restful.api.entity.Medico;

public record MedicoDetalhes(
        Long id,
        String nome,
        Especialidade especialidade
) {
    public MedicoDetalhes(Medico medico) {
        this(
                medico.getId(),
                medico.getNome(),
                medico.getEspecialidade()
        );
    }
}
