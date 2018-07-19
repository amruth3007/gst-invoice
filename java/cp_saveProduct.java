/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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


public class cp_saveProduct extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out=response.getWriter(); 
          HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
     
         out.print("<title> Product Detail</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
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
            
           
          
        String item=request.getParameter("item");  
        String product_id=request.getParameter("product_id"); 
        String product_name=request.getParameter("product_name");  
        String price=request.getParameter("price");  
       
          
        cp_product p=new cp_product();  
        p.setItem(item);
        p.setProduct_id(product_id);
        p.setProduct_name(product_name);
        p.setPrice(price);
  
          
        int status=cp_prodDao.save(p);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("cp_viewProduct").include(request, response);  
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }
}
