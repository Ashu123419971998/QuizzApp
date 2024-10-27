package com.springboot.quizzapp.service;

import com.springboot.quizzapp.dao.QuestionDao;
import com.springboot.quizzapp.dao.QuizDao;
import com.springboot.quizzapp.model.Question;
import com.springboot.quizzapp.model.QuestionWrapper;
import com.springboot.quizzapp.model.Quiz;
import com.springboot.quizzapp.model.QuizResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;
    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<String> createQuiz(String category, int numOfQuestions,String title)
    {
        try {
            List<Question> questions = questionDao.findRandomQuestionsByCategory(category, numOfQuestions);
            System.out.println(questions);
            Quiz quiz = new Quiz();
            quiz.setTitle(title);
            quiz.setQuestions(questions);
            quizDao.save(quiz);
            return new ResponseEntity("Created", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestionsById(Integer id)
    {
        try {

            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            List<QuestionWrapper> questionForUser = new ArrayList<>();
            for (Question question : questions) {
                QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getQuestion_title(), question.getOption1(),
                        question.getOption2(), question.getOption3(), question.getOption4());
                questionForUser.add(wrapper);
            }
            return new ResponseEntity(questionForUser, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<Integer> getQuizResultById(Integer id,List<QuizResponse> QuizResponse)
    {
        try {
            Optional<Quiz> quiz = quizDao.findById(id);
            List<Question> questions = quiz.get().getQuestions();
            Integer result = 0;
            HashMap<Integer, String> questionWithAnswer = new HashMap<>();
            for (Question question : questions) {
                questionWithAnswer.put(question.getId(), question.getAnswer());
            }
            for (QuizResponse quizResponse : QuizResponse) {
                if (questionWithAnswer.get(quizResponse.getId()) != null &&
                        questionWithAnswer.get(quizResponse.getId()).equals(quizResponse.getAnswer())) {
                    result++;
                }
            }

            return new ResponseEntity(result, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(0,HttpStatus.BAD_REQUEST);

    }
}
