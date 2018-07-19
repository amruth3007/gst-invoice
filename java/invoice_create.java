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
public class invoice_create extends HttpServlet {

  String product_id;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
               response.setContentType("text/html;charset=UTF-8");
         PrintWriter out = response.getWriter();
         
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
             out.print("<title>Product Entry</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<h1>Welcome </h1>");
        out.print("<input type='submit' value='logout'/>");
        
        out.print("</form>");
        out.print("<h1>Add New Product</h1> ");
             
        
        
         String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
        String sql = "select item,product_id from cp_product;";
      out.println("<table>");
           out.println(" <form action='select_product' method='post' > ");
                      out.print("<tr><td>Choose Product</td><td> <select name='product'>");
                        
            
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
        
            
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery(sql);
            
            while (rs.next()) {
                
            out.print("<option value="+rs.getString(1)+" >"+rs.getString(1)+"</option>");

            product_id=rs.getString(2);
            }
            
            out.print("<option value='create_product'>Create new Product</option>");
            out.print(" </select><br></td></tr>");
        }catch(Exception e)
        {
            e.printStackTrace();
        }
         
          out.println("<tr><td colspan='2'><input type='submit' value='Submit'/></td></tr> ");
          out.println("<table>");
           out.println(" </form>");
          
           
           
           
           
                 }}
