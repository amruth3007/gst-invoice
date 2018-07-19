import static java.awt.SystemColor.text;
    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/EditServlet")  
    public class EditServlet extends HttpServlet {  
        int serial_id;
        protected void doGet(HttpServletRequest request, HttpServletResponse response)   
               throws ServletException, IOException {  
            response.setContentType("text/html");  
            PrintWriter out=response.getWriter(); 
                
            out.print("<title>Edit Product</title>");
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
            
            
            out.println("<h3>Update Product</h3>");  
            
             try
             {
                 
              serial_id=Integer.parseInt(request.getParameter("serial_id")); 
                 
             }catch(Exception e)
             {
                 e.printStackTrace();
             }
            

            prod_list p=prodDao.getProductById(serial_id);  
              
            out.print("<form action='EditServlet2' method='post' >");  
            out.print("<table>");
            out.print("<tr><td>sl No.</td><td><input type='text' name='serial_id'  readonly='readonly' value='"+p.getSerial_id()+"'/></td></tr>");
            out.print("<tr><td>Item:</td><td><input type='text' name='item' readonly='readonly' value='"+p.getItem()+"'/></td></tr>");
            out.print("<tr><td>Product_ID:</td><td><input type='text' name='product_id' readonly='readonly' value='"+p.getProduct_id()+"'/></td></tr>");
            out.print("<tr><td>Product Name</td><td><input type='text' name='product_name' readonly='readonly' value='"+p.getProduct_name()+"'/></td></tr>");
            out.print("<tr><td>Price:</td><td><input type='text' name='price' value='"+p.getPrice()+"'/></td></tr>");
            out.print("<tr><td>Quantity:</td><td><input type='text' name='quantity' value='"+p.getQuantity()+"'/></td></tr>");
            out.print("<tr><td>HSN code:</td><td><input type='text' name='hsn_code' value='"+p.getHsn_code()+"'/></td></tr>");

            
            
            
            out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");  
            out.print("</table>");  
            out.print("</form>");  
              
            out.close();  
        }  
    }  