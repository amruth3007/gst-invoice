import static java.awt.SystemColor.text;
    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/Cc_editcustomer")  
    public class cc_editcustomer extends HttpServlet {  
        String customer_edit;
        String customer;
        protected void doPost(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter();  
            
                  out.print("<title>Customer Edit</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
         
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
             
              customer=request.getParameter("customer");
              customer_edit=customer;
              
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            
             out.print("<form action='cc_editcustomer2' method='post' >");  
            out.print("<table>");
            
             cc_customer c=cc_custDao.getCustomerByID(customer); 
            out.print("<tr><td></td><td><input type='hidden'  name='customer_edit' value='"+customer_edit+"'/></td></tr>");

            out.print("<tr><td>Customer ID:</td><td><input type='text' readonly='readonly' name='customer_id' value='"+c.getCustomer_id()+"'/></td></tr>");
            out.print("<tr><td>Customer:</td><td><input type='text'  name='customer' value='"+c.getCustomer()+"'/></td></tr>");
            out.print("<tr><td>Customer Type:</td><td><input type='text' readonly='readonly'  name='customer_type' value='"+c.getCustomer_type()+"'/></td></tr>");
            out.print("<tr><td>Customer Name:</td><td><input type='text'  name='customer_name' value='"+c.getCustomer_name()+"'/></td></tr>");

            out.print("<tr><td>Street:</td><td><input type='text'   name='street' value='"+c.getStreet()+"'/></td></tr>");
            out.print("<tr><td>Postal Code:</td><td><input type='text'  name='postal_code' value='"+c.getPostal_code()+"'/></td></tr>");
            out.print("<tr><td>Country ID:</td><td><input type='text'  name='country_id' value='"+c.getCountry_id()+"'/></td></tr>");
           
            out.print("<tr><td>Phone:</td><td><input type='text'  name='phone' value='"+c.getPhone()+"'/></td></tr>");
            out.print("<tr><td>Mobile:</td><td><input type='text'  name='mobile' value='"+c.getMobile()+"'/></td></tr>");

            out.print("<tr><td>Email:</td><td><input type='text'  name='email' value='"+c.getEmail()+"'/></td></tr>");
            out.print("<tr><td>PAN no:</td><td><input type='text' name='pan_no' value='"+c.getPan_no()+"'/></td></tr>");
            out.print("<tr><td>GST no:</td><td><input type='text'  name='gst_no' value='"+c.getGst_no()+"'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
            out.print("</table>");  
            out.print("</form>");  
              
            out.close();  
        }  
    }  