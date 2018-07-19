

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amruth
 */
public class choose_tax extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      PrintWriter out = response.getWriter(); 
             HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
              out.print("<title>SelectTAX</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");

              String currency=request.getParameter("currency");
                 String cur_value=request.getParameter("cur_value");
    
        
                 
        
            out.print("<form action='select_tax' method='post' >"); 
           
            out.print("<table>");
             out.print("<input type='hidden' name='currency' value='"+currency+"'/>");
             out.print("<input type='hidden' name='cur_value' value='"+cur_value+"'/>");
            out.print("<tr><td>Discount:</td><td><input type='number' placeholder='%' value='0' step='0.1' name='discount'/></td></tr>"); 
            
            
      
        String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
        String sql = "select tax_name from tax;";
      
               
        out.print("<tr><td>Choose TAX</td><td> <select name='tax'>");
                        
            
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
        
            
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            
            while (rs.next()) {
                
            out.print("<option value="+rs.getString(1)+" >"+rs.getString(1)+"</option>");
           
            
            }
            
            out.print("<option value='create_tax'>Create TAX</option>");
            out.print(" </select><br></td></tr>");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         
          out.println("<tr><td colspan='2'><input type='submit' value='Submit'/></td></tr> ");
          out.println("<table>");
           out.println(" </form>");
          
           
                 }}
