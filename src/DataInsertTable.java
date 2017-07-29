import java.io.*; 

import java.sql.*; 


import javax.servlet.*; 

import javax.servlet.http.*; 

public class DataInsertTable extends HttpServlet	{
	public void doGet(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException	{
		
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		PrintWriter pwinsert = res.getWriter();
//		PrintWriter pwdelete = res.getWriter(); 

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Statement st = null;
	
		
		out.println("<html>");
			out.println("<head>");
				out.println("<title>User Data</title>"); 
			out.println("</head>"); 
			
			out.println("<body>"); 
			out.println("<center><u><h1>User Data</h1></u>"); 

			out.println("<form name='form' >"); 

//			int a = 2; 
			out.println("<table border="+2+ "> ");

			out.println("<tr>");
				out.println("<td> Select </td>");
				out.println("<td> Name </td>");
				out.println("<td> Roll No. </td>");
				out.println("<td> Class </td>");
				out.println("<td> Mobile Number </td>");
				out.println("<td> Edit </td>");
			out.println("</tr>");


			String nm = req.getParameter("name");
			String roll = req.getParameter("roll");
			String clas = req.getParameter("clas");
			String mono = req.getParameter("mono");
			
			
			try	{
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch(ClassNotFoundException ex)	{
				System.out.println("driver not loaded");
				System.exit(0); 
			} 

			String URL = "jdbc:oracle:thin:@192.168.106.87:1521:ORA11G";
			String Username = "pratik";
			String Password = "pratik";
			
			try	{
				con = DriverManager.getConnection(URL,Username,Password); 
				
				if(req.getParameter("choise")==null)	{
					ps = con.prepareStatement("INSERT INTO student (name, rollno, class, mobileno) VALUES (?, ?, ?, ? )"); 
					ps.setString(1,nm);
					ps.setString(2,roll); 
					ps.setString(3,clas); 
					ps.setString(4,mono); 

					int i = ps.executeUpdate(); 
					pwinsert.println(i); 

					if(i!=0)	{ 
						pwinsert.println("Your data has been stored in the database"); 
					} 
					else	{ 
						pwinsert.println("Your data could not be stored in the database"); 
					} 
				}
			} 
			catch(Exception e)	{
				pwinsert.println(e.getMessage());
			} 

			String idr=null;

			if(req.getParameter("choise")!=null)	{
				nm = req.getParameter("choise");
				idr = "DELETE from student WHERE name ='"+nm+"'"; 
				
				try	{
					st=con.createStatement(); 
					rs = st.executeQuery(idr); 
				} 
				catch (Exception e)	{ 
					e.getMessage(); 
					System.out.println("Error " +e); 
				} 

				System.out.println("Data deleted..."); 
			}


//			res.sendRedirect("DataInsertTable.java"); 


			idr = "SELECT * FROM student WHERE name IS NOT NULL"; 
			
			try	{
				st=con.createStatement(); 
				rs = st.executeQuery(idr); 
			}
			catch (Exception e)	{ 
				e.getMessage(); 
				System.out.println("Error " +e); 
			} 
			
			
			try {
				while (rs.next())	{ 
					out.println("<tr>"); 
						out.println("<td>" + "<input type=\"radio\" name=\"choise\" value=\"" + rs.getString(1) + "\" /> </br>" + "</td>");
						out.println("<td>" + rs.getString(1) + "</td>" + "\t <td>" + rs.getInt(2) + "</td>" + "\t <td>" + rs.getString(3) + "</td>" + "\t <td>" + rs.getString(4)); 
						out.println("<td>" + "<a href='"+req.getContextPath()+"/DataUpdate?mode=EDIT&name="+rs.getString(1)+"&roll="+rs.getInt(2)+"&clas="+rs.getString(3) +"&mono="+rs.getString(4)+"'>Edit</a> </br>" + "</td>"); 
					out.println("</tr>"); 
				} 
				
				out.println("<tr>"); 
					out.println("<td> </td>"); 
					out.println("<td> </td>"); 
					out.println("<td>" + "<input type=\"submit\" value=\"Delete\" />" + "</td>");
					out.println("<td> </td>"); 
					out.println("<td> </td>"); 
				out.println("</tr>"); 
			} 
			catch (Exception e) { 
				e.getMessage(); 
				System.out.println("Error" +e); 
			} 

			
			try { 
				rs.close(); 
			} 
			catch (Exception e) { 
				e.getMessage(); 
				System.out.println("Error" +e); 
			} 
			
			try { 
				st.close(); 
			} 
			catch (Exception e) { 
				e.getMessage(); 
				System.out.println("Error" +e); 
			} 
			
			
			out.println("</table>"); 
	
			out.println("</form>"); 
	
			out.println("</center>"); 
	
			out.println("</body>"); 
		out.println("</html>"); 
		
	out.close(); 
	
	} 
}