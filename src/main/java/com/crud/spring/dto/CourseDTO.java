package com.crud.spring.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.util.List;

public record CourseDTO(
        @JsonProperty("_id") Long id,
        @NotBlank
        @NotNull(message = "The name cannot be null")
        @Length(min = 5, max = 100, message = "The name must be between 5 and 100 characters") String name,
        @NotNull
        @Length(max = 10, message = "The code must be a maximum of 10 characters")
        @Pattern(regexp = "back-end|front-end|mobile|dev-ops") String category ,
        List<LessonDTO> lessons){


}
