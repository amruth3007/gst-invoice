

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
public class select_product extends HttpServlet {

    String product_id;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
  out.print("<title>Select Product</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");

        String product = request.getParameter("product");
        
        if (product.equals("create_product")) {
            response.sendRedirect("create_product.jsp");
        } else {

            String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
            String userMysql = "amruth";
            String pwdMysql = "root";
            String dbUser, dbPwd;
            String sql = "select product_id from cp_product where item=?;";

            try {
                Class.forName("com.mysql.jdbc.Driver");

                Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);

                PreparedStatement ps = con.prepareStatement(sql);
                ps.setString(1, product);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {

                    product_id = rs.getString(1);
                }

                cp_product p = cp_prodDao.getProductById(product_id);;

                out.println("<table>");
                out.println(" <form action='saveServlet' method='post' > ");

                out.print("<tr><td>Item:</td><td><input type='text' readonly='readonly' name='item' value='" + p.getItem() + "'/></td></tr>");
                out.print("<tr><td>Product ID:</td><td><input type='text' readonly='readonly'  name='product_id' value='" + p.getProduct_id() + "'/></td></tr>");
                out.print("<tr><td>Product Name:</td><td><input type='text' readonly='readonly' name='product_name' value='" + p.getProduct_name() + "'/></td></tr>");

                out.print("<tr><td>Price per unit(INR):</td><td><input type='text' readonly='readonly'  name='price' value='" + p.getPrice() + "'/></td></tr>");
                out.print("<tr><td>Quantity:</td><td><input type='text' name='quantity' required/></td></tr>");
                out.print("<tr><td>HSN code:</td><td><input type='text' name='hsn_code' required/></td></tr>");

                out.println("<tr><td colspan='2'><input type='submit' value='Save Product'/></td></tr> ");
                out.println("</table>");
                out.println("</form>");

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }
}
