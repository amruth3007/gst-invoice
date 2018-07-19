<%@page import="javax.swing.border.TitledBorder"%>
<%@page import="java.sql.*"%>
<%@page import="java.io.*"%>
<%@page import="com.darwinsys.spdf.PDF,com.darwinsys.spdf.Page,com.darwinsys.spdf.Text,com.darwinsys.spdf.MoveTo"%>

<%
PreparedStatement ps=null;
ResultSet rs=null;
try
{
 String donorid =request.getParameter("donorid");
 PrintWriter out1 = response.getWriter();
  response.setContentType("application/pdf");
 
  response.setHeader("Content-disposition","inline; filename='Report.pdf'");
  
  String query = "select * from report where donorid = "+donorid+"";          //Fetching data from table
  
  
  Class.forName("com.mysql.jdbc.Driver");
  Connection conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/Blood", "root", "root");
   
  ps=conn.prepareStatement(query);                // executing query
  rs=ps.executeQuery();
   
  
  PDF p = new PDF(out1);
  Page p1 = new Page(p);
  p1.add(new MoveTo(p, 200, 700));
  
  
   p1.add(new Text(p," "));
  p1.add(new Text(p,"------------ Details------------"));
  p1.add(new Text(p," "));
  p1.add(new Text(p," "));
     while(rs.next())
    {
          // fetch & writing records in pdf files
   p1.add(new Text(p,"Donor-Id            -         "+rs.getString(1)+" "));
   p1.add(new Text(p," "));

   p1.add(new Text(p,"Name           -       "+rs.getString(2)+" "));
    }
    p1.add(new Text(p," "));
          
    p.add(p1);
 p.setAuthor("name");
 
 p.writePDF();

   conn.close();  //db connection close
}
catch(Exception e)
{
        e.printStackTrace();
            out.println(e);
         
}


%>

