

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
public class tax_Delete extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException  {  
         PrintWriter out=response.getWriter(); 
            
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
            
               out.print("<title>Delete TAX</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
 
        String tax_name=request.getParameter("tax_name");  
        tax_Dao.delete(tax_name);
            //    out.print("<script>alert ('Record deleted successfully!');</script>");

       
       response.sendRedirect("welcome.jsp");
       
    }  
}