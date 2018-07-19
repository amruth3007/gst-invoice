import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
@WebServlet("/cc_delete")  
public class cc_delete extends HttpServlet {  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)   
             throws ServletException, IOException {  
         PrintWriter out=response.getWriter(); 
            
           out.print("<title>Customer Delete</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
         
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
 
        String customer=request.getParameter("customer");  
        cc_custDao.delete(customer);
               

       
       response.sendRedirect("welcome.jsp");
      //  out.print("<script>alert ('Record deleted successfully!');</script>");
       
    }  
}