<%@ page import="model.PlayerRegistration" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Delete Player</title>
</head>
<body>
<h1>Delete Player</h1>
<form action="delete" method="post">
    <!-- Make sure the ID is properly validated and secured -->
    <input type="hidden" name="id" value="<%=
        org.apache.commons.text.StringEscapeUtils.escapeHtml4(request.getParameter("id")) %>"/>
    <p>Are you sure you want to delete this player?</p>
    <input type="submit" value="Yes, Delete"/>
</form>
</body>
<a href="viewRecords">View all players</a>
</html>
