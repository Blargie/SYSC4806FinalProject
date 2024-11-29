package project.survey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class SurveyService {
    //Fields
    private final RestTemplate restTemplate;

    @Value("${postgrest.api.baseurl}")
    private String apiBaseUrl;

    //Constructor
    public SurveyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Survey Methods
    public List<Survey> getSurveys() {
        ResponseEntity<Survey[]> response = restTemplate.getForEntity(apiBaseUrl + "/survey", Survey[].class);
        return Arrays.asList(response.getBody());
    }

    public Survey createSurvey(Survey survey) {
        ResponseEntity<Survey> response = restTemplate.postForEntity(apiBaseUrl + "/survey", survey, Survey.class);
        return response.getBody();
    }
}
