package com.springboot.quizzapp.controller;

import com.springboot.quizzapp.model.Question;
import com.springboot.quizzapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("questions")

public class QuestionController {
    @Autowired
    QuestionService questionService;
    @GetMapping("getAll")
public  ResponseEntity<List<Question>> getAllQuestions()
{
return questionService.getAllQuestions();
}

@GetMapping("category/{category}")
    public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category)
{
    return  questionService.getQuestionsByCategory(category);
}

    @PostMapping("add")
    public ResponseEntity<String> getQuestionsByCategory(@RequestBody Question question)
    {
        return questionService.addQuestion(question);
    }

    @GetMapping("deleteById/{id}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer id)
    {
        return questionService.deleteQuestionById( id);
    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<String> updateQuestionById(@PathVariable Integer id,@RequestBody Question question)
    {
        return questionService.updateQuestionById(id,question);
    }
}
