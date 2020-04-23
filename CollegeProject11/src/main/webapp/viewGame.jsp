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
	Welcome
	<br>

	<%
		Games gm = (Games) request.getAttribute("gameDeatils");
	%>
	Game Name:<%=gm.getGamename()%>
	Game Date:<%=gm.getGamedate()%>
	<%
		List<ViewBean> viewBeans = (List<ViewBean>) request.getAttribute("viewBeans");
	%>
	<table boder=5>
		<tr>
			<th>Button Id</th>
			<th>User Count</th>
		</tr>
		<%
			for (int i = 0; i < viewBeans.size(); i++) {
				out.print("<tr><td>" + viewBeans.get(i).getButtuonid() + "</td>" + "<td>"
						+ viewBeans.get(i).getTotaluser() + "</td></tr>");
			}
		%>
	</table>
</body>
</html>