
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TAX Entry</title>
 
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

           
            
            
 <form action="tax_save" method="post">  
                <label> <h3>Enter the TAX Details:</h3></label><br><br> 
<table> 

<tr><td>Tax Name(*don't use blank space*)</td><td><input type="text" name="tax_name" required/></td></tr>
<tr><td>Tax Value %</td><td><input type="text" name="tax_value" required placeholder="In percentage"/></td></tr>

 
<tr><td colspan="2"><input type="submit" value="Save TAX"/></td></tr>  
</table>  
</form>
            
            <br/> 
    </body>
</html>

