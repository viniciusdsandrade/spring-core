package com.restful.studentmanagement.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "users",
        schema = "db_student_management"
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "first_name",
            nullable = false,
            columnDefinition = "VARCHAR(30)"
    )
    private String firstName;
    
    @Column(
            name = "last_name",
            nullable = false,
            columnDefinition = "VARCHAR(80)"
    )
    private String lastName;
    
    @Email
    @Column(
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String email;
}
