package project.survey;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Date;
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    List<Survey> findBySurveyId(int surveyId);
    List<Survey> findByIsOpenTrue();
    List<Survey> findByIsOpenTrueAndExpirationDateAfterOrExpirationDateIsNull(Date currentDate);
}