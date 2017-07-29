<html>
    <head>
        <title>Data insertion form</title>
    </head>

    <body>

    <%
    String action="/SimpleServlet/DataInsertTable";
    String method="get";

    if(request.getAttribute("EDIT")!=null)	{
    	action="/SimpleServlet/DataUpdateTable";
	    method="post";
    }
    %>

    <form method= "<%=method%>" action="<%=action%>">
	    <h1><center>Enter the required information</center></h1>

    	<table>
    		<tr>
				<td>Name</td>
				<td> :- </td>
            	<%if(request.getAttribute("NAME")!=null){ %>
            	<td><input type="text" name="name" value="<%=request.getAttribute("NAME")%>"></input>

	            <%}else{ %>
    	        <td><input type="text" name="name"></input>
        	    <%} %>
            	</td>
        </tr>

        <tr>
            <td>Roll Number</td>
            <td> :- </td>
           	<%if(request.getAttribute("ROLL")!=null){ %>
            <td><input type="text" name="roll" value="<%=request.getAttribute("ROLL")%>"></input>

            <%}else{ %>
            <td><input type="text" name="roll"></input>
            <%} %>
            </td>       
        </tr>

        <tr>
            <td>Class </td>
            <td> :- </td>
             <%if(request.getAttribute("CLAS")!=null){ %>
            <td><input type="text" name="clas" value="<%=request.getAttribute("CLAS")%>"></input>

            <%}else{ %>
            <td><input type="text" name="clas"></input>
            <%} %>
            </td>
        </tr>

        <tr>
            <td>Mobile Number </td>
            <td> :- </td>
            <%if(request.getAttribute("MONO")!=null){ %>
            <td><input type="text" name="mono" value="<%=request.getAttribute("MONO")%>"></input>

            <%}else{ %>
            <td><input type="text" name="mono"></input>
            <%} %>
            </td>
        </tr>

        <tr>
            <td></td>
            <td><input type="submit" value="Submit" width="76"></input></td>
        </tr>

    </table>

    </form>

    </body>
</html>