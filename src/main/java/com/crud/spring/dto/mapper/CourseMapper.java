package com.crud.spring.dto.mapper;

import com.crud.spring.dto.CourseDTO;
import com.crud.spring.enums.Category;
import com.crud.spring.model.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public CourseDTO toDTO(Course course) {
        if (course == null) {
            return null;
        }
        return new CourseDTO(course.getId(), course.getName(), course.getCategory().getValue());
    }

    public Course toEntity(CourseDTO courseDTO) {
        if (courseDTO == null) {
            return null;
        }
        Course course = new Course();
        if (courseDTO.id() != null) {
            course.setId(courseDTO.id());
        }
        course.setName(courseDTO.name());
        course.setCategory(convertCategoryValue(courseDTO.category()));
        return course;
    }

    public Category convertCategoryValue(String value) {
        if (value == null) {
            return null;
        }

        return switch (value) {
            case "front-end" -> Category.FRONTEND;
            case "back-end" -> Category.BACKEND;
            case "mobile" -> Category.MOBILE;
            case "dev-ops" -> Category.DEVOPS;
            default -> throw new IllegalArgumentException("Invalid category value: " + value);
        };
    }

}
