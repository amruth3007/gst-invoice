

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
public class tax_save extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
       
                
         out.print("<title>TAX Details</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
       
       
            
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
     
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
            
        
        
        String tax_name=request.getParameter("tax_name");  
        String tax_value=request.getParameter("tax_value"); 
          
        
        tax t=new tax();
        
        t.setTax_name(tax_name);
        t.setTax_value(tax_value);
        
          
        int status=tax_Dao.save(t);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("tax_view").include(request, response);
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }
}
}
