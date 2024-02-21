<%@include file="auth/auth.jsp" %>


<%@page import="java.util.List"%>
<%@page import="com.example.superhim.pojo.AddCategoryPojo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.example.superhim.GetApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="com.example.superhim.dao.CategoryDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product Insertion Form</title>
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
            width: 400px;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input, select {
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
        }
    </style>
</head>
<body>

<form id="productForm" action="saveproduct" method="post" enctype="multipart/form-data">
    <h2>Product Insertion Form</h2>
    <h3><%@include file="Message/message.jsp"%></h3>
    
    <label for="productTitle">Product Title:</label>
    <input type="text" id="productTitle" name="producttital" required>

    <label for="productDescription">Product Description:</label>
    <textarea id="productDescription" rows="4" required name="productdescription"></textarea>

    <label for="crossPrice">Cross Price:</label>
    <input type="number" id="crossPrice" required name="crossprice">

    <label for="mainPrice">Main Price:</label>
    <input type="number" id="mainPrice" required name="mainprice">

    <label for="availability">Availability:</label>
    <select id="availability" required name="availability" >
        <option value="inStock">In Stock</option>
        <option value="outOfStock">Out of Stock</option>
    </select>

    <label for="department">Department:</label>
    <select id="department" onchange="updateCategory()" required name="department">
        <option value="" disabled selected>select</option>
        <option value="grocery">Grocery</option>
        <option value="stationary">Stationary</option>
        <option value="vegetable">Vegetable</option>
        <option value="fruit">Fruit</option>
        <option value="confectionery">Confectionery</option>
    </select>

    <label for="category">Category:</label>
    <select id="category" required name="category"></select>
    

    <label for="productImage">Product Image :</label>
    <input type="file" id="productImage" required name="productimage" accept="image/*" >
   
    <button type="submit">submit</button>

    <!--  <button class="btn btn-primary" type="submit">Submit</button>  -->
    </form>
	<%
	ApplicationContext ac = GetApplicationContext.getApplicationContext();
	CategoryDao c = ac.getBean(CategoryDao.class);
	List<AddCategoryPojo>list = c.readCategory();
	
	ArrayList<String> grocery = new ArrayList() ;
	ArrayList<String> stationary = new ArrayList() ;
	ArrayList<String> vegetable = new ArrayList() ;
	ArrayList<String> fruit = new ArrayList() ;
	ArrayList<String> confectionery = new ArrayList() ;
	for(AddCategoryPojo cp :list){
		
		String department = cp.getDepartmentString();
		String category = cp.getCategoryString();
		if(department.equalsIgnoreCase("grocery")){
			
			grocery.add(category);
		}else if(department.equalsIgnoreCase("stationary")){
			
			stationary.add(category);
		}else if(department.equalsIgnoreCase("vegetable")){
			
			vegetable.add(category);
		}else if(department.equalsIgnoreCase("fruit")){
			
			fruit.add(category);
		}else{
			
			confectionery.add(category);
		}
	}

%>
<script>

    const categories = {
    		

            grocery: [<%for(int i =0 ;i<grocery.size(); i++){ %>' <%=grocery.get(i) %>',<% }  %>],
            stationary: [<%for(int i =0 ;i<stationary.size(); i++){ %>" <%=stationary.get(i) %>",<% }  %>],
            vegetable: [<%for(int i =0 ;i<vegetable.size(); i++){ %>" <%=vegetable.get(i) %>",<% }  %>],
            fruit: [<%for(int i =0 ;i<fruit.size(); i++){ %>" <%=fruit.get(i) %>" ,<% }  %>],
            confectionery: [<%for(int i =0 ;i<confectionery.size(); i++){ %>" <%=confectionery.get(i) %>" ,<% }  %>]
    
   //     grocery: ["Cereal", "Dairy", "Snacks", "Beverages", "Canned Goods", "Bakery", "Produce"],
   //     stationary: ["Notebooks", "Pens", "Markers", "Staplers", "Office Furniture", "Paper", "Art Supplies"],
   //    vegetable: ["Leafy Greens", "Root Vegetables", "Cruciferous Vegetables", "Squashes"],
   //   fruit: ["Citrus", "Berries", "Tropical Fruits", "Stone Fruits"],
   //     confectionery: ["Chocolates", "Candies", "Cookies", "Gummies"]
    };

    function updateCategory() {
        var department = document.getElementById("department").value;
        var categoryDropdown = document.getElementById("category");

        // Clear previous options
        categoryDropdown.innerHTML = "";

        // Add new options based on the selected department
        if (categories.hasOwnProperty(department)) {
            categories[department].forEach(function(category) {
                var option = document.createElement("option");
                option.value = category;
                option.text = category;
                categoryDropdown.add(option);
            });
        }
    }

    function submitForm() {
        // Get values from the form
        var productTitle = document.getElementById("productTitle").value;
        var productDescription = document.getElementById("productDescription").value;
        var crossPrice = document.getElementById("crossPrice").value;
        var mainPrice = document.getElementById("mainPrice").value;
        var availability = document.getElementById("availability").value;
        var department = document.getElementById("department").value;
        var category = document.getElementById("category").value;
        var productImage = document.getElementById("productImage").value;

        // Log the values (you can replace this with your own logic to handle the form data)
        console.log("Product Title:", productTitle);
        console.log("Product Description:", productDescription);
        console.log("Cross Price:", crossPrice);
        console.log("Main Price:", mainPrice);
        console.log("Availability:", availability);
        console.log("Department:", department);
        console.log("Category:", category);
        console.log("Product Image:", productImage);
    }
</script>

</body>
</html>
