

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class tax_viewlist extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
         out.print("<title>TAX list </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");

        out.println("<a href='create_tax.jsp'>Add New TAX</a>");
        out.println("<h3>TAX Details</h3>");
        List<tax> list = tax_Dao.getAllTaxes();
        out.print("<table border='1' width='100%'");

        out.print("<tr><th>Tax Key</th><th>Tax Name</th><th>Tax Value %</th><th>Edit</th><th>Delete</th></tr>");

        for (tax t : list) {

            out.print("<tr><td>" +t.getTax_key() + "</td><td>" + t.getTax_name() + "</td><td>" + t.getTax_value() + "</td>");
            out.print("<form action='tax_edit' method='post'>");
            out.print("<input type='hidden' name='tax_name' value='" + t.getTax_name() + "'/>");

            out.print("<td><input type='submit' value='Edit'/></td>");
            
            out.print("</form>");
            out.print("<form action='tax_Delete' method='post'>");
            out.print("<input type='hidden' name='tax_name' value='" + t.getTax_name() + "'/>");

            out.print("<td><input type='submit' value='Delete'/></td></tr>");
            
            out.print("</form>");
        }
        out.print("</table>");

        out.close();
    }
}
