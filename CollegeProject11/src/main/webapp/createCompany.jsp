<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
		if (request.getAttribute("message") == null) {

		} else {
			out.print("<p>" + request.getAttribute("message") + "<p>");
		}
	%>
<form action="createCompany" method="post">
<input type="text" name="CompanyName" placeholder="Enter Company Name">
<input type="password" name="CompanyPassword" placeholder="Enter Password">
<input type="password" name="CompanyPassword1" placeholder="RE-Enter Password">
<input type="submit" value="REGISTERCOMPANY">
</form>
</body>
</html>