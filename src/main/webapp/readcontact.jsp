<%@include file="auth/auth.jsp" %>

<%@page import="com.example.superhim.pojo.ContactPojo"%>
<%@page import="java.util.List"%>
<%@page import="com.example.superhim.dao.ContactDao"%>
<%@page import="com.example.superhim.GetApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    
<h3><%@include file="Message/message.jsp"%></h3>

<% 
ApplicationContext ac = GetApplicationContext.getApplicationContext();
ContactDao c = ac.getBean(ContactDao.class);
List<ContactPojo>list = c.readContact();
for(ContactPojo cp :list){
	
%>
SN: <%=cp.getSn() %><br>
NAME: <%=cp.getName() %><br>
EMAIL: <%=cp.getEmail() %><br>
MESSAGE: <%=cp.getMessage()%><br>
DATE: <%=cp.getDatetime() %><br>
<form action="deletecontact" method="post"><br>
<input type="hidden" name= "sn" value="<%=cp.getSn()%>">
<button>Delete</button>
</form><br><br>

<%} %>
</body>
</html>