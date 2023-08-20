package com.crud.spring;

import com.crud.spring.enums.Category;
import com.crud.spring.model.Course;
import com.crud.spring.model.Lesson;
import com.crud.spring.repository.CourseRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner initDatabase(CourseRepository courseRepository){
		return args -> {
			courseRepository.deleteAll();
			Course course = new Course();
			course.setName("Angular");
			course.setCategory(Category.FRONTEND);

			Lesson lesson = new Lesson();
			lesson.setName("Introdução ao Angular");
			lesson.setUrlVideo("https://youtu.be/tPOMG0D57S0");
			lesson.setCourse(course);

			course.getLessons().add(lesson);

			courseRepository.save(course);
		};
	}
}
