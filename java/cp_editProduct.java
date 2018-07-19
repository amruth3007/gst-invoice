/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amruth
 */
public class cp_editProduct extends HttpServlet {
 String product_edit;
        String product_id;
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
          
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            
                 out.print("<title>Edit Product</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>"); 
         
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");
            out.println("<h3>Update Product Details</h3>");  
            
             try
             {
                 System.out.println("value"+request.getParameter("product_id"));
              product_id=request.getParameter("product_id");
              product_edit=product_id;
              
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            
             out.print("<form action='cp_editProduct2' method='post'>");  
            out.print("<table>");
            
             cp_product p=cp_prodDao.getProductById(product_id);
            out.print("<tr><td></td><td><input type='hidden'  name='product_edit' value='"+product_edit+"'/></td></tr>");

                         
            out.print("<tr><td>Item:</td><td><input type='text'  name='item' value='"+p.getItem()+"'/></td></tr>");
            out.print("<tr><td>Product ID:</td><td><input type='text'   name='product_id' value='"+p.getProduct_id()+"'/></td></tr>");
            out.print("<tr><td>Product Name:</td><td><input type='text'  name='product_name' value='"+p.getProduct_name()+"'/></td></tr>");

            out.print("<tr><td>Price per unit:(INR)</td><td><input type='text'  name='price' value='"+p.getPrice()+"'/></td></tr>");
         
            out.print("<tr><td colspan='2'><input type='submit' value='Edit'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");     
            
              
            out.close();  
        }  
    }  