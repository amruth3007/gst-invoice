

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class tax_view extends HttpServlet {
String tax_name;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
            
               out.print("<title>TAX Details</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
                
          
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");
            
        
        
         try
             {
                 
              
              
              tax_name=request.getParameter("tax_name");
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            out.println("<h3>TAX Details</h3>");  

              out.print("<form action='tax_edit' method='post' >"); 
             tax t=tax_Dao.getTaxByID(tax_name); 
            out.print("<table>");         
            
            out.print("<tr><td>Tax Name:</td><td><input type='text' readonly='readonly' name='tax_name' value='"+t.getTax_name()+"'/></td></tr>");
            out.print("<tr><td>Tax Value:</td><td><input type='text' readonly='readonly'  name='tax_value' value='"+t.getTax_value()+"'/></td></tr>");
            
            out.print("<tr><td colspan='2'><input type='submit' value='Edit'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");     
           

            
            
            out.print("<form action='create_tax.jsp' >"); 
             
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