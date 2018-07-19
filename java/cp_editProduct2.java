

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/cp_editProduct2")
public class cp_editProduct2 extends HttpServlet {
String pro_id1;
int pro_id;
  
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");  
           PrintWriter out=response.getWriter(); 
                  out.print("<title>Edit Product</title>");
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
       
          String product_edit=request.getParameter("product_edit"); 
               pro_id1=request.getParameter("pro_id");
       
                try
               {
              pro_id=Integer.parseInt(pro_id1);
                }catch(Exception e)
          {
               e.printStackTrace();
         }
        String item=request.getParameter("item"); 
        String product_id=request.getParameter("product_id");  
        String product_name=request.getParameter("product_name");
        String price=request.getParameter("price");  
    
        
        cp_product p=new cp_product();
        
       p.setPro_id(pro_id);
       p.setItem(item);
       p.setProduct_id(product_id);
       p.setProduct_name(product_name);
       p.setPrice(price);
              
            int status=cp_prodDao.update(p,product_edit);
           
            if(status>0){  
                
                out.print("<script>alert ('Record updated successfully!');</script>");
               request.getRequestDispatcher("cp_viewProduct").include(request, response);
                
              
                
            }else{  
                out.println("Sorry! unable to update record");  
            }  
              
            out.close();  
        }  
      
    }  