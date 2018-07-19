

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logindao.LoginDao;

public class login extends HttpServlet {

 
    protected void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
  {
          String user=req.getParameter("user"); 
          String pwd=req.getParameter("pwd");
         LoginDao dao=new LoginDao();
         PrintWriter out=res.getWriter();
         
      out.print("<title>Login</title>");
       out.print("<link rel='shortcut icon' href='images/exza.png'>");        
        
     try{
          
            if(dao.check(user,pwd))
            {System.out.println("User name:"+user+" Paswword:"+pwd);
                HttpSession session=req.getSession() ;
                session.setAttribute("user",user);
                res.sendRedirect("welcome.jsp");   
            }
            else
            {
                res.sendRedirect("login.jsp");
            }
       } catch(Exception e)
            {  }
     
    /* if(user.equals("root") && pwd.equals("exzatech"))
            {
                HttpSession session=req.getSession() ;
                session.setAttribute("user",user);
                res.sendRedirect("welcome.jsp");   
            }
            else
            {
                res.sendRedirect("login.jsp");
            }*/
    }
}



