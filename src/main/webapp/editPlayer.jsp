<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.PlayerRegistration" %>

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        .container {
            padding: 50px;
            background-color: lightgray;
        }
        hr {
            border: 1px solid #f1f1f1;
            margin-bottom: 25px;
        }
        .registerbtn {
            background-color: #4CAF50;
            color: white;
            padding: 16px 20px;
            margin: 8px 0;
            border: none;
            cursor: pointer;
            width: 50%;
            opacity: 0.9;
        }
        .registerbtn:hover {
            opacity: 1;
        }
        button[type="submit"] {
            padding: 15px;
            border-radius: 10px;
            border: none;
            background-color: orange;
            color: white;
            cursor: pointer;
            width: 20%;
            font-size: 16px;
        }
    </style>
</head>
<body>
<div class="container">
    <center>
        <h1><span style="color:darkblue;font-weight:bold">Update Player</span></h1>
    </center>

    <form action="editPlayer" method="POST">
        <input type="hidden" name="id" value="${playerRegistration.id}" required/>

        <div class="col-md-6">
            <label for="fullName" class="form-label">Full Name</label>
            <input type="text" id="fullName" name="fullName" value="${playerRegistration.fullName}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="age" class="form-label">Age</label>
            <input type="number" class="form-control" id="age" name="age" value="${playerRegistration.age}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="gender" class="form-label">Gender</label>
            <select id="gender" name="gender" required class="form-select">
                <option value="">Select Gender</option>
                <option value="male" ${playerRegistration.gender == 'male' ? 'selected' : ''}>Male</option>
                <option value="female" ${playerRegistration.gender == 'female' ? 'selected' : ''}>Female</option>
                <option value="other" ${playerRegistration.gender == 'other' ? 'selected' : ''}>Other</option>
            </select></br>
        </div>

        <div class="col-md-6">
            <label for="phoneNo" class="form-label">Phone No</label>
            <input type="number" class="form-control" id="phoneNo" name="phoneNo" value="${playerRegistration.phoneNo}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="email" class="form-label">E-mail</label>
            <input type="email" class="form-control" id="email" name="email" value="${playerRegistration.email}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="currentlyTraining" class="form-label">Currently Undergoing Training</label>
            <select id="currentlyTraining" name="currentlyTraining" required class="form-select">
                <option value="Yes" ${playerRegistration.currentlyTraining == 'Yes' ? 'selected' : ''}>Yes</option>
                <option value="No" ${playerRegistration.currentlyTraining == 'No' ? 'selected' : ''}>No</option>
            </select></br>
        </div>

        <div class="col-md-6">
            <label for="training" class="form-label">Which Training program would you like to register for?</label>
            <select multiple class="form-select" id="training" name="training" required>
                <option value="Summer Camp" ${playerRegistration.training.contains('Summer Camp') ? 'selected' : ''}>Summer Camp</option>
                <option value="Yearly Grouping Coaching" ${playerRegistration.training.contains('Yearly Grouping Coaching') ? 'selected' : ''}>Yearly Grouping Coaching</option>
                <option value="Half Yearly Grouping Coaching" ${playerRegistration.training.contains('Half Yearly Grouping Coaching') ? 'selected' : ''}>Half Yearly Grouping Coaching</option>
            </select></br>
        </div>

        <div class="col-md-6">
            <label for="other" class="form-label">Other</label>
            <input type="text" class="form-control" id="other" name="other" value="${playerRegistration.other}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="date" class="form-label">Which date and batch would you prefer for academy visit and spot registration?</label>
            <input type="datetime-local" class="form-control" id="date" name="date" value="${playerRegistration.date}" required/></br>
        </div>

        <div class="col-md-6">
            <label for="address" class="form-label">Please add your proper address</label>
            <input type="text" class="form-control" id="address" name="address" value="${playerRegistration.address}" required/></br>
        </div>

        <div class="text-center mt-3">
            <input type="submit" value="Update Player" class="registerbtn"/>
        </div>
    </form>
    <a href=viewRecords>Back to Player List</a>
</div>
</body>
</html>
