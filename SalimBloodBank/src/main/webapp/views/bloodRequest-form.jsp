<!DOCTYPE html>
<html>
<head>
    <title>Blood Donation Form</title>
    <!-- You can link your CSS file here -->
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<h1>Blood Donation Form</h1>

<form action="bloodRequest" method="post">
    <label for="transaction_type">Transaction Type:</label><br>
    <input type="radio" id="donation" name="requestType" value="donation" checked>
    <label for="donation">Blood Donation</label><br>
    <input type="radio" id="request" name="requestType" value="request">
    <label for="request">Blood Request</label><br><br>

    <label for="blood_group">Blood Group:</label><br>
    <select id="blood_group" name="bloodGroup">
        <option value="A+">A+</option>
        <option value="A-">A-</option>
        <option value="B+">B+</option>
        <option value="B-">B-</option>
        <option value="AB+">AB+</option>
        <option value="AB-">AB-</option>
        <option value="O+">O+</option>
        <option value="O-">O-</option>
    </select><br><br>

    <label for="quantity">Quantity (in liters):</label><br>
    <input type="number" id="quantity" name="quantity" min="0" step="0.01"><br><br>

    <input type="submit" value="Submit">
</form>

</body>
</html>
