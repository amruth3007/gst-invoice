/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class cp_viewlist extends HttpServlet {

 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<title> Products List</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>"); 
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        
         out.print("<title>PRODUCTS LIST </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");

        out.println("<a href='create_product.jsp'>Add New Product</a>");
        out.println("<h3>Product Details</h3>");
        List<cp_product> list = cp_prodDao.getAllProducts();
        out.print("<table border='1' width='100%'");

        out.print("<tr><th>Item</th><th>Product ID</th><th>Product Name</th><th>Price per unit(INR)</th><th>Edit</th><th>Delete</th></tr>");

        for (cp_product p : list) {

            out.print("<tr><td>" + p.getItem() + "</td><td>" + p.getProduct_id() + "</td><td>" + p.getProduct_name() + "</td><td>" + p.getPrice() + "</td>");
            out.print("<form action='cp_editProduct' method='post'>");
            out.print("<input type='hidden' name='product_id' value='" + p.getProduct_id() + "'/>");

            out.print("<td><input type='submit' value='Edit'/></td>");
            
            out.print("</form>");
            out.print("<form action='cp_prodDelete' method='post'>");
            out.print("<input type='hidden' name='pro_id' value='" + p.getPro_id() + "'/>");

            out.print("<td><input type='submit' value='Delete'/></td></tr>");
            
            out.print("</form>");
        }
        out.print("</table>");

        out.close();
    }
}
