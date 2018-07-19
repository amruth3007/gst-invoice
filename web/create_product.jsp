


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Entry</title>
 
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

        <form action="logout" method="post">
            <h1>Welcome root1</h1>
            <input type="submit" value="logout">  <br>  <br>      

        </form>


        <h1>Add New Product</h1>  
        <form action="cp_saveProduct" method="post">  
            <table> 
                <tr><td>Item(*don't use blank space*)</td><td><input type="text" name="item" required/></td></tr>
                <tr><td>product ID:</td><td><input type="text" name="product_id" required/></td></tr>
                <tr><td>product Name:</td><td><input type="text" name="product_name" maxlength="30"required/></td></tr>  
                <tr><td>Price per Unit:(INR)</td><td><input type="text" name="price" required/></td></tr>    

                <tr><td colspan="2"><input type="submit" value="Save Product"/></td></tr>  
            </table>  
        </form>  <br><br>



    </body>



</html>      
