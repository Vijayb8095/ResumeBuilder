package com.resume.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadResumeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Integer userId = (Integer) request.getSession().getAttribute("userId");

        if (userId == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        // Path to the PDF file
        String pdfFilePath = getServletContext().getRealPath("/") + "resumes/" + userId + "_resume.pdf";
        File file = new File(pdfFilePath);

        if (file.exists()) {
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + userId + "_resume.pdf");
            response.setContentLength((int) file.length());

            try (FileInputStream in = new FileInputStream(file);
                 OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = in.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            }
        } else {
            response.sendRedirect("resumeconfirmation.jsp?error=FileNotFound");
        }
    }
}
