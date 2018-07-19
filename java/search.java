

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

public class search extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();
 out.print("<title>Search Invoices</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }

        String from = request.getParameter("from");
        String to = request.getParameter("to");

        try {
            String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
            String userMysql = "amruth";
            String pwdMysql = "root";
            String dbUser, dbPwd;

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);

            PreparedStatement ps = con.prepareStatement("select distinct invoice,customer_id,customer,customer_type,customer_name,currency from save_table where invoice_date >=? and invoice_date <=?;");
            ps.setString(1, from);
            ps.setString(2, to);

            ResultSet rs = ps.executeQuery();
          

            out.print("<form action='logout' method='post'>");
            out.print("<input type='submit' value='logout'/>");
            out.print("<h1>Welcome </h1>");
            out.print("</form>");

            out.print("<table border='1' width='100%'");
            out.print("<tr><th>Invoice</th><th>Customer ID</th><th>Customer</th><th>Customer Type</th><th>Customer Name</th><th>Currency</th><th>View</th></tr>");

            while (rs.next()) {

                out.print("<tr><td>" + rs.getInt(1) + "</td><td>" + rs.getInt(2) + "</td><td>" + rs.getString(3) + "</td><td>" + rs.getString(4) + "</td><td>" + rs.getString(5) + "</td><td>" + rs.getString(6) + "</td>");

                out.print("<form action='result' method='post'>");
                out.print("<input type='hidden' name='invoice_review' value='" + rs.getInt(1) + "'/>");
               // out.print("<input type='hidden' name='currency_review' value='" + rs.getString(4) + "'/>");
                out.print("<td><input type='submit' value='View Invoice'/></td></tr>");
                out.print("</form>");

            }
            out.print("</table>");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
