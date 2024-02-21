<%@include file="auth/auth.jsp" %>


<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 300px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        select, input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
        }

        button {
            background-color: #4caf50;
            color: #fff;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
        }
    </style>
</head>
<body>
    <form action="addcategory" method="post">
    <h3><%@include file="Message/message.jsp"%></h3>
        <label>Department</label>
        <select name="department" required="required">
            <option value="Grocery">Grocery</option>
            <option value="stationary">Stationary</option>
            <option value="fruit">Fruit</option>
            <option value="vegetable">Vegetable</option>
        <option value="confectionery">Confectionery</option>
        </select><br>
        <label>Product Category:</label>
        <input type="text" name="category" placeholder="Enter product category"><br>
        <button type="submit">ADD</button>
    </form>
</body>
</html>
