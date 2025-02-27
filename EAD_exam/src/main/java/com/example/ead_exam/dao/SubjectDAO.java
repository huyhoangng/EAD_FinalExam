package com.example.ead_exam.dao;


import com.example.ead_exam.entity.Subjects;
import com.example.ead_exam.util.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

public class SubjectDAO {

    // Thêm một môn học mới vào database
    public static void insert(Subjects subject) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(subject);
        em.getTransaction().commit();
        em.close();
    }

    // Lấy danh sách tất cả các môn học
    public static List<Subjects> getAll() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Subjects> subjects = em.createQuery("SELECT s FROM Subject s", Subjects.class).getResultList();
        em.close();
        return subjects;
    }

    // Tìm môn học theo ID
    public static Subjects findById(int subjectId) {
        EntityManager em = JpaUtil.getEntityManager();
        Subjects subject = em.find(Subjects.class, subjectId);
        em.close();
        return subject;
    }
}
