    import java.io.IOException;  
    import java.io.PrintWriter;  
    import java.util.List;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/Cc_viewcustomer")  
    public class cc_viewCustomer extends HttpServlet {  
        String customer;
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
        out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");
            
        
        
         try
             {
                 
              
              
              customer=request.getParameter("customer");
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            out.println("<h3>Customer Details</h3>");  

              out.print("<form action='cc_editcustomer' method='post' >"); 
             cc_customer c=cc_custDao.getCustomerByID(customer); 
            out.print("<table>");         
            System.out.println("cc_customer"+customer);
            out.print("<tr><td>Customer ID:</td><td><input type='text' readonly='readonly' name='customer_id' value='"+c.getCustomer_id()+"'/></td></tr>");
            out.print("<tr><td>Customer:</td><td><input type='text' readonly='readonly' name='customer' value='"+c.getCustomer()+"'/></td></tr>");
            out.print("<tr><td>Customer Type:</td><td><input type='text' readonly='readonly'  name='customer_type' value='"+c.getCustomer_type()+"'/></td></tr>");
            out.print("<tr><td>Customer Name:</td><td><input type='text' readonly='readonly' name='customer_name' value='"+c.getCustomer_name()+"'/></td></tr>");

            out.print("<tr><td>Street:</td><td><input type='text' readonly='readonly'  name='street' value='"+c.getStreet()+"'/></td></tr>");
            out.print("<tr><td>Postal Code:</td><td><input type='text' readonly='readonly' name='postal_code' value='"+c.getPostal_code()+"'/></td></tr>");
            out.print("<tr><td>Country ID:</td><td><input type='text' readonly='readonly' name='country_id' value='"+c.getCountry_id()+"'/></td></tr>");
           
            out.print("<tr><td>Phone:</td><td><input type='text' readonly='readonly' name='phone' value='"+c.getPhone()+"'/></td></tr>");
            out.print("<tr><td>Mobile:</td><td><input type='text' readonly='readonly' name='mobile' value='"+c.getMobile()+"'/></td></tr>");

            out.print("<tr><td>Email:</td><td><input type='text' readonly='readonly' name='email' value='"+c.getEmail()+"'/></td></tr>");
            out.print("<tr><td>PAN no:</td><td><input type='text' readonly='readonly' name='pan_no' value='"+c.getPan_no()+"'/></td></tr>");
            out.print("<tr><td>GST no:</td><td><input type='text' readonly='readonly' name='gst_no' value='"+c.getGst_no()+"'/></td></tr>");

            out.print("<tr><td colspan='2'><input type='submit' value='Edit'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");     
           

            
            
            out.print("<form action='create_customer.jsp' >"); 
             
            out.print("<table>");  
             out.print("<tr><td colspan='2'><input type='submit' value='Save & Add More'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");

             out.print("<form action='welcome.jsp' >"); 
             
            out.print("<table>");  
             out.print("<tr><td colspan='2'><input type='submit' value='Save &Close'/></td></tr>");  
    
            out.print("</table>");  
            out.print("</form>");
            
            out.close();  
        }  
    }  