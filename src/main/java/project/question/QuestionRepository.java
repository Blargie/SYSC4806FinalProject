package project.question;
import org.springframework.data.jpa.repository.Query;
import project.question.Question;

import org.springframework.data.repository.CrudRepository;
import project.survey.Survey;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {
    @Query("SELECT q FROM Question q WHERE q.survey.surveyId = :surveyId")
    List<Question> findBySurveyId(Integer surveyId);
}