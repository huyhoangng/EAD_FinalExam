package com.example.ead_exam.servlet;

import com.example.ead_exam.dao.StudentDAO;
import com.example.ead_exam.dao.StudentScoreDAO;
import com.example.ead_exam.dao.SubjectDAO;
import com.example.ead_exam.entity.Student;
import com.example.ead_exam.entity.StudentScore;
import com.example.ead_exam.entity.Subjects;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addScore")
public class AddScoreServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int subjectId = Integer.parseInt(request.getParameter("subjectId"));
        double score1 = Double.parseDouble(request.getParameter("score1"));
        double score2 = Double.parseDouble(request.getParameter("score2"));

        Student student = StudentDAO.findById(studentId);
        Subjects subject = SubjectDAO.findById(subjectId);
        StudentScore studentScore = new StudentScore(student, subject, score1, score2);

        StudentScoreDAO.insert(studentScore);
        response.sendRedirect("students.jsp");
    }
}

