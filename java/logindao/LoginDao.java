
package logindao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


public class LoginDao {
    boolean login=false;
    public boolean check(String user,String pwd) throws Exception
    {
        System.out.println("Inside check method");      
 String sql="SELECT * FROM login WHERE user='"+user+ "'";  
 String url="jdbc:mysql://localhost:3306/exza?useSSL=true";
 String userMysql="amruth";
 String pwdMysql="root";
 String dbUser,dbPwd; 
 
try
{
    Class.forName("com.mysql.jdbc.Driver");
 
Connection con=DriverManager.getConnection(url,userMysql,pwdMysql);  
        
Statement stmt=con.createStatement(); 
ResultSet rs=stmt.executeQuery(sql);
       
        
while(rs.next())
{
    
    dbUser=rs.getString("user");
    dbPwd=rs.getString("password");
    if(dbUser.equals(user)&& dbPwd.equals(pwd))
        {
        
            login=true;
            return login;
        }
 
}}catch(Exception e)
{
    System.out.println(e);}
  
return login;
    }
}
    


