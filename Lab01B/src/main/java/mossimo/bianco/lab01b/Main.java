package mossimo.bianco.lab01b;

import java.util.Scanner;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //step 2: FinalExam test
        System.out.print("How many questions are on the final exam? ");
        int questions = input.nextInt();
        System.out.print("How many questions did the student miss? ");
        int missed = input.nextInt();

        FinalExam finalExam = new FinalExam(questions, missed);

        System.out.print(String.format("Each question counts %s points.", finalExam.getPointsEach()));
        System.out.print(String.format("\nThe exam score is %s", finalExam.getScore()));
        System.out.println(String.format("\nThe exam grade is %s", finalExam.getGrade()));

        //step 3: PassFailActivity and PassFailExam test
        System.out.print("\nHow many questions are on the final exam? ");
        questions = input.nextInt();
        System.out.print("How many questions did the student miss? ");
        missed = input.nextInt();
        System.out.print("what is the minimum passing score? ");
        int minPassing = input.nextInt();

        PassFailExam PFE = new PassFailExam(questions, missed, minPassing);

        System.out.print(String.format("Each question counts %s points.", PFE.getPointsEach()));
        System.out.print(String.format("\nThe exam score is %s", PFE.getScore()));
        System.out.println(String.format("\nThe exam grade is %s", PFE.getGrade()));

        //step 4: Essay test
        System.out.println("\nTerm paper:");
        System.out.print("Grammar points: ");
        double gr = input.nextDouble();
        System.out.print("Spelling points: ");
        double sp = input.nextDouble();
        System.out.print("Length points: ");
        double len = input.nextDouble();
        System.out.print("Content points: ");
        double cnt = input.nextDouble();

        Essay essay = new Essay();
        essay.setScore(gr, sp, len, cnt);

        System.out.println(String.format("Total points: %s", essay.getScore()));
        System.out.println(String.format("Grade: %s", essay.getGrade()));
        
        //step 5: CoursesGrades test
        CourseGrades courseGrades = new CourseGrades();
        GradedActivity lab = new GradedActivity();
        lab.setScore(85);
        courseGrades.setLab(lab);
        courseGrades.setPassFailExam(PFE);
        courseGrades.setEssay(essay);
        courseGrades.setFinalExam(finalExam);
        System.out.println("\n" + courseGrades.toString());
        //step 6 test
        System.out.println(String.format("Average score: %s", courseGrades.getAverage()));
        System.out.println(String.format("Highest score: %s", courseGrades.getHighest().getScore()));
        System.out.println(String.format("Lowest score: %s", courseGrades.getLowest().getScore()));
    }    }
    
