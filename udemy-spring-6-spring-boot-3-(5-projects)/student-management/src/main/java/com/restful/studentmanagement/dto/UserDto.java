package com.restful.studentmanagement.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Setter
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @Schema(description = "ID of the user")
    @EqualsAndHashCode.Include
    private Long id;

    @Schema(
            description = "First name of the user"
    )
    @NotEmpty(message = "First name cannot be empty")
    private String firstName;

    @Schema(
            description = "Last name of the user"
    )
    @NotEmpty(message = "Last name cannot be empty")
    private String lastName;

    @Schema(
            description = "Email of the user"
    )
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
}