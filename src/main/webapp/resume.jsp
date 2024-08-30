<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Resume Builder</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
    <h1>Resume Builder</h1>
    <form action="ResumeServlet" method="post">
        <div>
            <label for="name">Name:</label>
            <input type="text" id="name" name="name" required>
        </div>
        <div>
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div>
            <label for="phone">Phone:</label>
            <input type="text" id="phone" name="phone" required>
        </div>
        <div>
            <label for="skills">Skills:</label>
            <textarea id="skills" name="skills" rows="4" required></textarea>
        </div>
        <div>
            <label for="experience">Experience:</label>
            <textarea id="experience" name="experience" rows="4" required></textarea>
        </div>
        <div>
            <label for="education">Education:</label>
            <textarea id="education" name="education" rows="4" required></textarea>
        </div>
        <button type="submit">Generate Resume</button>
    </form>
</body>
</html>
