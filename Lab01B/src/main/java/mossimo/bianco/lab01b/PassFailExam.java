package mossimo.bianco.lab01b;

/**
 *
 * @author sport
 */
public class PassFailExam extends PassFailActivity {
    private int numQuestions;
    private double pointsEach;
    private int numMissed;

    public PassFailExam(int questions, int missed, double minPassing) {
        super(minPassing);

        double numericScore;

        numQuestions = questions;
        numMissed = missed;

        pointsEach = 100.0 / numQuestions;
        numericScore = 100.0 - (numMissed * pointsEach);

        setScore(numericScore);
    }

    public double getPointsEach() {
        return pointsEach;
    }

    public int getNumMissed() {
        return numMissed;
    }
}
