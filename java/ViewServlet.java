    import java.io.IOException;  
    import java.io.PrintWriter;  
    import java.util.List;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/ViewServlet")  
    public class ViewServlet extends HttpServlet {  
        protected void doGet(HttpServletRequest request, HttpServletResponse response)   
                   throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
    out.print("<title>Products list</title>");
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
        out.print("<h1>Welcome </h1>");
        out.print("</form>");
            
            
            

            out.println("<h3>Products List</h3>");  
            List<prod_list> list=prodDao.getAllProducts();  
            out.print("<table border='1' width='100%'");  
            
            out.print("<tr><th>Sl No</th><th>Item</th><th>Product ID</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>HSN code</th><th>Edit</th><th>Delete</th></tr>");  
            
           for(prod_list p1:list){  
              
             out.print("<tr><td>"+p1.getSerial_id()+"</td><td>"+p1.getItem()+"</td><td>"+p1.getProduct_id()+"</td><td>"+p1.getProduct_name()+"</td><td>"+p1.getPrice()+"</td><td>"+p1.getQuantity()+"</td><td>"+p1.getHsn_code()+"</td><td><a href='EditServlet?serial_id="+p1.getSerial_id()+"'>edit</a></td><td><a href='DeleteServlet?serial_id="+p1.getSerial_id()+"'>delete</a></td></tr>"); 
            
             
            }  
            out.print("</table>");  
            
                out.print("<br>");  
                out.print("<br>");  
             out.print("<form action='invoice_create' method='post' >"); 
            out.print("<table>");
            out.print("<tr><td colspan='2'><input type='submit' value='Add more products'/></td></tr>"); 
            out.print("</table>");
            out.print("</form>");  
            out.print("<form action='cur_tax.jsp'  >"); 
            out.print("<table>");
            out.print("<tr><td colspan='2'><input type='submit' value='Proceed to currency'/></td></tr>"); 
            out.print("</table>");
            out.print("</form>");
              
            out.close();  
        }  
    }  