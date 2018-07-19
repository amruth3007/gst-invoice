

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class tax_Dao {
    
  
      
        public static Connection getConnection(){  
            Connection con=null;  
            try{  
                Class.forName("com.mysql.jdbc.Driver"); 
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exza?useSSL=true","amruth","root");  
            }catch(Exception e){System.out.println(e);}  
            return con;  
        }  
        public static int save(tax t){  
            int status=0;
            try{  
                  
                Connection con=tax_Dao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "insert into tax(tax_name,tax_value) values(?,?);");  
                ps.setString(1,t.getTax_name());
                ps.setString(2,t.getTax_value());
                
               int i= ps.executeUpdate();  
                status=i;
                
  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static int update(tax t,String tax_edit){  
            int status=0; 
            int i=0;
            
            
            try{  
                Connection con=tax_Dao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
              
                        "update tax set tax_name=?,tax_value=? where tax_name=?;");  
              
                ps.setString(1,t.getTax_name());
                ps.setString(2,t.getTax_value());
                ps.setString(3,tax_edit);
    
                i= ps.executeUpdate();  
                status=i;  
                  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        public static int delete(String tax_name){  
            int status=0;  
            try{  
                Connection con=tax_Dao.getConnection();  
                PreparedStatement ps=con.prepareStatement("delete from tax where tax_name=?;");  
                ps.setString(1,tax_name);
                status=ps.executeUpdate();  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static tax getTaxByID(String tax_name){  
            tax t=new tax();  
              
            try{  
                
                Connection con=tax_Dao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from tax where tax_name=?"); 
                ps.setString(1, tax_name);
                ResultSet rs=ps.executeQuery();  
                if(rs.next()){
         t.setTax_key(rs.getInt(1));
         t.setTax_name(rs.getString(2));
         t.setTax_value(rs.getString(3));
        
                }  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return t;  
        }  
        
        
        public static List<tax> getAllTaxes(){  
            List<tax> list=new ArrayList<tax>();  
           
              
            try{  
                Connection con=tax_Dao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from tax;");  
                ResultSet rs=ps.executeQuery();  
                while(rs.next()){  
                    
                    tax t=new tax();
                    
                   
        t.setTax_key(rs.getInt(1));
         t.setTax_name(rs.getString(2));
         t.setTax_value(rs.getString(3));
                    
                    list.add(t);
                    
                }  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return list;  
        }
       
    }  
 



