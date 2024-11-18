package project.survey;

import project.answer.Answer;
import project.answer.TextAnswer;
import project.answer.MultipleChoiceAnswer;
import project.answer.NumericRangeAnswer;
import project.answer.AnswerRepository;
import project.question.Question;
import project.question.QuestionRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.RequestParam;


@Controller  // Changed from @RestController
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyRepository surveyRepository;
    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository; // Inject QuestionRepository


    @Autowired
    public SurveyController(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;

    }

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }


    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("survey", new Survey());
        return "create-survey";
    }

    @PostMapping("/save")
    public ResponseEntity<Survey> createSurvey(@RequestBody Survey survey) {
        survey.setIsOpen(true); // New surveys are open by default
        Survey savedSurvey = surveyRepository.save(survey);
        return ResponseEntity.ok(savedSurvey);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<Survey> getSurvey(@PathVariable Integer surveyId) {
        List<Survey> surveys = surveyRepository.findBySurveyId(surveyId);
        if (surveys.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(surveys.get(0));
    }

    @GetMapping("/list")
    public String listSurveys(Model model) {
        Iterable<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("surveys", surveys);
        return "survey-list";  // Name of the template above
    }

    @GetMapping("/{surveyId}/answer")
    public String getSurveyToAnswer(@PathVariable Integer surveyId, Model model) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));
        model.addAttribute("survey", survey);
        return "answer-survey";
    }

    // New mapping for View Survey page
    @GetMapping("/view-survey")
    public String viewSurveyPage() {
        return "ViewSurvey";
    }

    // New method for returning JSON response
    @GetMapping("/list-json")
    public ResponseEntity<List<Survey>> getAllSurveys() {
        List<Survey> surveys = (List<Survey>) surveyRepository.findAll();
        surveys.forEach(survey -> survey.getSurveyQuestions().size()); // Trigger loading of questions
        return ResponseEntity.ok(surveys);
    }

    @GetMapping("/{surveyId}/edit")
    public String editSurvey(@PathVariable Integer surveyId, Model model) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));
        model.addAttribute("survey", survey);
        return "edit-survey"; // The name of your edit survey template (e.g., edit-survey.html)
    }

    @GetMapping("/{surveyId}/generate")
    public String generateReport(@PathVariable Integer surveyId, Model model) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));
        model.addAttribute("survey", survey);
        // Logic to generate the report (e.g., create PDF or HTML report)
        return "survey-report"; // The name of your report template (e.g., survey-report.html)
    }




    // handles submitting survey answers
    @PostMapping("/{surveyId}/submit")
    public String submitSurveyAnswers(@PathVariable Integer surveyId, @RequestParam Map<String, String> answers) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));

        // Iterate answers and create the correct type
        for (Map.Entry<String, String> entry : answers.entrySet()) {
            Integer questionId = Integer.parseInt(entry.getKey().replace("question_", ""));  // Extract question ID
            Question question = getQuestionById(questionId);  // Get question details

            Answer answer = createAnswerFromQuestion(entry, question);  // create answer type

            Integer userId = 1;
            answer.setUserId(userId);
            answer.setSurveyId(surveyId);
            answer.setQuestionId(questionId);

            //save to ANSWER database
            answerRepository.save(answer);
        }

        return "redirect:/api/surveys/list"; // redirect user back to survey list after they awnser

    }

    private Answer createAnswerFromQuestion(Map.Entry<String, String> entry, Question question) {
        Answer answer = null;

        // create answer type based on questions type
        switch (question.getType()) {
            case "TEXT":
                answer = new TextAnswer();
                ((TextAnswer) answer).setText(entry.getValue());
                break;
            case "MULTIPLE_CHOICE":
                answer = new MultipleChoiceAnswer();
                ((MultipleChoiceAnswer) answer).setSelectedChoice(Integer.parseInt(entry.getValue()));
                break;
            case "NUMERIC_RANGE":
                answer = new NumericRangeAnswer();
                ((NumericRangeAnswer) answer).setChoice(Integer.parseInt(entry.getValue()));
                break;
            default:
                throw new IllegalArgumentException("Unsupported question type: " + question.getType());
        }

        return answer;
    }

    private Question getQuestionById(Integer questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid question ID"));
    }


    private Integer getQuestionIdFromEntry(Map.Entry<String, String> entry) {
        return Integer.parseInt(entry.getKey());  // convert key to question ID
    }

    //test
}