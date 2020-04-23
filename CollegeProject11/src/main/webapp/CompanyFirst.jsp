<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.*"%>
<%@page import="com.example.demo.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<a href="createUser.jsp">CREATE USER</a>
	<%
		if (request.getAttribute("gameList") != null) {
			List<Games> gms1 = (List<Games>) request.getAttribute("gameList");
	%>

	Existing Games
	<table>
		<tr>
			<th>Game Id</th>
			<th>Game Name</th>
			<th>Game Date</th>
			<th>Play Games</th>
		</tr>
		<%
			for (int i = 0; i < gms1.size(); i++) {
					out.print("<tr>");
					out.print("<td>");
					out.print(gms1.get(i).getGameid() + "</td>");
					out.print("<td>");
					out.print(gms1.get(i).getGamename() + "</td>");
					out.print("<td>");
					out.print(gms1.get(i).getGamedate() + "</td>");
					out.print("<td>");
					out.print("<form action='playGame' method='post'><input type='submit' value='Play Games' name='"+gms1.get(i).getGameid()+"' id='" + gms1.get(i).getGameid() + "' ></td>");
					out.print("<td>");
					out.print("<input type='hidden' name='a1' value='"+gms1.get(i).getGameid()+"'></td></form>");
				}
		%>
	</table>
	<%
		}
	%>
</body>
</html>