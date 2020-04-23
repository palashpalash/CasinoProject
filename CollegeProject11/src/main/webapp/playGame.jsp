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
<form id="setGameDetails">
<input type="hidden" value=<%=gm.getGameid() %> name="gameid">
<input type="hidden"  id="personid" name="personid">
<input type="hidden"  id="buttonid" name="buttonid">
</form>
	<%
		List<UserBean> users = (List<UserBean>) request.getAttribute("userlist");
	%>
	<select name="userid" class="person">
		<option value='selectOne'>selectOne</option>
		<%
			for (int i = 0; i < users.size(); i++)
				out.print("<option value='" + users.get(i).getUserid() + "'>" + users.get(i).getUserfullname() + "/"
						+ users.get(i).getUsername() + "</option>");
		%>

	</select>
	<br>
	<br>
	<table border=5>
	<tr>
	<td><input type="button" value="1" id="buttonid1" onclick="butonClicked(1)" style="background-color: skyblue;"></td>
	<td><input type="button" value="2" id="buttonid2" onclick="butonClicked(2)" style="background-color: skyblue;"></td>
	<td><input type="button" value="3" id="buttonid3" onclick="butonClicked(3)" style="background-color: skyblue;"></td>
	<td><input type="button" value="4" id="buttonid4" onclick="butonClicked(4)" style="background-color: skyblue;"></td>
	</tr>
	<tr>
	<td><input type="button" value="5" id="buttonid5" onclick="butonClicked(5)" style="background-color: skyblue;"></td>
	<td><input type="button" value="6" id="buttonid6" onclick="butonClicked(6)" style="background-color: skyblue;"></td>
	<td><input type="button" value="7" id="buttonid7" onclick="butonClicked(7)" style="background-color: skyblue;"></td>
	<td><input type="button" value="8" id="buttonid8" onclick="butonClicked(8)" style="background-color: skyblue;"></td>
	</tr>
	<tr>
	<td><input type="button" value="9" id="buttonid9" onclick="butonClicked(9)" style="background-color: skyblue;"></td>
	<td><input type="button" value="10" id="buttonid10" onclick="butonClicked(10)" style="background-color: skyblue;"></td>
	<td><input type="button" value="11" id="buttonid11" onclick="butonClicked(11)" style="background-color: skyblue;"></td>
	<td><input type="button" value="12" id="buttonid12" onclick="butonClicked(12)" style="background-color: skyblue;"></td>
	</tr>
	<tr>
	<td><input type="button" value="13" id="buttonid13" onclick="butonClicked(13)" style="background-color: skyblue;"></td>
	<td><input type="button" value="14" id="buttonid14" onclick="butonClicked(14)" style="background-color: skyblue;"></td>
	<td><input type="button" value="15" id="buttonid15" onclick="butonClicked(15)" style="background-color: skyblue;"></td>
	<td><input type="button" value="16" id="buttonid16" onclick="butonClicked(16)" style="background-color: skyblue;"></td>
	</tr>
	</table>
	
	
	<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
	<script>
	var selectedPerson="selectOne";
	$("select.person").change(function(){
		//loop for recontruct intilizaed state
		for(var i=1;i<=16;i++)
			{
			//button enabling
			document.getElementById(("buttonid")+i).disabled = false;
				document.getElementById(("buttonid")+i).style.backgroundColor="skyblue";
			}
         selectedPerson = $(this).children("option:selected").val();
         alert(selectedPerson);
         if(selectedPerson!='selectOne')
        	 {
         document.getElementById("personid").value =selectedPerson;
         $.ajax({

 			url : '/getGameDetails',
 			data : $("#setGameDetails").serialize(),
 			method : 'post',
 			error : function() {
 				alert("csfhgdhb");
 				
 			},
 			success : function(msg) {
 			for(var i=0;i<msg.length;i++)
 				{
 				//button disabled
 				document.getElementById(("buttonid")+msg[i]).disabled = true;
 				document.getElementById(("buttonid")+msg[i]).style.backgroundColor="grey";
 				}
 				

 			}

 		});
        	 }
    });
	
	function butonClicked(a)
	{
		if(selectedPerson!='selectOne')
			{
		alert(a);
		//button disable
		document.getElementById(("buttonid")+a).disabled = true;
		document.getElementById(("buttonid")+a).style.backgroundColor="grey";
		//setting person and buttonid
		document.getElementById("personid").value =selectedPerson;
		document.getElementById("buttonid").value=a;
		
		
		$.ajax({

			url : '/setGameDetails',
			data : $("#setGameDetails").serialize(),
			method : 'post',
			error : function() {
				alert("csfhgdhb");
				
			},
			success : function(msg) {
			alert(msg);
				

			}

		});
			}
	}
	</script>
</body>
</html>