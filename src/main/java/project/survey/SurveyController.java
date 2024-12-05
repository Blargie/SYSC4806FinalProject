package project.survey;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import project.answer.Answer;
import project.answer.AnswerRepository;
import project.answer.MultipleChoiceAnswer;
import project.answer.NumericRangeAnswer;
import project.answer.TextAnswer;
import project.question.*;

@Controller
@RequestMapping("/api/surveys")
public class SurveyController {
    private final SurveyRepository surveyRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;

    @Autowired
    public SurveyController(SurveyRepository surveyRepository,
            AnswerRepository answerRepository,
            QuestionRepository questionRepository) {
        this.surveyRepository = surveyRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSurvey(@PathVariable Integer id) {
        try {
            Survey survey = surveyRepository.findById(id)
                    .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

            // Delete all related questions first to avoid relationship conflicts
            for (Question question : survey.getSurveyQuestions()) {
                questionRepository.delete(question);
            }

            // Now delete the survey
            surveyRepository.deleteById(survey.getSurveyId());
            return ResponseEntity.ok("Survey deleted successfully");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Failed to delete survey");
        }
    }

    @GetMapping("/{surveyId}/edit")
    public String editSurvey(@PathVariable Integer surveyId, Model model) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));

        // Load questions eagerly to prevent LazyInitializationException
        survey.getSurveyQuestions().size();
        model.addAttribute("survey", survey);
        return "edit-survey";
    }

    @PostMapping("/{surveyId}/update")
    public ResponseEntity<String> updateSurvey(@PathVariable Integer surveyId,
            @RequestBody Survey updatedSurvey) {
        Survey existingSurvey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        // Update basic survey information
        existingSurvey.setSurveyName(updatedSurvey.getSurveyName());
        existingSurvey.setSurveyDescription(updatedSurvey.getSurveyDescription());
        existingSurvey.setIsOpen(updatedSurvey.getIsOpen());
        existingSurvey.setIsAnonymous(updatedSurvey.getIsAnonymous());
        existingSurvey.setExpirationDate(updatedSurvey.getExpirationDate());

        // Handle question updates
        existingSurvey.removeAllQuestions();
        if (updatedSurvey.getSurveyQuestions() != null) {
            for (Question question : updatedSurvey.getSurveyQuestions()) {
                question.setSurvey(existingSurvey);
                existingSurvey.addQuestion(question);
            }
        }

        surveyRepository.save(existingSurvey);
        return ResponseEntity.ok("Survey updated successfully");
    }

    @GetMapping("/home")
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
        survey.setIsOpen(true);
        survey.setCreatedAt(new Date());
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
        return "survey-list"; // Name of the template above
    }

    @GetMapping("/{surveyId}/answer")
    public String getSurveyToAnswer(@PathVariable Integer surveyId, Model model) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));
        model.addAttribute("survey", survey);
        return "answer-survey";
    }

    @GetMapping("/list-open")
    public String listOpenSurveys(Model model) {
        List<Survey> openSurveys = surveyRepository.findByIsOpenTrue();
        model.addAttribute("surveys", openSurveys);
        return "survey-list"; // Ensure this points to the correct template
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



    @GetMapping("/{surveyId}/generate")
    public String generateReport(@PathVariable Integer surveyId, Model model) {
        // Fetch the survey and associated questions
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));
        model.addAttribute("survey", survey);

        // Fetch answers for the survey
        List<Answer> answers = answerRepository.findBySurveyId(surveyId);

        // Debugging outputs
        System.out.println("Survey: " + survey.getSurveyName());
        System.out.println("Questions: " + survey.getSurveyQuestions());
        System.out.println("Answers: " + answers);

        // Prepare data for each question
        Map<Integer, Object> reportData = new HashMap<>();
        for (Question question : survey.getSurveyQuestions()) {
            if (question == null) continue; // Skip null questions for safety

            if (question instanceof TextQuestion) {
                List<String> textAnswers = answers.stream()
                        .filter(answer -> answer instanceof TextAnswer &&
                                answer.getQuestionId() != null &&
                                answer.getQuestionId().equals(question.getQuestionId()))
                        .map(answer -> ((TextAnswer) answer).getText())
                        .toList();
                reportData.put(question.getQuestionId(), textAnswers);

            } else if (question instanceof NumericRangeQuestion) {
                List<Integer> numericAnswers = answers.stream()
                        .filter(answer -> answer instanceof NumericRangeAnswer &&
                                answer.getQuestionId() != null &&
                                answer.getQuestionId().equals(question.getQuestionId()))
                        .map(answer -> ((NumericRangeAnswer) answer).getChoice())
                        .filter(Objects::nonNull)
                        .toList();

                if (!numericAnswers.isEmpty()) {
                    Map<Integer, Long> histogram = numericAnswers.stream()
                            .collect(Collectors.groupingBy(choice -> choice, Collectors.counting()));
                    reportData.put(question.getQuestionId(), histogram);
                } else {
                    reportData.put(question.getQuestionId(), Map.of());
                }

            } else if (question instanceof MultipleChoiceQuestion) {
                List<String> mcAnswers = answers.stream()
                        .filter(answer -> {
                            boolean isMC = answer instanceof MultipleChoiceAnswer;
                            if (isMC) {
                                System.out.println("Answer Question ID: " + answer.getQuestionId() + " | Current Question ID: " + question.getQuestionId());
                            }
                            return isMC &&
                                    answer.getQuestionId() != null &&
                                    answer.getQuestionId().equals(question.getQuestionId());
                        })
                        .map(answer -> ((MultipleChoiceAnswer) answer).getSelectedOptionText())
                        .filter(Objects::nonNull)
                        .toList();


                if (!mcAnswers.isEmpty()) {
                    Map<String, Long> pieData = mcAnswers.stream()
                            .collect(Collectors.groupingBy(answer -> answer, Collectors.counting()));
                    reportData.put(question.getQuestionId(), pieData);
                    System.out.println("Pie Chart Data for Question ID " + question.getQuestionId() + ": " + pieData);
                } else {
                    System.out.println("No multiple-choice answers found for Question ID " + question.getQuestionId());
                }

            }

        }

        // Debug final data for verification
        System.out.println("Final Report Data: " + reportData);

        // Add data to the model
        model.addAttribute("reportData", reportData);
        model.addAttribute("questions", survey.getSurveyQuestions());

        return "survey-report"; // Ensure template name matches file in templates directory
    }



    @PostMapping("/{surveyId}/toggle-status")
    public ResponseEntity<String> toggleSurveyStatus(@PathVariable Integer surveyId,
            @RequestBody Map<String, Boolean> request) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid survey ID"));

        boolean newStatus = request.get("isOpen");
        survey.setIsOpen(newStatus);
        surveyRepository.save(survey);

        return ResponseEntity.ok("Survey status updated successfully");
    }

    // handles submitting survey answers
    @PostMapping(value = "/{surveyId}/submit", consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<String> submitSurveyAnswers(@PathVariable Integer surveyId,
        @RequestBody Map<String, String> answers,
        @RequestParam(required = false) Integer userId) {
        Survey survey = surveyRepository.findById(surveyId)
                .orElseThrow(() -> new IllegalArgumentException("Survey not found"));

        if (!survey.getIsOpen()) {
            return ResponseEntity.badRequest().body("Survey is closed");
        }

        if (survey.getExpirationDate() != null &&
                survey.getExpirationDate().before(new Date())) {
            return ResponseEntity.badRequest().body("Survey has expired");
        }

        for (Map.Entry<String, String> entry : answers.entrySet()) {
            Integer questionId = Integer.parseInt(entry.getKey().replace("question_", ""));
            Question question = getQuestionById(questionId);
            Answer answer = createAnswerFromQuestion(entry, question);

            if (!survey.getIsAnonymous()) {
                answer.setUserId(userId);
            }
            answer.setSurveyId(surveyId);
            answer.setQuestionId(questionId);
            answerRepository.save(answer);
        }

        return ResponseEntity.ok("Survey answers submitted successfully");
    }

    private Answer createAnswerFromQuestion(Map.Entry<String, String> entry, Question question) {
        Answer answer = null;

        // create answer type based on question's type
        switch (question.getType()) {
            case "TEXT":
                answer = new TextAnswer();
                ((TextAnswer) answer).setText(entry.getValue());
                break;

            case "MULTIPLE_CHOICE":
                MultipleChoiceAnswer mcAnswer = new MultipleChoiceAnswer();
                int selectedChoice = Integer.parseInt(entry.getValue());
                mcAnswer.setSelectedChoice(selectedChoice);

                // Populate selectedOptionText from List<String> options
                if (question instanceof MultipleChoiceQuestion) {
                    MultipleChoiceQuestion mcQuestion = (MultipleChoiceQuestion) question;
                    List<String> options = mcQuestion.getOptions(); // Assuming options are a List<String>
                    if (selectedChoice >= 0 && selectedChoice < options.size()) {
                        String selectedOptionText = options.get(selectedChoice);
                        mcAnswer.setSelectedOptionText(selectedOptionText);
                    } else {
                        throw new IllegalArgumentException("Invalid selected choice index");
                    }
                } else {
                    throw new IllegalArgumentException("Question is not of type MultipleChoiceQuestion");
                }

                answer = mcAnswer;
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
        return Integer.parseInt(entry.getKey()); // convert key to question ID
    }


}