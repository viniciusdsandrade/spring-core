package com.restful.api.dto.consulta;

import com.restful.api.entity.Paciente;

public record PacienteDetalhes(
        Long id,
        String nome
) {
    public PacienteDetalhes(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome()
        );
    }
}
