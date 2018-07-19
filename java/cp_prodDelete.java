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

/**
 *
 * @author amruth
 */
public class cp_prodDelete extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         PrintWriter out=response.getWriter(); 
            
            response.setHeader("cache-control","no-cache,no-store,must-revalidate");//HTTP 1.1
            response.setHeader("pragma","no-cache");//HTTP 1.0
            response.setHeader("Expires","0");//Proxies
            HttpSession session=request.getSession();
            if(session.getAttribute("user") == null)
                    {
                        response.sendRedirect("login.jsp");
                    }
 
             out.print("<title> Product Delete</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");
        String prod_id=request.getParameter("pro_id");  
        int pro_id=Integer.parseInt(prod_id);
        cp_prodDao.delete(pro_id);
       // out.print("<script>alert ('Record deleted successfully!');</script>");
        response.sendRedirect("welcome.jsp");
       
    }  
}