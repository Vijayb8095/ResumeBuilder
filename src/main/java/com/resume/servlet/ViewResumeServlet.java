package com.resume.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ViewResumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Generate the path to the PDF
        String pdfFilePath = getServletContext().getRealPath("/") + "resumes/" + userId + "_resume.pdf";
        
        // Redirect to the PDF file
        response.sendRedirect("resumes/" + userId + "_resume.pdf");
    }
}
