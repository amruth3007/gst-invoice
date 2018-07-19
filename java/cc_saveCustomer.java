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

public class cc_saveCustomer extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
       
                out.print("<title>Customer Detail</title>");
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
            
            
        
        
        String customer=request.getParameter("customer"); 
        String customer_type=request.getParameter("customer_type");
        String customer_name=request.getParameter("customer_name");  
        String street=request.getParameter("street");  
        String postal_code=request.getParameter("postal_code");  
        String country_id=request.getParameter("country_id"); 
        String phone=request.getParameter("phone"); 
        String mobile=request.getParameter("mobile");
        String email=request.getParameter("email");
        String pan_no=request.getParameter("pan_no");  
        String gst_no=request.getParameter("gst_no");
          
        
        cc_customer c=new cc_customer();
        
        c.setCustomer(customer);
        c.setCustomer_type(customer_type);
        c.setCustomer_name(customer_name);
        c.setStreet(street);
        c.setPostal_code(postal_code);
        c.setCountry_id(country_id);
        c.setMobile(mobile);
        c.setPhone(phone);
        c.setEmail(email);
        c.setPan_no(pan_no);
        c.setGst_no(gst_no);
          
        int status=cc_custDao.save(c);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            request.getRequestDispatcher("cc_viewCustomer").include(request, response);
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }
}
