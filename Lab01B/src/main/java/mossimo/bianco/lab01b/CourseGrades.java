package mossimo.bianco.lab01b;

/**
 *
 * @author sport
 */
public class CourseGrades implements Analyzable {
    private GradedActivity[] grades;
    private static final int NUM_GRADES = 4;

    public CourseGrades() {
        grades = new GradedActivity[NUM_GRADES];
    }

    // Setters
    public void setLab(GradedActivity labScore) {
        grades[0] = labScore;
    }
            
    public void setPassFailExam(PassFailExam passFailExam) {
        grades[1] = passFailExam;
    }

    public void setEssay(Essay essayScore) {
        grades[2] = essayScore;
    }

    public void setFinalExam(FinalExam finalExamScore) {
        grades[3] = finalExamScore;
    }

    // Analyzable interface
    @Override
    public double getAverage() {
        double total = 0;

        for (GradedActivity grade : grades) {
            total += grade.getScore();
        }

        return total / grades.length;
    }

    @Override
    public GradedActivity getHighest() {
        GradedActivity highest = grades[0];

        for (GradedActivity grade : grades) {
            if (grade.getScore() > highest.getScore())
                highest = grade;
        }

        return highest;
    }

    @Override
    public GradedActivity getLowest() {
        GradedActivity lowest = grades[0];

        for (GradedActivity grade : grades) {
            if (grade.getScore() < lowest.getScore())
                lowest = grade;
        }

        return lowest;
    }

    // toString
    @Override
    public String toString() {
        String[] labels = {"Lab", "Pass/Fail Exam", "Essay", "Final Exam"};
        String str = "";

        for (int i = 0; i < grades.length; i++) {
            str += String.format("%s: %.1f (%c)%n", labels[i], grades[i].getScore(), grades[i].getGrade());
        }

        return str;
    }
    
}
