package project.question;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TextQuestion extends Question {
    //Fields
    private int charLimit; //The character limit for the text question
    //Constructors
    public TextQuestion() {};
    //Methods
    //Getters
    private int getCharLimit() {
        return this.charLimit;
    }
    //Setters
    private void setCharLimit(int charLimit) {
        this.charLimit = charLimit;
    }
}
