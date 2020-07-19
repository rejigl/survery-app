package com.rejigl.app.service;

import com.rejigl.app.model.Question;
import com.rejigl.app.model.Survey;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SurveyService {

    private static final List<Survey> surveys = new ArrayList<>();

    static{
        Question q1= new Question(
                "Q1","Largest Country in the World?","Russia",
                Arrays.asList("India", "Russia", "USA", "China"));
        Question q2= new Question(
                "Q2","Most Populus country in the World?","China",
                Arrays.asList("India", "Russia", "USA", "China"));
        Question q3= new Question(
                "Q3","Second largest English speaking country","India",
                Arrays.asList("India", "Russia", "USA", "China"));
        Question q4= new Question(
                "Q4","Country with most Gold in Olympics","USA",
                Arrays.asList("India", "Russia", "USA", "China"));

        List<Question> questions = new ArrayList<>(Arrays.asList(q1, q2, q3, q4));

        Survey survey = new Survey("s1", "General Knowledge", "Test your GK", questions);

        surveys.add(survey);
    }

    public List<Survey> getAllSurveys() {
        return surveys;
    }

    public Optional<Survey> getSurveyById(String id){
        for(Survey survey: surveys){
            if(survey.getId().equals(id))
                return Optional.of(survey);
        }
        return Optional.empty();
    }

    public List<Question> getAllQuestionsBySurveyId(String surveyId){
        Optional<Survey> survey = getSurveyById(surveyId);

        return survey.map(Survey::getQuestions).orElse(null);

    }

    public Optional<Question> getQuestionBySurveyIdAndByQuestionId(String surveyId, String questionId){
        List<Question> questions = getAllQuestionsBySurveyId(surveyId);

        for(Question question : questions){
            if(question.getId().equals(questionId)){
                return Optional.of(question);
            }
        }

        return Optional.empty();
    }

    private final SecureRandom random = new SecureRandom();

    public Question addQuestion(String surveyId, Question question) {
        Optional<Survey> survey = getSurveyById(surveyId);

        String randomId = new BigInteger(130, random).toString(32);
        question.setId(randomId);

        survey.ifPresent(value -> value.getQuestions().add(question));

        return question;
    }
}
