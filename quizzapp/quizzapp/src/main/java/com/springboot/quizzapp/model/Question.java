package com.springboot.quizzapp.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "question")
@Entity
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String question_title;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String answer;
    private String difficulty_level;
    private String category;
}
