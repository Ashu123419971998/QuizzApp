package com.springboot.quizzapp.service;

import com.springboot.quizzapp.dao.QuestionDao;
import com.springboot.quizzapp.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionDao questionDao;
    public ResponseEntity<List<Question>> getAllQuestions()
    {
        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public  ResponseEntity<List<Question>> getQuestionsByCategory( String category)
    {
        try {
            return new ResponseEntity<>(questionDao.findByCategory(category), HttpStatus.OK);
        }
         catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion( Question question)
    {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("Success",HttpStatus.CREATED);
        }
         catch(Exception e)
            {
                e.printStackTrace();
            }
            return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> deleteQuestionById( Integer id)
    {
        try {
        questionDao.deleteById(id);
        return  new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);


    }

    public ResponseEntity<String> updateQuestionById(Integer id, Question question)
    {
        try {
        Question temp_question=questionDao.getReferenceById(id);

        temp_question.setId(id);
        temp_question.setQuestion_title(question.getQuestion_title());
        temp_question.setOption1(question.getOption1());
        temp_question.setOption2(question.getOption2());
        temp_question.setOption3(question.getOption3());
        temp_question.setOption4(question.getOption4());
        temp_question.setAnswer(question.getAnswer());
        temp_question.setCategory(question.getCategory());
        temp_question.setDifficulty_level(question.getDifficulty_level());

        questionDao.save(question);
        return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return new ResponseEntity<>("",HttpStatus.BAD_REQUEST);
    }
}
