package com.crud.spring.service;

import com.crud.spring.dto.CourseDTO;
import com.crud.spring.dto.mapper.CourseMapper;
import com.crud.spring.exception.RecordNotFoundException;
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
    private final CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }

    public List<CourseDTO> list() {
                return courseRepository.findAll().stream()
                .map(courseMapper::toDTO)
                .toList();
    }

    public CourseDTO findById(@Positive @NotNull Long id) {
        return courseRepository.findById(id)
                .map(courseMapper::toDTO)
                .orElseThrow(() -> new RecordNotFoundException(id));
    }

    public CourseDTO create(@Valid @NotNull CourseDTO course) {
        return courseMapper.toDTO(courseRepository.save(courseMapper.toEntity(course)));
    }

    public CourseDTO update(@Positive @NotNull Long id, @Valid @NotNull CourseDTO course) {
        return courseRepository.findById(id)
                .map(courseFound -> {
                    courseFound.setName(course.name());
                    courseFound.setCategory(course.category());
                    return courseMapper.toDTO(courseRepository.save(courseFound));
                }).orElseThrow(() -> new RecordNotFoundException(id));
    }

    public void delete(@Positive @NotNull Long id) {
        courseRepository.delete(courseRepository.findById(id).orElseThrow(() -> new RecordNotFoundException(id)));
    }
}
