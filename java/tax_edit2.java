

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
public class tax_edit2 extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
        {  
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
        out.print("<h1>Welcome </h1>");
        out.print("</form>");
       
          String tax_edit=request.getParameter("tax_edit"); 

       
       // int tax_key=Integer.parseInt(request.getParameter("tax_key"));
        String tax_name=request.getParameter("tax_name"); 
        String tax_value=request.getParameter("tax_value");
        
        
        tax t=new tax();
        
        t.setTax_name(tax_name);
        t.setTax_value(tax_value);
            
              
            int status=tax_Dao.update(t,tax_edit);  
           
            if(status>0){  
                out.print("<script>alert ('Record updated successfully!');</script>");
                
               request.getRequestDispatcher("tax_view").include(request, response);
                
              
                
            }else{  
                out.println("Sorry! unable to update record");  
            }  
              
            out.close();  
        }  
      
    }  