package project.question;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("TEXT")
public class TextQuestion extends Question {

    @Column(nullable = false)
    private int charLimit = 255; // Default value

    public TextQuestion() {}

    public int getCharLimit() {
        return this.charLimit;
    }

    public void setCharLimit(int charLimit) {
        this.charLimit = charLimit;
    }
}