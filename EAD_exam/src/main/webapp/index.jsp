<%@ page import="java.util.List" %>
<%@ page import="com.example.ead_exam.dao.StudentDAO" %>
<%@ page import="com.example.ead_exam.dao.StudentScoreDAO" %>
<%@ page import="com.example.ead_exam.entity.Student" %>
<%@ page import="com.example.ead_exam.entity.StudentScore" %>
<html>
<head>
  <title>Danh sách Sinh viên</title>
  <style>
    table { width: 100%; border-collapse: collapse; }
    th, td { border: 1px solid black; padding: 8px; text-align: center; }
    th { background-color: #f2f2f2; }
  </style>
</head>
<body>
<h2>Danh sách Sinh viên</h2>
<table>
  <tr>
    <th>Student ID</th>
    <th>Full Name</th>
    <th>Address</th>
    <th>Grade</th>
    <th>Grade Letter</th>
  </tr>
  <%
    List<Student> students = StudentDAO.getAll();
    for (Student student : students) {
      double grade = StudentScoreDAO.getGrade(student.getStudentId());
      String gradeLetter = StudentScoreDAO.getGradeLetter(student.getStudentId());
  %>
  <tr>
    <td><%= student.getStudentId() %></td>
    <td><%= student.getFullName() %></td>
    <td><%= student.getAddress() %></td>
    <td><%= String.format("%.2f", grade) %></td>
    <td><%= gradeLetter %></td>
  </tr>
  <% } %>
</table>
</body>
</html>
