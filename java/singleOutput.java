
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class singleOutput extends HttpServlet {

    String customer;
    String product_id;
    String product;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("<title>INVOICE</title>");
        out.print("<link rel='shortcut icon' href='images/exza.png'>");

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");

        String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        
        String sql = "select customer from cc_customer;";
        out.println("<table width='100%'>");
        out.println(" <form action='singleOutput' method='post' > ");
        out.print("<tr><th><label>Choose Customer:</label></th><th> <select name='customer'>");

        //create customer_entry
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);

            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                out.print("<option value=" + rs.getString(1) + " >" + rs.getString(1) + "</option>");
            }

            out.print("<option value='create_customer'>Create new customer</option>");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        out.print(" </select></th>");
        out.println("<th><input type='submit' value='Submit'/></th></tr> ");
        out.println("<table>");
        out.println(" </form>");

        customer = request.getParameter("customer");
        try {
            if (customer.equals("create_customer")) {
                response.sendRedirect("create_customer.jsp");
            } else {

                cc_customer c = cc_custDao.getCustomerByID(customer);

                out.print("<table border='1' width='100%'");
                // out.println(" <form action='singleOutput' method='post' > ");
                out.print("<tr><th>Customer ID</th><th>Customer</th><th>Customer Type</th><th>Customer Name</th><th>Country ID</th><th>Phone</th><th>Mobile</th></tr>");
                out.print("<tr><td>" + c.getCustomer_id() + "</td><td>" + c.getCustomer() + "</td><td>" + c.getCustomer_type() + "</td><td>" + c.getCustomer_name() + "</td><td>" + c.getCountry_id() + "</td><td>" + c.getPhone() + "</td><td>" + c.getMobile() + "</td></tr>");
                out.print("<tr><th>Email</th><th>Street</th><th>Postal code</th><th>GST No</th><th>PAN No</th><th>Invoice Date</th>");

                out.print("<form action='welcome.jsp' method='post'>");
                out.print("<input type='hidden' name='customer' value='" + c.getCustomer() + "'/>");
                out.print("<th>Delete</th></tr>");
                //out.print("<th><input type='submit' value='Delete'/></th></tr>");

                out.print("<tr><td>" + c.getEmail() + "</td><td>" + c.getStreet() + "</td><td>" + c.getPostal_code() + "</td><td>" + c.getGst_no() + "</td><td>" + c.getPan_no() + "</td><th><input type='date' name='invoice_date' required/></th><th><input type='submit' value='Delete'/></th></tr>");
                //<th><input type='submit' value='Confirm'/></th>
                out.print("</form>");

                out.println("</table>");
                out.print("<input type='hidden' readonly='readonly' name='customer_id' value='" + c.getCustomer_id() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='customer' value='" + c.getCustomer() + "'/>");
                out.print("<input type='hidden' readonly='readonly'  name='customer_type' value='" + c.getCustomer_type() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='customer_name' value='" + c.getCustomer_name() + "'/>");

                out.print("<input type='hidden' readonly='readonly'  name='street' value='" + c.getStreet() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='postal_code' value='" + c.getPostal_code() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='country_id' value='" + c.getCountry_id() + "'/>");

                out.print("<input type='hidden' readonly='readonly' name='phone' value='" + c.getPhone() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='mobile' value='" + c.getMobile() + "'/>");

                out.print("<input type='hidden' readonly='readonly' name='email' value='" + c.getEmail() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='pan_no' value='" + c.getPan_no() + "'/>");
                out.print("<input type='hidden' readonly='readonly' name='gst_no' value='" + c.getGst_no() + "'/>");

                //  out.println("</form>");

                /*     int customer_id = Integer.parseInt(request.getParameter("customer_id"));
                
                String customer = request.getParameter("customer");
                String customer_type = request.getParameter("customer_type");
                String customer_name = request.getParameter("customer_name");
                String invoice_date = request.getParameter("invoice_date");

                String street = request.getParameter("street");
                String postal_code = request.getParameter("postal_code");
                String country_id = request.getParameter("country_id");
                String phone = request.getParameter("phone");
                String email = request.getParameter("email");
                String pan_no = request.getParameter("pan_no");
                String gst_no = request.getParameter("gst_no");

                customer c1 = new customer();
                c1.setCustomer_id(customer_id);
                c1.setCustomer(customer);
                c1.setCustomer_type(customer_type);
                c1.setCustomer_name(customer_name);
                c1.setInvoice_date(invoice_date);

                c1.setStreet(street);
                c1.setPostal_code(postal_code);
                c1.setCountry_id(country_id);
                c1.setPhone(phone);
                c1.setEmail(email);
                c1.setPan_no(pan_no);
                c1.setGst_no(gst_no);*/
 /*   int status=custDao.save(c1);  
        if(status>0){ 
            System.out.println("<p>Record saved successfully!</p>");
            
            request.getRequestDispatcher("SingleOutput").include(request, response);
        }else{  
            out.println("Sorry! unable to save record");  
        }
                 */
 
 
                //product
                String sql2 = "select item,product_id from cp_product;";
                out.println("<br>");
                out.println("<table width='100%'>");

                out.println(" <form action='singleOutput' method='get'>");
                out.print("<tr><th><label>Choose Product:</label></th><th> <select name='product'>");
                

                   /* action='singleOutput' method='post' 
request.setAttribute("message", "Hello world");
RequestDispatcher dispatcher = servletContext().getRequestDispatcher(url);
dispatcher.forward(request, response);                   
                   
                   */
                   
                   
                   
                   
                   
                   
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);

                PreparedStatement ps2 = con.prepareStatement(sql2);

                ResultSet rs = ps2.executeQuery();

                while (rs.next()) {

                    out.print("<option value=" + rs.getString(1) + " >" + rs.getString(1) + "</option>");

                    product_id = rs.getString(2);
                }

                out.print("<option value='create_product'>Create new Product</option>");
                out.print(" </select></th>");

                out.println("<th><input type='submit' value='Submit' onclick='product(request,response)'/></th></tr> ");
                out.println("<table>");
                out.println(" </form>");

                //select product
            
                        }

            
        } catch (Exception e) {

        }
   
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException { 
    PrintWriter out=response.getWriter();
         
        String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        try
        {
        product = request.getParameter("product");

                if (product.equals("create_product")) {
                    response.sendRedirect("create_product.jsp");
                } else {

                    String sql3 = "select product_id from cp_product where item=?;";

                    Class.forName("com.mysql.jdbc.Driver");

                    Connection con1 = DriverManager.getConnection(url, userMysql, pwdMysql);

                    PreparedStatement ps3 = con1.prepareStatement(sql3);
                    ps3.setString(1, product);
                    ResultSet rs = ps3.executeQuery();

                    if (rs.next()) {

                        product_id = rs.getString(1);
                    }

                    cp_product p = cp_prodDao.getProductById(product_id);;

                    out.print("<table border='1' width='100%'");
                    out.print("<tr><th>Item</th><th>Product ID</th><th>Product Name</th><th>Price per unit</th><th>Quantity:</th><th>HSN code:</th><th>Delete</th></tr>");

                    out.println(" <form action='saveServlet' method='post' > ");
            
                    out.print("<tr><td>" + p.getItem() + "</td><td>" + p.getProduct_id() + "</td><td>" + p.getProduct_name() + "</td><td>" + p.getPrice() + "</td>");
                    out.print("<td><input type='text' name='quantity' required/></td>");
                    out.print("<td><input type='text' name='hsn_code' required/></td>");

                    out.print("</form>");
                    out.print("<form action='cp_prodDelete' method='post'>");
                    out.print("<input type='hidden' name='pro_id' value='" + p.getPro_id() + "'/>");

                    out.print("<input type='hidden' readonly='readonly' name='item' value='" + p.getItem() + "'");
                    out.print("<input type='hidden' readonly='readonly'  name='product_id' value='" + p.getProduct_id() + "'");
                    out.print("<input type='hidden' readonly='readonly' name='product_name' value='" + p.getProduct_name() + "'");

                    out.print("<input type='hidden' readonly='readonly'  name='price' value='" + p.getPrice() + "'");
                           
                    out.println("<td colspan='2'><input type='submit' value='submit'/></td></tr> ");
                    out.println("</table>");
                    out.println("</form>");
              
                }}
     catch(Exception e)
                {
                  e.printStackTrace();
                }
}
}
