

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome </title>
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




        <h1 style="color:blue;text-align: center;">select currency</h1>
        <form action="logout" method="post">
            <h3>Welcome ${user}</h3>
            <input type="submit" value="logout">  <br>  <br>      

        </form>
    

        <form action="singleOutput.jsp">
            
            <table width="75%">
               <tr><td>Choose Currency</td><td> <select name="currency">
                        <option value="inr" >INR</option>
                        <option value="usd" >USD</option>
        </select><br></td></tr>
            <tr><td></td><td><input type="submit"  value="Search"></td></tr>
            </table>
        </form>


    </body>
</html>
