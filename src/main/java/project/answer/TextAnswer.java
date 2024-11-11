package project.answer;

import jakarta.persistence.Entity;

@Entity
public class TextAnswer extends Answer {
    //Fields
    private String text;

    //Constructor
    public TextAnswer() {}
    //Methods
    //Getters
    public String getText() {
        return this.text;
    }
    //Setters
    public void setText(String text) {
        this.text = text;
    }
}
