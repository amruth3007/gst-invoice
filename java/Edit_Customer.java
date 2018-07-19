import static java.awt.SystemColor.text;
    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/EditCustomer")  
    public class Edit_Customer extends HttpServlet {  
        int customer_id;
        protected void doPost(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            out.print("<title>Edit Customer</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
                
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome</h1>");
        out.print("</form>");
            out.println("<h3>Update Customer Details</h3>");  
            
             try
             {
                 
              customer_id=Integer.parseInt(request.getParameter("customer_id")); 
                 
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            
             out.print("<form action='Edit_Customer2' method='post' >");  
            out.print("<table>");
            
             customer c=custDao.getCustomer(); 
                     
            out.print("<tr><td>Customer ID:</td><td><input type='text' readonly='readonly' name='customer_id' value='"+c.getCustomer_id()+"'/></td></tr>");
            out.print("<tr><td>Customer:</td><td><input type='text' readonly='readonly' name='customer' value='"+c.getCustomer()+"'/></td></tr>");
            out.print("<tr><td>Customer Type:</td><td><input type='text' readonly='readonly'  name='customer_type' value='"+c.getCustomer_type()+"'/></td></tr>");
            out.print("<tr><td>Customer Name:</td><td><input type='text' readonly='readonly' name='customer_name' value='"+c.getCustomer_name()+"'/></td></tr>");
            out.print("<tr><td>Date of Invoice Entry:</td><td><input type='date'   name='invoice_date' value='"+c.getInvoice_date()+"'/></td></tr>");
           ;     
            out.print("<tr><td>Street:</td><td><input type='text'  name='street'readonly='readonly' value='"+c.getStreet()+"'/></td></tr>");
            out.print("<tr><td>Postal Code:</td><td><input type='text'  name='postal_code'readonly='readonly' value='"+c.getPostal_code()+"'/></td></tr>");
            out.print("<tr><td>Country ID:</td><td><input type='text' name='country_id' readonly='readonly' value='"+c.getCountry_id()+"'/></td></tr>");
            out.print("<tr><td>Phone:</td><td><input type='text'  name='phone' readonly='readonly'value='"+c.getPhone()+"'/></td></tr>");
            out.print("<tr><td>Email:</td><td><input type='text' name='email' readonly='readonly' value='"+c.getEmail()+"'/></td></tr>");
            out.print("<tr><td>PAN no:</td><td><input type='text'  name='pan_no' readonly='readonly' value='"+c.getPan_no()+"'/></td></tr>");
            out.print("<tr><td>GST no:</td><td><input type='text' name='gst_no' readonly='readonly' value='"+c.getGst_no()+"'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
            out.print("</table>");  
            out.print("</form>");  
              
            out.close();  
        }  
    }  