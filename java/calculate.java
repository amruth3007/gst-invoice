
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/calculate")
public class calculate extends HttpServlet {

    float tax_value;
    float cur_value;
    float discount;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        response.setHeader("cache-control", "no-cache,no-store,must-revalidate");//HTTP 1.1
        response.setHeader("pragma", "no-cache");//HTTP 1.0
        response.setHeader("Expires", "0");//Proxies
        HttpSession session = request.getSession();
        if (session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
        }
        
        out.print("<title>Purchase Details</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
          
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");

        out.println("<h3>Customer Details</h3>");

        customer c = custDao.getCustomer();
        out.print("<table border='1' width='50%'");
        out.print("<tr><td>Customer ID:</td><td>" + c.getCustomer_id() + "</td></tr>");
        out.print("<tr><td>Customer:</td><td>" + c.getCustomer() + "</td></tr>");
        out.print("<tr><td>Customer Type:</td><td>" + c.getCustomer_type() + "</td></tr>");
        out.print("<tr><td>Customer Name:</td><td>" + c.getCustomer_name() + "</td></tr>");
        out.print("<tr><td>Date of Invoice Entry:</td><td>" + c.getInvoice_date() + "</td></tr>");
        out.print("<tr><td>Street:</td><td>" + c.getStreet() + "</td></tr>");
        out.print("<tr><td>Postal Code:</td><td>" + c.getPostal_code() + "</td></tr>");
        out.print("<tr><td>Country ID:</td><td>" + c.getCountry_id() + "</td></tr>");
        out.print("<tr><td>Phone:</td><td>" + c.getPhone() + "</td></tr>");
        out.print("<tr><td>Email:</td><td>" + c.getEmail() + "</td></tr>");
        out.print("<tr><td>PAN no:</td><td>" + c.getPan_no() + "</td></tr>");
        out.print("<tr><td>GST no:</td><td>" + c.getGst_no() + "</td></tr>");
        out.print("</table>");

        out.println("<h3>Product List</h3>");
        List<prod_list> list = prodDao.getAllProducts();
        out.print("<table border='1' width='100%'");

        out.print("<tr><th>Sl No.</th><th>Item</th><th>Product ID</th><th>Product Name</th><th>Price(INR)</th><th>Quantity</th><th>HSN code</th></tr>");
        System.out.println("for method");
        for (prod_list p1 : list) {

            out.print("<tr><td>" + p1.getSerial_id() + "</td><td>" + p1.getItem() + "</td><td>" + p1.getProduct_id() + "</td><td>" + p1.getProduct_name() + "</td><td>" + p1.getPrice() + "</td><td>" + p1.getQuantity() + "</td><td>" + p1.getHsn_code() + "</td></tr>");

        }
        out.print("</table>");

        String tax_name = request.getParameter("tax_name");
        String currency = request.getParameter("currency");

        try {
            tax_value = Float.parseFloat(request.getParameter("tax_value"));
            discount = Float.parseFloat(request.getParameter("discount"));

            cur_value = Float.parseFloat(request.getParameter("cur_value"));

        } catch (Exception e) {
            e.printStackTrace();
        }

        String cur = "";

        try {
            if (currency.equals("inr")) {
                cur = "INR";
            } else if (currency.equals("usd")) {
                cur = "USD";
            } else {
                cur = "@UNIT";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        String price[] = new String[20];
        float price1[] = new float[20];
        Integer quantity[] = new Integer[20];
        int i = 0;
        float total = 0;

        for (prod_list p1 : list) {

            price[i] = p1.getPrice();
            price1[i] = (Float.parseFloat(price[i]))/(cur_value);
            quantity[i] = p1.getQuantity();
            total += (price1[i] * quantity[i]);
        }

        float total_tax = total * (tax_value) / 100;

        float total_price = total + total_tax;
        float discount_price = discount * total/100;
        float total_amount = total_price - discount_price;

        out.println("<h3>Price Calculation</h3>");

        out.print("<table border='1' width='50%'");

        out.print("<tr><td>Total(without TAX)</th><td>" + cur + total + "</td></tr>");
        out.print("<tr><td>" + tax_name + ":" + tax_value + "%</th><td>" + cur + total_tax + "</td></tr>");
        if(discount != 0)
        {
        out.print("<tr><td>Total </td><td>" + cur + total_price + "</td></td>");
        out.print("<tr><td>Discount:" + discount + "%</td><td>" + cur + discount_price + "</td></td>");
        }
        out.print("<tr><td>Total Price</td><td>" + cur + total_amount + "</td></td>");
        out.print("</table>");
        
        out.print("<br>");
        
              out.print("<form action='cur_tax.jsp'>");
        out.print("<table>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit Currency'/></td>");
         out.print("<td colspan='2'><input type='submit' value='Edit TAX'/></td>");
        out.print("<td colspan='2'><input type='submit' value='Edit DISCOUNT'/></td></tr>");
        out.print("</table>");
        out.print("</form>");
        
   
        out.print("</form>");
        out.print("<br>");
        out.print("<form action='Edit_Customer' method='post' >");
        out.print("<table>");
        out.print("<tr><td colspan='2'><input type='submit' value='Edit Invoice Date'/></td>");
        
        out.print("</form>");
       
        out.print("<form action='ViewServlet' method='get' >");
        
        out.print("<td colspan='2'><input type='submit' value='Edit Product'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.print("<br>");
        out.print("<form action='final_submission' method='post' >");
        out.print("<table>");

        out.print("<input type='hidden' name='currency' value='" + currency + "'/>");
        out.print("<input type='hidden' name='cur_value' value='" + cur_value + "'/>");
        out.print("<input type='hidden' name='tax_name' value='" + tax_name + "'/>");
        out.print("<input type='hidden' name='tax_value' value='" + tax_value + "'/>");
        out.print("<input type='hidden' name='discount' value='" + discount + "'/>");
        out.print("<tr><td colspan='2'><input type='submit' value='Final Submission'/></td></tr>");
        out.print("</table>");
        out.print("</form>");

        out.close();
    }
}
