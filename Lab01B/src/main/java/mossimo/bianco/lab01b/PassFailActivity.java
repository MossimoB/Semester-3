package mossimo.bianco.lab01b;

/**
 *
 * @author sport
 */
public class PassFailActivity extends GradedActivity {
    private double minPassingScore;

    public PassFailActivity(double mps) {
        minPassingScore = mps;
    }

    public char getGrade() {
        char letterGrade;

        if (getScore() >= minPassingScore)
            letterGrade = 'P';
        else
            letterGrade = 'F';

        return letterGrade;
    }
}
