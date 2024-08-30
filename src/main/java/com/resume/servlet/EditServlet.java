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

public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Retrieve userId from session
        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM resumes WHERE user_id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                request.setAttribute("resume", rs);
                request.getRequestDispatcher("edit.jsp").forward(request, response);
            } else {
                response.sendRedirect("resume.jsp?error=NoResumeFound");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("resume.jsp?error=DatabaseError");
        }
    }
}
