package project.survey;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import project.question.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    //Fields
    private final SurveyService surveyService;
    private final QuestionService questionService;

    //Constructor
    public SurveyController(SurveyService surveyService, QuestionService questionService) {
        this.surveyService = surveyService;
        this.questionService = questionService;
    }

    //Home Page
    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    //Create Survey
    @RequestMapping("/create-survey")
    public String createSurvey(Survey survey) {
        return "create-survey";
    }
    @RequestMapping("/save-survey")
    public ResponseEntity<Map<String, String>> saveSurvey(@RequestBody Survey survey) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the Survey
        System.out.println(survey.toString());

        //POST the Survey
        Survey newSurvey = surveyService.createSurvey(survey);

        //Return the successful response
        response.put("message", "Survey created successfully");
        response.put("surveyId", String.valueOf(newSurvey.getSurveyId()));
        return ResponseEntity.ok(response);
    }
    @RequestMapping("/save-question")
    public ResponseEntity<Map<String, String>> saveQuestion(@RequestBody Question question) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the Question
        System.out.println(question.toString());

        //POST the Question
        Question newQuestion = questionService.createQuestion(question);

        //Return the successful response
        response.put("message", "Question created successfully");
        response.put("questionId", String.valueOf(newQuestion.getQuestionId()));
        return ResponseEntity.ok(response);
    }
    @RequestMapping("/save-mcq")
    public ResponseEntity<Map<String, String>> saveMcq(@RequestBody MultipleChoiceQuestion mcq) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the MultipleChoiceQuestion
        System.out.println(mcq.toString());

        //POST the MultipleChoiceQuestion
        MultipleChoiceQuestion newMCQ = questionService.createMultipleChoiceQuestion(mcq);

        //Return the successful response
        response.put("message", "Multiple Choice Question created successfully");
        response.put("mcqId", String.valueOf(newMCQ.getMcqId()));
        return ResponseEntity.ok(response);
    }
    @RequestMapping("/save-mcq-option")
    public ResponseEntity<Map<String, String>> saveMcqOption(@RequestBody MultipleChoiceOption mcqOption) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the MultipleChoiceQuestion
        System.out.println(mcqOption.toString());

        //POST the MultipleChoiceQuestion
        questionService.createMultipleChoiceOption(mcqOption);

        //Return the successful response
        response.put("message", "Multiple Choice Question created successfully");
        return ResponseEntity.ok(response);
    }
    @RequestMapping("/save-nrq")
    public ResponseEntity<Map<String, String>> saveNrq(@RequestBody NumericRangeQuestion nrq) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the NumericRangeQuestion
        System.out.println(nrq.toString());

        //POST the NumericRangeQuestion
        questionService.createNumericRangeQuestion(nrq);

        //Return the successful response
        response.put("message", "Numeric Range Question created successfully");
        return ResponseEntity.ok(response);
    }
    @RequestMapping("/save-tq")
    public ResponseEntity<Map<String, String>> saveTq(@RequestBody TextQuestion tq) {
        //Instantiate response
        Map<String, String> response = new HashMap<>();

        //Print the TextQuestion
        System.out.println(tq.toString());

        //POST the TextQuestion
        questionService.createTextQuestion(tq);

        //Return the successful response
        response.put("message", "Text Question created successfully");
        return ResponseEntity.ok(response);
    }
    //View Surveys
    @RequestMapping("/view-survey")
    public String viewSurvey() {
        return "view-survey";
    }

    //List Surveys
    @RequestMapping("/list-survey")
    public String getSurveys(Model model) {
        model.addAttribute("surveys", surveyService.getSurveys());
        return "survey-list";
    }
}
