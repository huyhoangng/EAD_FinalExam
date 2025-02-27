package com.example.ead_exam.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "subject_t")
public class Subjects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id")
    private int subjectId;

    @Column(name = "subject_code", nullable = false)
    private String subjectCode;

    @Column(name = "subject_name", nullable = false)
    private String subjectName;

    @Column(name = "credit", nullable = false)
    private int credit;
}
