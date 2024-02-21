<%
	String login = (String)session.getAttribute("login");		
		if(login == null)
			{
			response.sendRedirect("adminlogin.jsp");
			return;
			}
%>