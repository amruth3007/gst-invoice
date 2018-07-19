/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class saveServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request, HttpServletResponse response)   
         throws ServletException, IOException {  
        response.setContentType("text/html");  
        PrintWriter out=response.getWriter(); 
       
                 out.print("<title>Save Product</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
       
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
     
        
          // String item,product_id,product_name,price,quantity,hsn_code; 
          
        String item=request.getParameter("item");  
        String product_id=request.getParameter("product_id"); 
        String product_name=request.getParameter("product_name");  
        String price=request.getParameter("price");  
        int quantity=Integer.parseInt(request.getParameter("quantity"));  
        String hsn_code=request.getParameter("hsn_code"); 
          
        prod_list p=new prod_list();  
        p.setItem(item);
        p.setProduct_id(product_id);
        p.setProduct_name(product_name);
        p.setPrice(price);
        p.setQuantity(quantity);
        p.setHsn_code(hsn_code);
          
        int status=prodDao.save(p);  
        if(status>0){  
            out.print("<p>Record saved successfully!</p>");  
            response.sendRedirect("ViewServlet");
//request.getRequestDispatcher("ViewServlet").include(request, response);  
        }else{  
            out.println("Sorry! unable to save record");  
        }  
          
        out.close();  
    }
}
