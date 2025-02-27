package com.example.ead_exam.dao;

import com.example.ead_exam.entity.StudentScore;
import com.example.ead_exam.util.JpaUtil;
import jakarta.persistence.EntityManager;

public class StudentScoreDAO {
    public static void insert(StudentScore studentScore) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(studentScore);
        em.getTransaction().commit();
        em.close();
    }

    public static double getGrade(int studentId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Double grade = em.createQuery(
                    "SELECT AVG(0.3 * s.score1 + 0.7 * s.score2) FROM StudentScore s WHERE s.student.studentId = :studentId",
                    Double.class
            ).setParameter("studentId", studentId).getSingleResult();
            return (grade != null) ? grade : 0.0;
        } finally {
            em.close();
        }
    }

    public static String getGradeLetter(int studentId) {
        double grade = getGrade(studentId);
        if (grade >= 8.0) return "A";
        else if (grade >= 6.0) return "B";
        else if (grade >= 4.0) return "D";
        else return "F";
    }
}
