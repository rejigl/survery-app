package com.rejigl.app.controller;

import com.rejigl.app.model.Question;
import com.rejigl.app.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class SurveyController {

    @Autowired
    private SurveyService service;

    @GetMapping("/surveys/{surveyId}/questions")
    public List<Question> getQuestionsBySurvey(@PathVariable String surveyId){
        return service.getAllQuestionsBySurveyId(surveyId);
    }

    @PostMapping("/surveys/{surveyId}/questions")
    public ResponseEntity<Void> addQuestionToSurvey(@PathVariable String surveyId, @RequestBody Question question){

        Question q = service.addQuestion(surveyId, question);

        if(q == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(q.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question updateQuestionInSurvey(@PathVariable String surveyId, @PathVariable String questionId){
        Question question = service.getQuestionBySurveyIdAndByQuestionId(surveyId, questionId).get();
        return service.addQuestion(surveyId, question);
    }

    @GetMapping("/surveys/{surveyId}/questions/{questionId}")
    public Question getQuestionBySurveyIdAndQuestionId(@PathVariable String surveyId, @PathVariable String questionId){
        return service.getQuestionBySurveyIdAndByQuestionId(surveyId, questionId).get();
    }

}
