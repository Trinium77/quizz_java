package quizPack;

public class Question {
    private String questionText;
    private String option1;
    private String option2;
    private String option3;
    private String Domain;
    private String Niveau;
    private String correctAnswer;
    private String userAnswer;

    public Question(String questionText, String correctAnswer, String option1, String option2, String option3, String Domain, String Niveau) {

        this.questionText = questionText;
        this.correctAnswer = correctAnswer;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.Domain = Domain;
        this.Niveau = Niveau;

    }

    // Getters and setters
    public String getQuestionText() {
        return questionText;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getUserAnswer() {
        return userAnswer;
    }
}
