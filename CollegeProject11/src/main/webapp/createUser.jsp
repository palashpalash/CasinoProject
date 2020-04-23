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
	<form action="createUser" method="post">
		<Input type="text" name="mailId" placeholder="Enter MailId"> <Input type="text"
			name="userName" placeholder="Enter Name"> <Input type="submit" value="createUser">
	</form>
</body>
</html>