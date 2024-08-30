<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Resume</title>
</head>
<body>
    <h1>Edit Resume</h1>
    <form action="EditServlet" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" value="<%= ((ResultSet)request.getAttribute("resume")).getString("name") %>" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" value="<%= ((ResultSet)request.getAttribute("resume")).getString("email") %>" required>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" value="<%= ((ResultSet)request.getAttribute("resume")).getString("phone") %>" required>
        </div>
        <div>
            <label for="skills">Skills:</label>
            <textarea id="skills" name="skills" required><%= ((ResultSet)request.getAttribute("resume")).getString("skills") %></textarea>
        </div>
        <div>
            <label for="experience">Experience:</label>
            <textarea id="experience" name="experience" required><%= ((ResultSet)request.getAttribute("resume")).getString("experience") %></textarea>
        </div>
        <div>
            <label for="education">Education:</label>
            <textarea id="education" name="education" required><%= ((ResultSet)request.getAttribute("resume")).getString("education") %></textarea>
        </div>
        <button type="submit">Update Resume</button>
    </form>
</body>
</html>
