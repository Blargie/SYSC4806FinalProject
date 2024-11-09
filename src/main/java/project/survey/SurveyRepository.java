package project.survey;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface SurveyRepository extends CrudRepository<Survey, Integer> {
    List<Survey> findSurveyById(int id);
    //ToDo Milestone2: Implement other query methods
}
