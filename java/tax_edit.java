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
public class tax_edit extends HttpServlet {

    String tax_name;
    String tax_edit;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
             out.print("<title>Edit TAX </title>");
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
            out.println("<h3>Update Customer Details</h3>");  
            
             try
             {
             
              tax_name=request.getParameter("tax_name");
              tax_edit=tax_name;
              
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            
             out.print("<form action='tax_edit2' method='post' >");  
            out.print("<table>");
            
             tax t=tax_Dao.getTaxByID(tax_name); 
            out.print("<tr><td></td><td><input type='hidden'  name='tax_edit' value='"+tax_edit+"'/></td></tr>");

            out.print("<tr><td>Tax Name:</td><td><input type='text'  name='tax_name' value='"+t.getTax_name()+"'/></td></tr>");
            out.print("<tr><td>Tax Value:</td><td><input type='text'  name='tax_value' value='"+t.getTax_value()+"'/></td></tr>");
           out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
            out.print("</table>");  
            out.print("</form>");  
              
            out.close();  
        }  
    }  