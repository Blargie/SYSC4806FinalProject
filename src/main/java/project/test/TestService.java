package project.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TestService {
    //Fields
    private final RestTemplate restTemplate;

    @Value("${postgrest.api.baseurl}")
    private String apiBaseUrl;

    //Constructor
    public TestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    //Getters
    public List<Test> getTests() {
        ResponseEntity<Test[]> response = restTemplate.getForEntity(apiBaseUrl + "/test", Test[].class);
        return Arrays.asList(response.getBody());
    }

    //Setters
    public Test createTest(Test test) {
        ResponseEntity<Test> response = restTemplate.postForEntity(apiBaseUrl + "/test", test, Test.class);
        return response.getBody();
    }
}
