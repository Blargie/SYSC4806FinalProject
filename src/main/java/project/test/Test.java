package project.test;

public class Test {
    // Fields
    private int testId;
    private String testText;

    // Constructor
    public Test() {}

    // Getters (following JavaBean conventions)
    public int getTestId() { return this.testId; }
    public String getTestText() { return this.testText; }

    // Setters
    public void setTestId(int testId) { this.testId = testId; }
    public void setTestText(String testText) { this.testText = testText; }

    // Methods (optional additional behavior)
}

