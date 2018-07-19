
package logout;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author amruth
 */
public class logout extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        
        try {
         String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
         Class.forName("com.mysql.jdbc.Driver");
         Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
         
           System.out.println("Logout successful");
                String sql1 = "delete from customer;";
                String sql2 = "truncate prod_list;";
                String sql3 = "delete from invoice;";
                PreparedStatement ps1 = con.prepareStatement(sql1);
                PreparedStatement ps2 = con.prepareStatement(sql2);
                PreparedStatement ps3 = con.prepareStatement(sql3);
                int i1 = ps1.executeUpdate();
                int i2 = ps2.executeUpdate();
                int i3 = ps3.executeUpdate();
                System.out.println("delete from customer" + i1);
                System.out.println("truncate prod_list" + i2);
                System.out.println("delete from customer" + i3);
         
         con.close();
         
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        
        HttpSession session=request.getSession();
        
        session.removeAttribute("user");
        session.invalidate();
            
    
        
        response.sendRedirect("login.jsp");
        
        }
    }

    
