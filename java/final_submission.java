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
import java.sql.Statement;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class final_submission extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
       
        
        
         PrintWriter out=response.getWriter(); 
                
 
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
            
             out.print("<title>Final Submission</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");
        
              String tax_name=request.getParameter("tax_name");
              String tax_value=request.getParameter("tax_value");
              String currency=request.getParameter("currency");
              String cur_value=request.getParameter("cur_value");
              String discount=request.getParameter("discount");
        
        
        
        String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
        String userMysql = "amruth";
        String pwdMysql = "root";
        String dbUser, dbPwd;
        int invoice_review = 0;
      //  String currency_review = "";
        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);
            int i = 0;//To get the status

            String sql = "insert into save_table(invoice,serial_id,item,product_id,product_name,price,quantity,hsn_code,customer_id,customer,customer_type,customer_name,invoice_date,currency,cur_value,street,postal_code,country_id,phone,email,pan_no,gst_no,tax_name,tax_value,discount) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
            PreparedStatement ps = con.prepareStatement(sql);

            customer c = custDao.getCustomer();
            invoice in = new invoice();
        //    currency_review = c.getCurrency();

            String sql_1 = "insert into invoice(invoice_date,customer_id) values (?,?);";
            PreparedStatement ps_1 = con.prepareStatement(sql_1);
            ps_1.setString(1, c.getInvoice_date());
            ps_1.setInt(2, c.getCustomer_id());
            int i_1 = ps_1.executeUpdate();

            System.out.println("Invoice insertion" + i_1);

            String sql_2 = "select * from invoice;";
            PreparedStatement ps_2 = con.prepareStatement(sql_2);

            ResultSet rs = ps_2.executeQuery();
            while (rs.next()) {
                in.setInvoice_date(rs.getString(2));
                in.setInvoice(rs.getInt(1));
                in.setCustomer_id(rs.getInt(3));
            }
            invoice_review = in.getInvoice();
            System.out.println("invoice"+invoice_review);

            List<prod_list> list = prodDao.getAllProducts();

            for (prod_list p : list) {

                ps.setInt(1, in.getInvoice());
                ps.setInt(2, p.getSerial_id());

                ps.setString(3, p.getItem());
                ps.setString(4, p.getProduct_id());
                ps.setString(5, p.getProduct_name());
                ps.setString(6, p.getPrice());
                ps.setInt(7, p.getQuantity());
                ps.setString(8, p.getHsn_code());
                ps.setInt(9, c.getCustomer_id());
                ps.setString(10, c.getCustomer());
                ps.setString(11, c.getCustomer_type());
                ps.setString(12, c.getCustomer_name());
                ps.setString(13, c.getInvoice_date());
                ps.setString(14, currency);
                ps.setString(15, cur_value);
                ps.setString(16, c.getStreet());
                ps.setString(17, c.getPostal_code());
                ps.setString(18, c.getCountry_id());
                ps.setString(19, c.getPhone());
                ps.setString(20, c.getEmail());
                ps.setString(21, c.getPan_no());
                ps.setString(22, c.getGst_no());
                ps.setString(23, tax_name);
                ps.setString(24, tax_value);
                ps.setString(25, discount);
                i = ps.executeUpdate();

            }
            if (i > 0) {
                System.out.println("Saved in save_table successfully");
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
            }

            //invoice table
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        
        out.println("You have submitted your record successfully...");

        out.print("<form action='result' method='post'>");
        out.print("<table>");
       // out.print("<tr><td></td><td><input type='hidden' name='currency_review' value='" + currency_review + "'/></td></tr>");
        out.print("<tr><td></td><td><input type='hidden' name='invoice_review' value='" + invoice_review + "'/></td></tr>");
        out.print("<tr><td colspan='2'><input type='submit' value='REVIEW'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.print("<form action='result.jsp'>");
        out.print("<table>");
        out.print("<tr><td></td><td><input type='hidden' name='invoice_review' value='" + invoice_review + "'/></td></tr>");
     //   out.print("<tr><td></td><td><input type='hidden' name='currency_review' value='" + currency_review + "'/></td></tr>");

        out.print("<tr><td colspan='2'><input type='submit' value='PDF'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

    }

}
