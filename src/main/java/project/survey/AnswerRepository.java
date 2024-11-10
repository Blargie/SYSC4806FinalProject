package project.survey;

public interface AnswerRepository extends CrudRepository<Answer, Integer> {
    List<Answer> findBySurveyId(Integer surveyId);
}