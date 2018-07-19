    import java.io.IOException;  
    import java.io.PrintWriter;  
import javax.servlet.RequestDispatcher;
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/Cc_editcustomer2")  
    public class cc_editcustomer2 extends HttpServlet {  
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
       /* out.print("<form action='logout' method='post'>");
        out.print("<input type='submit' value='logout'/>");
        out.print("<h1>Welcome </h1>");
        out.print("</form>");*/
       
          String customer_edit=request.getParameter("customer_edit"); 

       
        int customer_id=Integer.parseInt(request.getParameter("customer_id"));
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
        
        c.setCustomer_id(customer_id);
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
            
              
            int status=cc_custDao.update(c,customer_edit);  
           
            if(status>0){  
                out.print("<script>alert ('Record updated successfully!');</script>");
                
               request.getRequestDispatcher("cc_viewCustomer").include(request, response);
              
                
            }else{  
                out.println("Sorry! unable to update record");  
            }  
              
            out.close();  
        }  
      
    }  