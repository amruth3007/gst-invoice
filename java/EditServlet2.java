    import java.io.IOException;  
    import java.io.PrintWriter;  
      
    import javax.servlet.ServletException;  
    import javax.servlet.annotation.WebServlet;  
    import javax.servlet.http.HttpServlet;  
    import javax.servlet.http.HttpServletRequest;  
    import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
    @WebServlet("/EditServlet2")  
    public class EditServlet2 extends HttpServlet {  
        protected void doPost(HttpServletRequest request, HttpServletResponse response)   
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
            
             
              
               int  serial_id=Integer.parseInt(request.getParameter("serial_id"));  
            int quantity=Integer.parseInt(request.getParameter("quantity"));
  
        String item=request.getParameter("item");  
        String product_id=request.getParameter("product_id"); 
        String product_name=request.getParameter("product_name");  
        String price=request.getParameter("price");  
        
        String hsn_code=request.getParameter("hsn_code");   
              
            prod_list p=new prod_list();  
        p.setSerial_id(serial_id);
        p.setItem(item);
        p.setProduct_id(product_id);
        p.setProduct_name(product_name);
        p.setPrice(price);
        p.setQuantity(quantity);
        p.setHsn_code(hsn_code);  
              
            int status=prodDao.update(p);  
            
            if(status>0){  
                response.sendRedirect("ViewServlet");  
            }else{  
                out.println("Sorry! unable to update record");  
            }  
              
            out.close();  
        }  
      
    }  