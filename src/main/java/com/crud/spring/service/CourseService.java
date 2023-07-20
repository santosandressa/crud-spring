package com.crud.spring.service;

import com.crud.spring.exception.RecordNotFoundException;
import com.crud.spring.model.Course;
import com.crud.spring.repository.CourseRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> list() {
        return courseRepository.findAll();
    }

    public Course findById(@Positive @NotNull Long id) {
        return courseRepository.findById(id).orElseThrow(
                () -> new RecordNotFoundException(id)
        );
    }

    public Course create(@Valid Course course) {
        return courseRepository.save(course);
    }

    public Course update(@Positive @NotNull Long id, @Valid Course course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.getName());
                    courseFound.setCategory(course.getCategory());
                    return courseRepository.save(courseFound);
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@Positive @NotNull Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
