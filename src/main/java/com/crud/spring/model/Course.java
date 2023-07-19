package com.crud.spring.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.Length;

@Data
@Entity
@SQLDelete(sql = "UPDATE Course SET status = 'Inativo' WHERE id = ?")
@Where(clause = "status = 'Ativo'")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("_id")
    private Long id;

    @NotBlank
    @NotNull(message = "The name cannot be null")
    @Length(min = 5, max = 100, message = "The name must be between 5 and 100 characters")
    @Column(length = 100, nullable = false)
    private String name;

    @NotNull
    @Length(max = 10, message = "The code must be a maximum of 10 characters")
    @Pattern(regexp = "back-end|front-end|mobile|dev-ops")
    @Column(length = 15, nullable = false)
    private String category;

    @NotNull
    @Length(max = 10, message = "The code must be a maximum of 10 characters")
    @Pattern(regexp = "Ativo|Inativo")
    @Column(length = 10, nullable = false)
    private String status = "Ativo";

}
