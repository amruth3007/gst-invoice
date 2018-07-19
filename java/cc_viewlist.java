

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class cc_viewlist extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        
         out.print("<title>CUSTOMERS LIST </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
       
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");

        out.println("<a href='create_customer.jsp'>Add New Customer</a>");
        out.println("<h3>Customers Details</h3>");
        List<cc_customer> list = cc_custDao.getAllCustomers();
        out.print("<table border='1' width='100%'");

        out.print("<tr><th>Customer ID</th><th>Customer</th><th>Customer Type</th><th>Customer Name</th><th>Country ID</th><th>Phone</th><th>Mobile</th><th>Email</th><th>Edit</th><th>Delete</th></tr>");

        for (cc_customer c : list) {

            out.print("<tr><td>" + c.getCustomer_id() + "</td><td>" + c.getCustomer() + "</td><td>" + c.getCustomer_type() + "</td><td>" + c.getCustomer_name() + "</td><td>" + c.getCountry_id() + "</td><td>" + c.getPhone() + "</td><td>" + c.getMobile() + "</td><td>" + c.getEmail() + "</td>");
            out.print("<form action='cc_editcustomer' method='post'>");
            out.print("<input type='hidden' name='customer' value='" + c.getCustomer() + "'/>");

            out.print("<td><input type='submit' value='Edit'/></td>");
            
            out.print("</form>");
            out.print("<form action='cc_delete' method='post'>");
            out.print("<input type='hidden' name='customer' value='" + c.getCustomer() + "'/>");

            out.print("<td><input type='submit' value='Delete'/></td></tr>");
            
            out.print("</form>");
        }
        out.print("</table>");

        out.close();
    }
}
