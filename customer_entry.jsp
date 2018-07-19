
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.io.PrintWriter"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Entry</title>
 
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
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
 
            %>
            
     
        
        <form action="logout" method="post">
            <h1>Welcome ${user}</h1>
            <input type="submit" value="logout">  <br>  <br>      
            
        </form>

            
 <form action="selectCustomer" method="post">  
               
<table> 
    
    <tr><label> <h3>Select customer:</h3></label><br><br> 
            <select name="customer">
                <%
                    
 System.out.println("sevlet code in jsp");
        String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
        String sql = "select * from cc_customer;";
      
        PrintWriter out1 = response.getWriter();
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
        
            
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            
            while (rs.next()) {
                
            out1.print("<option value="+rs.getString(1)+" >'choose'</option>"); 
            }
            
            out1.print("<option value='create_customer' >Create customer</option> ");
        }catch(Exception e)
        {
            e.printStackTrace();
        }


                    %>
                                    
        </select><br></td></tr>
    <tr><td colspan="2"><input type="submit" value="Select Customer"/></td></tr> 
    </table>
                 
            
            <br/> 
    </body>
</html>
