

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="one.customerz"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>output</title>
        <link rel='shortcut icon' href='images/exza.png'>
    </head>
    <body>
        
        
        <style>

            .navbar {
                overflow: hidden;
                background-color: #333;
                font-family: Arial, Helvetica, sans-serif;
            }

            .navbar a {
                float: left;
                font-size: 16px;
                color: white;
                text-align: center;
                padding: 14px 16px;
                text-decoration: none;
            }

        </style>
        <div class="navbar">
            <a href="http://localhost:8080/exzatech/welcome.jsp">Home</a>
            <a href="http://localhost:8080/exzatech/menulogout.jsp">Logout</a>
            <a href="https://www.google.com/">Search From Google</a>
        </div>   


        <% 
            
            
            response.setHeader("cache-control", "no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma", "no-cache");//HTTP 1.0
            response.setHeader("Expires", "0");//Proxies

            if (session.getAttribute("user") == null) {
                response.sendRedirect("login.jsp");
            }

        %>




        <h1 style="color:blue;text-align: center;">Invoice Page</h1>
        <form action="logout" method="post">
            <h3>Welcome ${user}</h3>
            <input type="submit" value="logout">  <br>  <br> 
        </form>
        <table width='100%'>
            
         <form action="singleOutput.jsp"> 
        <tr><th><label>Choose Customer:</label></th>
            <th> <select name='customer'>

        
       
        
        <%
            String sql = "select customer from cc_customer;";
  String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
  try {
 
     Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
   PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.print("<option value=" + rs.getString(1) + " >" + rs.getString(1) + "</option>");
            }

  }
  catch(SQLException e) {
    e.printStackTrace();
  }
%>
        <option value='create_customer'>Create new customer</option>
    
                 </select></th>
                 <th><input type='submit' value='Submit'/></th></tr> 
             </form>  </table>


   
<%
String customer = request.getParameter("customer");  
                      
                       try {
            if (customer.equals("create_customer")) {
                response.sendRedirect("create_customer.jsp");
            } else {
                cc_customer c = cc_custDao.getCustomerByID(customer);
          out.print("<tr><th>Email</th><th>Street</th><th>Postal code</th><th>GST No</th><th>PAN No</th><th>Invoice Date</th>");
            }  }catch(Exception e)
                    {
                    e.printStackTrace();
                    }

    %>
        
      
                </body>
</html>
