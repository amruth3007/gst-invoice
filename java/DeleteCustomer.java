import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
@WebServlet("/DeleteCustomer")  
public class DeleteCustomer extends HttpServlet {  
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
        
            
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
            
   
            
        
      PrintWriter out=response.getWriter();
         out.print("<title>Delete Customer </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        int status=custDao.delete();  
        
 request.getRequestDispatcher("customer_entry");
       // response.sendRedirect("customer_entry");   
       
        
    }  
}
