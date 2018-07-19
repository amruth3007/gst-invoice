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
public class cp_viewProduct extends HttpServlet {

  String product_id;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
            
           try
             {
            
              product_id=request.getParameter("product_id");
             }catch(Exception e)
             {
                 
             }
                
          
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
             out.print("<title>Product Details </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");
            
        

            out.println("<h3>Product Details</h3>");  

              out.print("<form action='cp_editProduct' method='post' >"); 
             cp_product p=cp_prodDao.getProductById(product_id);
            out.print("<table>");         
                        
            out.print("<tr><td>Item:</td><td><input type='text' readonly='readonly' name='item' value='"+p.getItem()+"'/></td></tr>");
            out.print("<tr><td>Product ID:</td><td><input type='text' readonly='readonly'  name='product_id' value='"+p.getProduct_id()+"'/></td></tr>");
            out.print("<tr><td>Product Name:</td><td><input type='text' readonly='readonly' name='product_name' value='"+p.getProduct_name()+"'/></td></tr>");

            out.print("<tr><td>Price per unit(INR):</td><td><input type='text' readonly='readonly'  name='price' value='"+p.getPrice()+"'/></td></tr>");
         
            out.print("<tr><td colspan='2'><input type='submit' value='Edit'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");     
           

            
            
            out.print("<form action='create_product.jsp' >"); 
             
            out.print("<table>");  
             out.print("<tr><td colspan='2'><input type='submit' value='Save & Add More'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");

             out.print("<form action='welcome.jsp' >"); 
             
            out.print("<table>");  
             out.print("<tr><td colspan='2'><input type='submit' value='Save &Close'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");
            
            out.close();
    }
}