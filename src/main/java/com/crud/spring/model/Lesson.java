package com.crud.spring.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(nullable = false)
    private String urlVideo;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

}
