package com.example.ead_exam.servlet;



import com.example.ead_exam.dao.StudentDAO;
import com.example.ead_exam.entity.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentCode = request.getParameter("studentCode");
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");

        Student student = new Student();
        student.setStudentCode(studentCode);
        student.setFullName(fullName);
        student.setAddress(address);

        StudentDAO.insert(student);

        response.sendRedirect("student-list.jsp");
    }
}
