package project.question;
import project.question.Question;

import org.springframework.data.repository.CrudRepository;
import project.survey.Survey;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question, Integer> {

}