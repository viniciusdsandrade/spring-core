package com.restful.sistema_de_computacao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Setter
@Getter
@Entity(name = "student")
@Table(name = "tb_student",
        schema = "db_students",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_cpf_unique", columnNames = "cpf"),
                @UniqueConstraint(name = "student_email_unique", columnNames = "email"),
                @UniqueConstraint(name = "student_ra_unique", columnNames = "ra")
        }
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @NotNull(message = "O primeiro nome é obrigatório")
    @Size(min = 2, max = 60, message = "O primeiro nome deve ter entre 2 e 60 caracteres")
    private String first_name;

    @Size(min = 2, max = 60, message = "O sobrenome deve ter entre 2 e 60 caracteres")
    private String last_name;

    @NotNull(message = "O CPF é obrigatório")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotNull(message = "O email é obrigatório")
    @Email(message = "Email inválido")
    private String email;

    @NotNull(message = "O RA é obrigatório")
    @Pattern(regexp = "^[1-9][0-9]{4}$", message = "RA deve ter exatamente 5 dígitos. O primeiro dígito deve ser de 1 a 9 e os outros 4 dígitos devem ser de 0 a 9.")
    private String ra;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve ser no passado")
    private LocalDate birth_date;
}
