
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
public class select_tax extends HttpServlet {

   
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
             HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
             out.print("<title> Select TAX</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");
        
        String currency=request.getParameter("currency");
         String cur_value=request.getParameter("cur_value");
          Float discount=Float.parseFloat(request.getParameter("discount"));
            
       
        String tax=request.getParameter("tax");
        if(tax.equals("create_tax"))
        {
            response.sendRedirect("create_tax.jsp");
        }
        
        else
        {
             
      
        try {
           tax t=tax_Dao.getTaxByID(tax);
            out.println("<table>");
           out.println(" <form action='calculate' method='post' > ");
           out.print("<input type='hidden' name='currency' value='"+currency+"'/>");
           out.print("<input type='hidden' name='cur_value' value='"+cur_value+"'/>");
            out.print("<input type='hidden' name='discount' value='"+discount+"'/>");
            
             out.print("<tr><td>Discount(%):</td><td><input type='text' readonly='readonly' name='discount1' value='"+discount+"'/></td></tr>");

            out.print("<tr><td>Tax Name:</td><td><input type='text' readonly='readonly' name='tax_name' value='"+t.getTax_name()+"'/></td></tr>");
            out.print("<tr><td>Tax Value(%):</td><td><input type='text' readonly='readonly' name='tax_value' value='"+t.getTax_value()+"'/></td></tr>");
            
            
           out.println("<tr><td colspan='2'><input type='submit' value='calculate'/></td></tr> ");
           out.println("</table>");
           out.println("</form>");
 
           
        }catch(Exception e)
        {
            e.printStackTrace();
        }
           
        }
        
    }
}