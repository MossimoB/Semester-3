package mossimo.bianco.lab01b;

/**
 *
 * @author sport
 */
public class Essay extends GradedActivity {
    private double grammar;
    private double spelling;
    private double correctLength;
    private double content;

    // Setters
    /**
     * Sets the four component scores and computes the overall essay score
     * @param grammar the grammar score out of 30 points
     * @param spelling the spelling score out of 20 points
     * @param correctLength the correct length score out of 20 points
     * @param content the content score out of 30 points
     */
    public void setScore(double grammar, double spelling, double correctLength, double content) {
        setGrammar(grammar);
        setSpelling(spelling);
        setCorrectLength(correctLength);
        setContent(content);

        super.setScore(grammar + spelling + correctLength + content);
    }

    /**
     * Sets the grammar score
     * @param grammar the grammar score out of 30 points
     */
    public void setGrammar(double grammar) {
        this.grammar = grammar;
    }

    /**
     * Sets the spelling score
     * @param spelling the spelling score out of 20 points
     */
    public void setSpelling(double spelling) {
        this.spelling = spelling;
    }

    /**
     * Sets the correct length score
     * @param correctLength the correct length score out of 20 points
     */
    public void setCorrectLength(double correctLength) {
        this.correctLength = correctLength;
    }

    /**
     * Sets the content score
     * @param content the content score out of 30 points
     */
    public void setContent(double content) {
        this.content = content;
    }

    //Getters
    public double getGrammar() {
        return grammar;
    }

    public double getSpelling() {
        return spelling;
    }

    public double getCorrectLength() {
        return correctLength;
    }
}
