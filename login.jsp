

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
       <head>
        <title>eXzaTech</title>
 
     <link rel='shortcut icon' href='images/exza.png'>       
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        
        <%
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
        
            %>
        <marquee style="color:red;">eXzaTech consulting and services pvt. ltd.</marquee>
            <h1 style="color:blue;text-align: center;">eXzaTech</h1>
            
           
            
            
            <form action="login" method="post" >
            <table  width="50%">
                <tr>  <th> <label> Root Login:</label></th><th><input type="text" name="user" placeholder="login entry"></th></tr><br>
            
                <tr>  <th> <label>Password:</label><th><input type="password" name="pwd" placeholder="login key"><br></th></tr><br>
            </table>
            
            
            <br>
                <table  width="60%">
            <tr> <th> <input type="submit" value="login" style="background-color: skyblue;"></th></tr>
            </table>
        </form>
    
    </body>
</html>
