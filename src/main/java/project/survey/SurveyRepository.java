package project.survey;

import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Date;
public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    List<Survey> findBySurveyId(Integer surveyId);
    List<Survey> findByIsOpenTrue();
    List<Survey> findByIsOpenTrueAndIsAnonymousTrue();
    List<Survey> findByIsOpenTrueAndExpirationDateAfterOrExpirationDateIsNull(Date currentDate);
    List<Survey> findByCreatorId(Integer creatorId);
}