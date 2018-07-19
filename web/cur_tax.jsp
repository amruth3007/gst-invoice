<%-- 
    Document   : cur_tax
    Created on : 16 Jul, 2018, 3:32:43 PM
    Author     : amruth
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Choose currency</title>
 
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
  
                
                <h3>Choose Currency</h3> 
        
        <form action="cur_tax.jsp">
            <table>
 
                <tr><td><input type="radio" name="currency" value="inr" >INR</td></tr>
                <tr><td><input type="radio" name="currency" value="usd" >USD</td></tr>
                <tr> <td> <input type="radio" name="currency" value="other" >Other</td></tr>
               
               
            </table><br>
            <table>
 
                <tr><td><input type='submit' value='submit'/></td></tr>
            </table>
</form>
                <br><br><br>
       <%
           try
           {
           String currency=request.getParameter("currency");
           if(currency.equals("inr"))
           {
            out.print("<h3>CURRENCY:INR</h3>");
            out.print("You have chosen Indian currency and hence GST charges are applicable...");
            out.print("<form action='choose_tax' method='post' >"); 
            out.print("<table>");
               out.print("<tr><td></td><td><input type='hidden'  name='currency' value='inr'/></td></tr>"); 

            out.print("<tr><td></td><td><input type='hidden'  name='cur_value' value='1' required/></td></tr>"); 

            out.print("<tr><td colspan='2'><input type='submit' value='You have chosen INR and click here to proceed'/></td></tr>"); 
            out.print("</table>");
           }
           else if(currency.equals("usd"))
           {out.print("<h3>CURRENCY:USD</h3>");
            out.print("You have chosen the currency of type USD($) and please provide the value of INR equals to 1 USD($)");
            out.print("<form action='choose_tax' method='post' >"); 
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden'  name='currency' value='usd'/></td></tr>"); 

            out.print("<tr><td>1 USD(in terms of INR)=</td><td><input type='text' placeholder='INR' name='cur_value' required/></td></tr>"); 
            out.print("<tr><td colspan='2'><input type='submit' value='You have chosen USD and click here to proceed'/></td></tr>"); 
            out.print("</table>");
           }
           else 
           {out.print("<h3>CURRENCY:Unknown</h3>");
            out.print("You have chosen the currency of unknown type and please provide the value of INR equals to 1 unit of currency");              
            out.print("<form action='choose_tax' method='post' >"); 
            out.print("<table>");
            out.print("<tr><td></td><td><input type='hidden'  name='currency' value='other'/></td></tr>"); 

            out.print("<tr><td>1 unit(in terms of INR)=</td><td><input type='text' placeholder='INR' name='cur_value' required/></td></tr>"); 
            out.print("<tr><td colspan='2'><input type='submit' value=' click here to proceed'/></td></tr>"); 
            out.print("</table>");
           }}
           catch(Exception e)
           {
               e.printStackTrace();
           }
           %>
    </body>
</html>
