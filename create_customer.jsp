
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

            
            
            
            
            
            
            
            
            
 <form action="cc_saveCustomer" method="post">  
                <label> <h3>Enter the Customer Details:</h3></label><br><br> 
<table> 
<tr><td>Customer(*don't use blank space*)</td><td><input type="text" name="customer" required/></td></tr> 
<tr><td>Customer Type</td><td> <select name="customer_type">
                        <option value="company" >COMPANY</option>
                        <option value="individual" >INDIVIDUAL</option>
                        
        </select><br></td></tr>
<tr><td>Customer Name</td><td><input type="text" name="customer_name" required/></td></tr>
<tr><td>Street:</td><td><input type="text" name="street" required/></td></tr>  
<tr><td>Postal code:</td><td><input type="text" name="postal_code" required/></td></tr>  
<tr><td>Country ID:</td><td><input type="text" name="country_id" required/></td></tr>
<tr><td>Phone:</td><td><input type="tel" name="phone" required/></td></tr> 
<tr><td>Mobile:</td><td><input type="tel" name="mobile" placeholder="123-456-7890" required/></td></tr>  

 <tr><td>Email:</td><td><input type="email" name="email" placeholder="abc@xyz.com" required/></td></tr>    
 <tr><td>PAN no:</td><td><input type="text" name="pan_no" required/></td></tr>  
<tr><td>GST no:</td><td><input type="text" name="gst_no" required/></td></tr>
 
<tr><td colspan="2"><input type="submit" value="Save Customer"/></td></tr>  
</table>  
</form>
            
            <br/> 
    </body>
</html>

