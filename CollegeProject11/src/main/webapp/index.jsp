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
		if (request.getAttribute("Error") == null)

		{

		}

		else {
			out.print("<p>" + request.getAttribute("Error") + "</p>");
		}
	%>
	<form action="login" method="post" onsubmit="return validateSubmit()">
		<input type="text" name="a1"> <br> <input type="text"
			name="a2"> <br> <select name="a3">

			<option value="CompanyAdmin">CompanyAdmin</option>
			<option value="GameAdmin">GameAdmin</option>
		</select> <input type="submit" value="LogIN">
	</form>
	<a href="createCompany.jsp">Click Here for Company Registration</a>
</body>
</html>