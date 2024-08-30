package com.resume.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.resume.util.DatabaseConnection;
import com.resume.util.PDFGenerator;
import com.resume.model.Resume;

public class ResumeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve userId from session
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("resume.jsp?error=NoUser");
            return;
        }

        // Create resume object
        Resume resume = new Resume();
        resume.setUserId(userId);
        resume.setName(request.getParameter("name"));
        resume.setEmail(request.getParameter("email"));
        resume.setPhone(request.getParameter("phone"));
        resume.setSkills(request.getParameter("skills"));
        resume.setExperience(request.getParameter("experience"));
        resume.setEducation(request.getParameter("education"));

        try (Connection conn = DatabaseConnection.getConnection()) {
            // Insert resume details into the database
            String query = "INSERT INTO resumes (user_id, name, email, phone, skills, experience, education) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, resume.getUserId());
            stmt.setString(2, resume.getName());
            stmt.setString(3, resume.getEmail());
            stmt.setString(4, resume.getPhone());
            stmt.setString(5, resume.getSkills());
            stmt.setString(6, resume.getExperience());
            stmt.setString(7, resume.getEducation());
            stmt.executeUpdate();

            // Generate PDF
            String pdfFilePath = getServletContext().getRealPath("/") + "resumes/" + resume.getName() + "_resume.pdf";
            PDFGenerator.generateResumePDF(resume, pdfFilePath);

            response.sendRedirect("resumeConfirmation.jsp?file=" + pdfFilePath);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("resume.jsp?error=DatabaseError");
        }
    }
}
