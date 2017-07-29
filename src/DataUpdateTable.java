import java.io.*; 
import java.sql.*; 
import javax.servlet.*; 
import javax.servlet.http.*; 

public class DataUpdateTable extends HttpServlet	{
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
				out.println("<td> Name </td>");
				out.println("<td> Roll No. </td>");
				out.println("<td> Class </td>"); 
				out.println("<td> Mobile Number </td>"); 
			out.println("</tr>"); 

				
			String nm = req.getParameter("name");
			String roll = req.getParameter("roll");
			String clas = req.getParameter("clas");
			String mono = req.getParameter("mono");

			req.setAttribute("NAME", nm);
			req.setAttribute("ROLL", roll);
			req.setAttribute("CLAS", clas);
			req.setAttribute("MONO", mono);
			req.setAttribute("EDIT", "Y");
				
			req.getRequestDispatcher("/index.jsp").forward(req, res);

	}

	
	public void doPost(HttpServletRequest req, HttpServletResponse res)
	throws IOException, ServletException	{
		
		res.setContentType("text/html"); 
		PrintWriter out = res.getWriter();
		PrintWriter pwinsert = res.getWriter();
//		PrintWriter pwdelete = res.getWriter();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null; 
		Statement st = null; 


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
			
			String nm = req.getParameter("name"); 
			String roll = req.getParameter("roll"); 
			String clas = req.getParameter("clas"); 
			String mono = req.getParameter("mono"); 
			
			
			if(req.getParameter("choise")==null)	{
				ps = con.prepareStatement("update student set name=?, rollno=?, class=?, mobileno=? where rollno=?"); 
				ps.setString(1,nm); 
				ps.setString(2,roll); 
				ps.setString(3,clas); 
				ps.setString(4,mono); 
				ps.setString(5,roll); 
				
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
	}
}