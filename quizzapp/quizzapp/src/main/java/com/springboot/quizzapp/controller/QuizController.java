package com.springboot.quizzapp.controller;

import com.springboot.quizzapp.model.QuestionWrapper;
import com.springboot.quizzapp.model.QuizResponse;
import com.springboot.quizzapp.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {
    @Autowired
    QuizService quizzService;
 @PostMapping("create")
 public ResponseEntity<String> createQuizz(@RequestParam String category, @RequestParam int numOfQuestions,
 @RequestParam String title)
{
    System.out.println(category);
    return quizzService.createQuiz(category,numOfQuestions,title);
}

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(@PathVariable Integer id)
    {
     return quizzService.getQuizQuestionsById(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> getQuizResultById(@PathVariable Integer id, @RequestBody List<QuizResponse> quizReponses)
    {
        return quizzService.getQuizResultById(id,quizReponses);
    }

}
