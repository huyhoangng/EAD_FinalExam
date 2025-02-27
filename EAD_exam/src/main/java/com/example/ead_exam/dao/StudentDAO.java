package com.example.ead_exam.dao;

import com.example.ead_exam.entity.Student;
import com.example.ead_exam.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudentDAO {
    public static void insert(Student student) {
        EntityManager em = JpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
    }

    public static List<Student> getAll() {
        EntityManager em = JpaUtil.getEntityManager();
        List<Student> students = em.createQuery("SELECT s FROM Student s", Student.class).getResultList();
        em.close();
        return students;
    }
    public static Student findById(int studentId) {
        EntityManager em = JpaUtil.getEntityManager();
        Student student = em.find(Student.class, studentId);
        em.close();
        return student;
    }
}
