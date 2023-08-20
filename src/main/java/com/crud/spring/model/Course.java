package com.crud.spring.model;

import com.crud.spring.enums.Category;
import com.crud.spring.enums.Status;
import com.crud.spring.enums.converter.CategoryConverter;
import com.crud.spring.enums.converter.StatusConverter;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @Column(length = 15, nullable = false)
    @Convert(converter = CategoryConverter.class)
    private Category category;

    @NotNull
    @Column(length = 10, nullable = false)
    @Convert(converter = StatusConverter.class)
    private Status status = Status.ACTIVE;

}
