package com.example.ead_exam.servlet;

import com.example.ead_exam.dao.StudentDAO;
import com.example.ead_exam.entity.Student;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/addStudent")
public class AddStudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws SecurityException, IOException {
        String studentCode = request.getParameter("studentCode");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");

        Student student = new Student(studentCode, fullName, address);
        StudentDAO.insert(student);

        response.sendRedirect("students.jsp");
    }
}
