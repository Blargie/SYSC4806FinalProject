package project.answer;

import jakarta.persistence.*;

@Entity
public class MultipleChoiceAnswer extends Answer {

    @Column(nullable = false)
    private int selectedChoice; // Index of the selected option (0-based)

    @Column(length = 500)
    private String selectedOptionText; // The text of the selected option

    // Constructor
    public MultipleChoiceAnswer() {}

    // Getters
    public int getSelectedChoice() {
        return this.selectedChoice;
    }

    public String getSelectedOptionText() {
        return this.selectedOptionText;
    }

    // Setters
    public void setSelectedChoice(int selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public void setSelectedOptionText(String selectedOptionText) {
        this.selectedOptionText = selectedOptionText;
    }

}