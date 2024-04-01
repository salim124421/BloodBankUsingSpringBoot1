<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> --%>
<%@ page import="java.util.List" %>
<%@ page import="in.sp.main.dto.RegisterDTO" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        /* CSS for buttons */
        .button-container {
            margin-bottom: 20px;
            text-align: center;
        }

        .button-container button {
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            margin-right: 10px;
        }

        .button-container button a {
            text-decoration: none;
            color: inherit;
        }

        .button-container button:hover {
            background-color: #0056b3;
        }

        /* CSS for table */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        /* CSS for profile info */
        .profile-info {
            margin-bottom: 20px;
        }

        .profile-info p {
            margin: 5px 0;
        }
    </style>
</head>
<body>
    <div class="button-container">
        <!-- Buttons -->
        <button><a href="#">Sorting</a></button>
        <button><a href="#">Filter</a></button>
        <button><a href="#">BloodService</a></button>
        <button><a href="#"></a></button>
    </div>

    <h1>Admin Dashboard - List of Signed-up Users</h1>

    <!-- Button to create an agent -->
    <button><a href="createAgent">Create Agent</a></button>

    <!-- Profile info -->
    <div class="profile-info">
        <p><strong>Username:</strong> ${user.getUsername()}</p>
        <p><strong>Date of Birth:</strong> ${user.getDateOfBirth()}</p>
        <p><strong>Phone Number:</strong>${user.getPhoneNumber()}</p>
        <p><strong>Address:</strong>${user.getCity()}</p>
    </div>

    <table>
        <thead>
            <tr>
                <th>Username</th>
                <th>Date of Birth</th>
                <th>Phone Number</th>
                <th>Address</th>
                <th>Blood Group</th>
            </tr>
        </thead>
        <tbody>
            <% List<RegisterDTO> allUsers = (List<RegisterDTO>) request.getAttribute("signedupUsers");
            for (RegisterDTO endUser : allUsers) { %>
                <tr>
                    <td><%= endUser.getUsername() %></td>
                    <td><%= endUser.getDateOfBirth() %></td>
                    <td><%= endUser.getPhoneNumber() %></td>
                    <td><%= endUser.getCity() %></td>
                    <td><%= endUser.getBloodGroup() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>

    <button><a href="logout">Logout</a></button>
</body>
</html>
