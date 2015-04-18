<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		request.setCharacterEncoding("UTF-8");
		if(request.getAttribute("msg") != null){
	%>
	
			<br>
			<font color="red"><%= request.getAttribute("msg") + "-------" %></font>
			<br><br>
	<% 
			
		}
	%>
	
	<form action="addCustomer.do" method="post">
		
		<table>
			<tr>
				<td>ID:</td>
				<td><input type="text" name="id" value="<%= request.getParameter("id") == null? "": request.getParameter("id") %>"/></td>
			</tr>
			<tr>
				<td>CustomerName:</td>
				<td><input type="text" name="name" value="<%= request.getParameter("name") == null? "" : request.getParameter("name") %>"/></td>
			</tr>
			
			<tr>
				<td>Address:</td>
				<td><input type="text" name="address" value="<%= request.getParameter("address") == null? "" : request.getParameter("address") %>"/></td>
			</tr>
			
			<tr>
				<td>Phone:</td>
				<td><input type="text" name="phone" value="<%= request.getParameter("phone") == null? "" : request.getParameter("phone") %>"/></td>
			</tr>
			
			<tr>
				<td>
					<input type="submit" value="Submit"/>
				</td>
			</tr>
		</table>
	</form>
	
	<a href="query.do">-----------------------------------------Query</a>
</body>
</html>