package project.question;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class QuestionService {
    //Fields
    private final RestTemplate restTemplate;

    @Value("${postgrest.api.baseurl}")
    private String apiBaseUrl;

    //Constructor
    public QuestionService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Question Methods
    public List<Question> getQuestions() {
        ResponseEntity<Question[]> response = restTemplate.getForEntity(apiBaseUrl + "/question", Question[].class);
        return Arrays.asList(response.getBody());
    }
    public Question createQuestion(Question question) {
        ResponseEntity<Question> response = restTemplate.postForEntity(apiBaseUrl + "/question", question, Question.class);
        return response.getBody();
    }

    //MultipleChoiceQuestion Methods
    public List<MultipleChoiceQuestion> getMultipleChoiceQuestions() {
        ResponseEntity<MultipleChoiceQuestion[]> response = restTemplate.getForEntity(apiBaseUrl + "/multiplechoicequestion", MultipleChoiceQuestion[].class);
        return Arrays.asList(response.getBody());
    }
    public MultipleChoiceQuestion createMultipleChoiceQuestion(MultipleChoiceQuestion multipleChoiceQuestion) {
        ResponseEntity<MultipleChoiceQuestion> response = restTemplate.postForEntity(apiBaseUrl + "/multiplechoicequestion", multipleChoiceQuestion, MultipleChoiceQuestion.class);
        return response.getBody();
    }

    //MultipleChoiceOption Methods
    public List<MultipleChoiceOption> getMultipleChoiceOptions() {
        ResponseEntity<MultipleChoiceOption[]> response = restTemplate.getForEntity(apiBaseUrl + "/multiplechoiceoption", MultipleChoiceOption[].class);
        return Arrays.asList(response.getBody());
    }
    public MultipleChoiceOption createMultipleChoiceOption(MultipleChoiceOption multipleChoiceOption) {
        ResponseEntity<MultipleChoiceOption> response = restTemplate.postForEntity(apiBaseUrl + "/multiplechoiceoption", multipleChoiceOption, MultipleChoiceOption.class);
        return response.getBody();
    }

    //NumericRangeQuestion Methods
    public List<NumericRangeQuestion> getNumericRangeQuestions() {
        ResponseEntity<NumericRangeQuestion[]> response = restTemplate.getForEntity(apiBaseUrl + "/numericrangequestion", NumericRangeQuestion[].class);
        return Arrays.asList(response.getBody());
    }
    public NumericRangeQuestion createNumericRangeQuestion(NumericRangeQuestion numericRangeQuestion) {
        ResponseEntity<NumericRangeQuestion> response = restTemplate.postForEntity(apiBaseUrl + "/numericrangequestion", numericRangeQuestion, NumericRangeQuestion.class);
        return response.getBody();
    }

    //TextQuestion Methods
    public List<TextQuestion> getTextQuestions() {
        ResponseEntity<TextQuestion[]> response = restTemplate.getForEntity(apiBaseUrl + "/textquestion", TextQuestion[].class);
        return Arrays.asList(response.getBody());
    }
    public TextQuestion createTextQuestion(TextQuestion textQuestion) {
        ResponseEntity<TextQuestion> response = restTemplate.postForEntity(apiBaseUrl + "/textquestion", textQuestion, TextQuestion.class);
        return response.getBody();
    }

}
