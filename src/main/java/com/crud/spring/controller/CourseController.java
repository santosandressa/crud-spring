package com.crud.spring.controller;

import com.crud.spring.dto.CourseDTO;
import com.crud.spring.service.CourseService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {


    private final CourseService courseService;

    public CourseController(CourseService courseService) {

        this.courseService = courseService;
    }

    @GetMapping
    public @ResponseBody List<CourseDTO> list() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public CourseDTO findById(@PathVariable("id") @Positive @NotNull Long id) {
        return courseService.findById(id);
    }

    @PostMapping
    public ResponseEntity<CourseDTO> create(@RequestBody @Valid CourseDTO course) {
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.create(course));
    }

    @PutMapping("/{id}")
    public CourseDTO update(@PathVariable("id") @Positive @NotNull Long id, @RequestBody @Valid CourseDTO course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") @Positive @NotNull Long id) {
        courseService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
