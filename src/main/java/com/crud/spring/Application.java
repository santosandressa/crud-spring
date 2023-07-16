package com.crud.spring;

import com.crud.spring.model.Course;
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
			course.setCategory("front-end");

			courseRepository.save(course);
		};
	}
}
