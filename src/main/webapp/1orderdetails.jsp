<%@page import="com.example.superhim.pojo.PlaceOrderPojo"%>
<%@page import="java.util.List"%>
<%@page import="com.example.superhim.dao.PlaceOrderDao"%>
<%@page import="com.example.superhim.pojo.BillingAddressPojo"%>
<%@page import="com.example.superhim.dao.BillingAddressDao"%>
<%@page import="com.example.superhim.GetApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order details</title>

    <style>
        body{
            width : 100%;
        }
        .container{
            height: auto;
            width: 60%;
            border: 2px solid lightgrey;
            box-shadow: lightgrey 2px 2px 5px;
            margin: auto;
            padding: 20px  10px 20px 30px;
            box-sizing: border-box;
        }

        .sn{
            /* width: 180px; */
            height: 40px;
            font-size: 20px;

        }
        #orderform{
            /* width: 30%; */
            display:flex;
            flex-direction: column;
        }
        #dopt{
            height: 30px;

        }
        button{
            width: 100%;
            height: 30px;
            font-size: 20px;
            font-weight: bolder;
            margin-top: 10px;
            background-color: greenyellow;
            border: none;
            border-radius: 12px;
        }
    </style>
</head>
<body>
<%@include file="Message/message.jsp" %>
<%
	int totalPrice = 0;
	ApplicationContext ac = GetApplicationContext.getApplicationContext();
	BillingAddressDao bAD = ac.getBean(BillingAddressDao.class);
	Iterable<BillingAddressPojo> itr = bAD.findAll();
	PlaceOrderDao pod = ac.getBean(PlaceOrderDao.class);
	
	
	int k = 1;
	for(BillingAddressPojo bap: itr){
	String orderid = bap.getOrderid(); 
	List<PlaceOrderPojo> list =  pod.readOrderIdwise(orderid);
	String check = bap.getIsDelivered();
	if(check.equalsIgnoreCase("No"))
	{	
%>
    <div class="container">
        <div class="sn">SN: <%=k %></div>
        <div class="sn">Name: <%=bap.getFirstname()%></div>
        <div class="sn">Country: <%=bap.getCountry()%></div>
        <div class="sn">Address: <%=bap.getAddress()%></div>
        <div class="sn">City: <%=bap.getCity()%></div>
        <div class="sn">State: <%=bap.getState()%></div>
        <div class="sn">Postal code: <%=bap.getPostcode()%></div>
        <div class="sn">Order Phone No: <%=bap.getPhonenumber()%></div>
        <div class="sn">Email: <%=bap.getEmail()%></div>
        <div class="sn">Note: <%=bap.getNotes()%></div>
        <div class="sn">Mobile: <%=bap.getMobile()%></div>
        <div class="sn">Date and time: <%=bap.getDatetime()%></div>
        <%
        
	for(PlaceOrderPojo pop : list)
	{
		int count = pop.getCount();
		totalPrice = totalPrice + Integer.parseInt(pop.getMainprice())*count;
		
		if(!bap.getCharge().equalsIgnoreCase("Free")){
			totalPrice =totalPrice+Integer.parseInt(bap.getCharge());
		}
		%>
        <div class="sn" style="height:auto">Image:</div><img src="myown/productimage/<%=pop.getProductimage()%>" alt="1" height="80px" width="80px">
        <div class="sn">Product Title: <%=pop.getProducttital()%></div>
        <div class="sn">Product Price: <%=pop.getMainprice()%></div>
        <div class="sn">Quantity: <%=count%></div>
        
            <%
	}
    //details of address closing        
	%>
        <div class="sn">Total Price: &#x20B9;<%=totalPrice%></div>
	<form action="orderform" id="orderform" method="post">
            <select name="sn" id="dopt"  required="required">
                <option  id="" value="" disabled selected>Select any one</option>
                <option value="<%=bap.getSn() %>"  >Delivered</option>
                <option value="Not deliver">Not delivered</option>
            </select>
            <button type="submit">Delivered</button>
        </form>


 
    </div>
    <% 
    k++;
	}
	}%>
    
</body>
</html>