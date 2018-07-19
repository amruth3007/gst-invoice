
import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/result")

public class result extends HttpServlet {

    String currency;
    float tax_value;
    float cur_value;
    String tax_name;
    Float discount;
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        
         out.print("<title>INVOICE </title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");

        
        
        int invoice = 0;
        

        String invoice_review = request.getParameter("invoice_review");
        invoice = Integer.parseInt(invoice_review);

        

        List<save_table> list = save_table_retrive.getAll_save(invoice);

        out.print("<table border='1' width='100%'>");
        out.print("<tr><th>GST INVOICE</th></tr>");
        out.print("</table>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>CUSTOMER DETAILS</th><th>INVOICE DETAILS</th></tr>");
        out.print("</table>");
        out.print("<table border='1' width='100%'");

        for (save_table s1 : list) {

            out.print("<tr><td>Customer ID</td><td>" + s1.getCustomer_id() + "</td><td>Invoice ID</td><td>" + s1.getInvoice() + "</td></tr>");
            out.print("<tr><td>Customer</td><td>" + s1.getCustomer() + "</td><td>Invoice Date</td><td>" + s1.getInvoice_date() + "</td></tr>");
            out.print("<tr><td>Customer Type</td><td>" + s1.getCustomer_type() + "</td></tr>");
            out.print("<tr><td>Customer Name</td><td>" + s1.getCustomer_name() + "</td></tr>");

            out.print("<tr><td>Street</td><td>" + s1.getStreet() + "</td></tr>");
            out.print("<tr><td>Postal code</td><td>" + s1.getPostal_code() + "</td></tr>");
            out.print("<tr><td>Country ID</td><td>" + s1.getCountry_id() + "</td></tr>");
            out.print("<tr><td>Phone</td><td>" + s1.getPhone() + "</td></tr>");
            out.print("<tr><td>Email</td><td>" + s1.getEmail() + "</td></tr>");
            out.print("<tr><td>PAN No</td><td>" + s1.getPan_no() + "</td></tr>");
            out.print("<tr><td>GST No</td><td>" + s1.getGst_no() + "</td></tr>");

             tax_name = s1.getTax_name();

            currency = s1.getCurrency();

             tax_value = Float.parseFloat(s1.getTax_value());

             cur_value = Float.parseFloat(s1.getCur_value());
             discount=Float.parseFloat(s1.getDiscount());

            break;
        }
        
        
        out.print("</table>");
        out.print("<br>");
               out.print("<table border='1' width='100%'>");
        out.print("<tr><th>PRODUCT DETAILS</th></tr>");
        out.print("</table>");
        out.print("<table border='1' width='100%'");
        out.print("<tr><th>Sl No</th><th>Item</th><th>Product ID</th><th>Product Name</th><th>Price(INR)</th><th>Quantity</th><th>HSN code</th></tr>");

        for (save_table s : list) {

            out.print("<tr><td>" + s.getInvoice_id() + "</td><td>" + s.getItem() + "</td><td>" + s.getProduct_id() + "</td><td>" + s.getProduct_name() + "</td><td>" + s.getPrice() + "</td><td>" + s.getQuantity() + "</td><td>" + s.getHsn_code() + "</td></tr>");

        }

        out.print("</table>");

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
        Integer quantity[] = new Integer[20];
        float price1[] = new float[20];
        float cur_value = 0;
        int i = 0;
        float total = 0;

        for (save_table s : list) {

            price[i] = s.getPrice();
            cur_value = Float.parseFloat(s.getCur_value());
            price1[i] = (Float.parseFloat(price[i]))/(cur_value);
            quantity[i] = s.getQuantity();
            
            total += (price1[i] * quantity[i]);
        }

        float total_tax = total * (tax_value) / 100;

        float total_price = total + total_tax;
        float discount_price = discount * total/100;
        float total_amount = total_price - discount_price;
out.print("<br>");
         out.print("<table border='1' width='50%'>");
        out.print("<tr><th>PRICE CALCULATION</th></tr>");
        out.print("</table>");

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
  
            
            
            out.print("<h3>Terms And Conditions:</h3>");
          
                          
            out.print("<form action='result.jsp'>"); 
            out.print("<table>");
            out.print("<tr><td colspan='2'><input type='submit' value='PDF'/></td></tr>"); 
            out.print("</table>");
            out.print("</form>");
          
          
           
        }
    }