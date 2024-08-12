<%@ page import="model.PlayerRegistration" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>View Records</title>


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
</head>
<style>

    table, th, td {
        border:1px solid black;

    }
</style>
<body>
<div class="container mt-5">
    <center><h1>Player Records</h1></center>

    <table style="background-color:lightblue;">

        <thead>
        <tr>
            <th>ID</th>
            <th>Full Name</th>
            <th>Age</th>
            <th>Gender</th>
            <th>Phone No</th>
            <th>E-mail</th>
            <th> Currently-Undergoing</th>
            <th>Training-Program</th>
            <th>Other</th>
            <th>Date</th>
            <th>Address</th>
            <th>Actions</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<PlayerRegistration> playerList = (List<PlayerRegistration>) request.getAttribute("playerRegistration");
            if (playerList!= null) {
                for (PlayerRegistration player : playerList) {
        %>
        <tr>
            <td><%= player.getId() %></td>
            <td><%= player.getFullName() %></td>
            <td><%= player.getAge() %></td>
            <td><%= player.getGender() %></td>
            <td><%= player.getPhoneNo() %></td>
            <td><%= player.getEmail() %></td>
            <td><%= player.getCurrentlyTraining() %></td>
            <td><%= player.getTraining() %></td>
            <td><%= player.getOther() %></td>
            <td><%= player.getDate() %></td>
            <td><%= player.getAddress() %></td>


            <td>
                <a href="editPlayer.jsp?action=edit&id=<%= player.getId() %>">Edit</a>
                <a href="deletePlayer.jsp?action=delete&id=<%= player.getId() %>" onclick="return confirm('Are you sure you want to delete this player?');">Delete</a>
            </td>

        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="10">No records found.</td>
        </tr>
        <%
            }
        %>
</div>
        </tbody>
    </table>
    <a href="registration.html">Submit another enquiry</a>

<script src="https://cdn.jsdelivr.net/npm/jquery@3.5.1/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.min.js"></script>
</body>
</html>
