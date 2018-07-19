
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Response;


public class cc_custDao {
    
  
      
        public static Connection getConnection(){  
            Connection con=null;  
           
            
            try{  
                Class.forName("com.mysql.jdbc.Driver"); 
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exza?useSSL=true","amruth","root");  
            }catch(Exception e){System.out.println(e);}  
            return con;  
        }  
        public static int save(cc_customer c){  
            int status=0;
            try{  
                  
                Connection con=cc_custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "insert into cc_customer(customer,customer_type,customer_name,street,postal_code,country_id,phone,mobile,email,pan_no,gst_no) values (?,?,?,?,?,?,?,?,?,?,?);");  
                ps.setString(1,c.getCustomer());
                ps.setString(2,c.getCustomer_type());
                ps.setString(3,c.getCustomer_name());  
                ps.setString(4,c.getStreet());  
                ps.setString(5,c.getPostal_code()); 
                ps.setString(6,c.getCountry_id());
                ps.setString(7,c.getPhone());
                ps.setString(8,c.getMobile());
                ps.setString(9,c.getEmail());
                ps.setString(10,c.getPan_no());
                ps.setString(11,c.getGst_no());

                
               int i= ps.executeUpdate();  
                status=i;
                
  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static int update(cc_customer c,String customer_edit){  
            int status=0; 
            int i=0;
            
            System.out.println("customer_update"+customer_edit);
            try{  
                Connection con=cc_custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
              
                        "update cc_customer set customer=?,customer_type=?,customer_name=?,street=?,postal_code=?,country_id=?,phone=?,mobile=?,email=?,pan_no=?,gst_no=? where customer=?;");  
              
                ps.setString(1,c.getCustomer());
                ps.setString(2,c.getCustomer_type());
                ps.setString(3,c.getCustomer_name());
       
                ps.setString(4,c.getStreet());  
                ps.setString(5,c.getPostal_code()); 
                ps.setString(6,c.getCountry_id());
                ps.setString(7,c.getPhone());
                ps.setString(8,c.getMobile());
                ps.setString(9,c.getEmail());
                ps.setString(10,c.getPan_no());
                ps.setString(11,c.getGst_no());
                ps.setString(12,customer_edit);
                i= ps.executeUpdate();  
                status=i;  
                  System.out.println("status in custDao"+i);
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        public static int delete(String customer){  
            int status=0;  
            try{  
                Connection con=cc_custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("delete from cc_customer where customer=?;");  
                ps.setString(1,customer);
                status=ps.executeUpdate();  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static cc_customer getCustomerByID(String customer){  
            cc_customer c=new cc_customer();  
              
            try{  
                
                Connection con=cc_custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from cc_customer where customer=?"); 
                ps.setString(1, customer);
                ResultSet rs=ps.executeQuery();  
                if(rs.next()){
                    
        c.setCustomer_id(rs.getInt(1));
        c.setCustomer(rs.getString(2));
        c.setCustomer_type(rs.getString(3));
        c.setCustomer_name(rs.getString(4));
        c.setStreet(rs.getString(5));
        c.setPostal_code(rs.getString(6));
        c.setCountry_id(rs.getString(7));
        c.setPhone(rs.getString(8));
        c.setMobile(rs.getString(9));
        c.setEmail(rs.getString(10));
        c.setPan_no(rs.getString(11));
        c.setGst_no(rs.getString(12));
     
                   
                }  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return c;  
        }  
        
        
        public static List<cc_customer> getAllCustomers(){  
            List<cc_customer> list=new ArrayList<cc_customer>();  
           
              
            try{  
                Connection con=cc_custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from cc_customer;");  
                ResultSet rs=ps.executeQuery();  
                while(rs.next()){  
                    
                    
                    cc_customer c=new cc_customer(); 
                   
        c.setCustomer_id(rs.getInt(1));
        c.setCustomer(rs.getString(2));
        c.setCustomer_type(rs.getString(3));
        c.setCustomer_name(rs.getString(4));
        c.setStreet(rs.getString(5));
        c.setPostal_code(rs.getString(6));
        c.setCountry_id(rs.getString(7));
        c.setPhone(rs.getString(8));
        c.setMobile(rs.getString(9));
        c.setEmail(rs.getString(10));
        c.setPan_no(rs.getString(11));
        c.setGst_no(rs.getString(12));
     
                    
                    list.add(c);
                    
                }  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return list;  
        }
       
    }  
 



