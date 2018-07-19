

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




        <h1 style="color:blue;text-align: center;">eXzaTech</h1>
        <form action="logout" method="post">
            <h3>Welcome ${user}</h3>
            <input type="submit" value="logout">  <br>  <br>      

        </form>
            <br><br>
            <table width="75%">
        <form action="create_customer.jsp" > 
            <tr><th><label for="CUSTOMER">CUSTOMER:</label></th>
                <td> <input type="submit"  value="Create Customer"></td>
        </form>

         <form action="cc_viewlist" method="post">

           <td> <input type="submit"  value="View Customers"></td></tr>
            
        </form>
        <form action="create_product.jsp">

            <tr><th>  <label for="PRODUCT"> PRODUCT:</label></th>
                <td><input type="submit"  value="Create Product"></td>
        </form>
        <form action="cp_viewlist" method="post">

            
            <td> <input type="submit"  value="View Products"></td></tr>
        </form>
                <form action="create_tax.jsp" > 
            <tr><th><label for="TAX">TAX:</label></th>
                <td> <input type="submit"  value="Create TAX"></td>
        </form>

         <form action="tax_viewlist" method="post">

           <td> <input type="submit"  value="View TAXES"></td></tr>
            
        </form>
                
    </table>
            
            <br><br>
       <form action="customer_entry" method="post" >
                   <!currency.jsp>
<table width="75%">
<tr> <th>   CREATE INVOICE:</th>
    <td><input type="submit"  value="Create Invoice"></td></tr>
        </table>
        </form>



        <form action="search" method="post">
            
            <table width="75%">
                <tr><th> <label for="search">SEARCH INVOICES:</label></th></tr>
                <br>
            <tr><th>  <label>FROM</label> </th><td><input type="date" value="from" name="from"></td></tr>
            <tr><th><label>TO</label></th><td><input type="date" value="to" name="to"></td></tr>
            <br><br>
            <tr><td></td><td><input type="submit"  value="Search"></td></tr>
            </table>
        </form>


    </body>
</html>
