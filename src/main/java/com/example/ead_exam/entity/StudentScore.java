package com.example.ead_exam.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "student_score_t")
public class StudentScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_score_id")
    private int studentScoreId;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "subject_id", nullable = false)
    private Subjects subject;

    private double score1;
    private double score2;

    public double getGrade() {
        return 0.3 * score1 + 0.7 * score2;
    }


    public String getGradeLetter() {
        double grade = getGrade();
        if (grade >= 8.0) return "A";
        else if (grade >= 6.0) return "B";
        else if (grade >= 4.0) return "D";
        else return "F";
    }
}
