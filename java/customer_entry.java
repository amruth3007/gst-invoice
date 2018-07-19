

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


public class customer_entry extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
          out.print("<title>Customer Entry</title>");
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
            
        
        
         String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
        String sql = "select customer from cc_customer;";
      out.println("<table>");
           out.println(" <form action='selectCustomer' method='post' > ");
                      out.print("<tr><td>Choose Customer</td><td> <select name='customer'>");
                        
            
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
        
            
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                
            out.print("<option value="+rs.getNString(1)+" >"+rs.getNString(1)+"</option>"); 
            }
            
            out.print("<option value='create_customer'>Create new customer</option>");
            out.print(out);
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         out.print(" </select><br></td></tr>");
          out.println("<tr><td colspan='2'><input type='submit' value='Submit'/></td></tr> ");
          out.println("<table>");
           out.println(" </form>");
                 }}