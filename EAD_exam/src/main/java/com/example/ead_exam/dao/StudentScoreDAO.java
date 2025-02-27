package com.example.ead_exam.dao;

import com.example.ead_exam.entity.Student;
import com.example.ead_exam.entity.StudentScore;
import com.example.ead_exam.entity.Subjects;
import com.example.ead_exam.util.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class StudentScoreDAO {

    // ✅ Thêm điểm từ StudentScore entity
    public static void insert(StudentScore studentScore) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Kiểm tra xem Student có tồn tại không
            Student student = em.find(Student.class, studentScore.getStudent().getStudentId());
            if (student == null) {
                System.out.println("Không tìm thấy sinh viên!");
                return;
            }

            // Kiểm tra xem Subject có tồn tại không
            Subjects subject = em.find(Subjects.class, studentScore.getSubject().getSubjectId());
            if (subject == null) {
                System.out.println("Không tìm thấy môn học!");
                return;
            }

            // Thêm dữ liệu vào database
            em.persist(studentScore);
            em.getTransaction().commit();
            System.out.println("Thêm điểm thành công!");
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ✅ Thêm điểm bằng tham số (Không cần tạo StudentScore bên ngoài)
    public static void addScore(int studentId, int subjectId, double score1, double score2) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            em.getTransaction().begin();

            // Tìm Student theo ID
            Student student = em.find(Student.class, studentId);
            if (student == null) {
                System.out.println("Không tìm thấy sinh viên!");
                return;
            }

            // Tìm Subject theo ID
            Subjects subject = em.find(Subjects.class, subjectId);
            if (subject == null) {
                System.out.println("Không tìm thấy môn học!");
                return;
            }

            // Tạo đối tượng StudentScore
            StudentScore studentScore = new StudentScore();
            studentScore.setStudent(student);
            studentScore.setSubject(subject);
            studentScore.setScore1(score1);
            studentScore.setScore2(score2);

            // Lưu vào DB
            em.persist(studentScore);
            em.getTransaction().commit();
            System.out.println("Thêm điểm thành công!");

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    // ✅ Lấy điểm trung bình của sinh viên
    public static double getGrade(int studentId) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Double grade = em.createQuery(
                    "SELECT AVG(0.3 * s.score1 + 0.7 * s.score2) FROM StudentScore s WHERE s.student.studentId = :studentId",
                    Double.class
            ).setParameter("studentId", studentId).getSingleResult();
            return (grade != null) ? grade : 0.0;
        } catch (NoResultException e) {
            return 0.0;
        } finally {
            em.close();
        }
    }

    // ✅ Lấy xếp hạng của sinh viên theo điểm
    public static String getGradeLetter(int studentId) {
        double grade = getGrade(studentId);
        if (grade >= 8.0) return "A";
        else if (grade >= 6.0) return "B";
        else if (grade >= 4.0) return "D";
        else return "F";
    }
}
