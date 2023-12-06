package com.restful.todomanagement.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "todos")
@Table(
        name = "Todo",
        schema = "db_todo_management"
)
public class Todo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "todo-title",
            nullable = false,
            columnDefinition = "VARCHAR(50)"
    )
    private String title;

    @Column(
            name = "todo-description",
            nullable = false,
            columnDefinition = "VARCHAR(255)"
    )
    private String description;

    @Column(
            name = "todo-completed",
            nullable = false, 
            columnDefinition = "BOOLEAN DEFAULT FALSE"
    )
    private Boolean completed;
}
