import java.io.IOException;  
import java.io.PrintWriter;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
@WebServlet("/DeleteServlet")  
public class DeleteServlet extends HttpServlet {  
    protected void doGet(HttpServletRequest request, HttpServletResponse response)   
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
            out.print("<title>Delete Product </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        
        
        String sid=request.getParameter("serial_id");  
        int serial_id=Integer.parseInt(sid);  
        prodDao.delete(serial_id);  
        response.sendRedirect("ViewServlet");  
    }  
}