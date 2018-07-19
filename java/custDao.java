
       import java.util.*;  
    import java.sql.*;  
    
      
    public class custDao {  
      
        public static Connection getConnection(){  
            Connection con=null;  
            try{  
                Class.forName("com.mysql.jdbc.Driver"); 
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exza?useSSL=true","amruth","root");  
            }catch(Exception e){System.out.println(e);}  
            return con;  
        }  
        public static int save(customer c){  
            int status=0;  
            try{  
                  
                
                Connection con=custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "insert into customer(customer_id,customer,customer_type,customer_name,invoice_date,street,postal_code,country_id,phone,email,pan_no,gst_no) values (?,?,?,?,?,?,?,?,?,?,?,?);");  
                ps.setInt(1,c.getCustomer_id()); 
                ps.setString(2,c.getCustomer());
                ps.setString(3,c.getCustomer_type());
                ps.setString(4,c.getCustomer_name());
                ps.setString(5,c.getInvoice_date());
               
                ps.setString(6,c.getStreet());  
                ps.setString(7,c.getPostal_code()); 
                ps.setString(8,c.getCountry_id());
                ps.setString(9,c.getPhone());
                ps.setString(10,c.getEmail());
                ps.setString(11,c.getPan_no());
                ps.setString(12,c.getGst_no());

                System.out.println("status");
               int i= ps.executeUpdate();  
                status=i;
                
  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        
        public static int update(customer c){  
            int status=0; 
            int i=0;
            try{  
                Connection con=custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "update customer set invoice_date=? where customer_id=?;");  
                 
                ps.setString(1,c.getInvoice_date());
                
                ps.setInt(2, c.getCustomer_id());
                 
                
                i= ps.executeUpdate();  
                status=i;  
                  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        public static int delete(){  
            int status=0;  
            try{  
                Connection con=custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("truncate customer;");  
                status=ps.executeUpdate();  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static customer getCustomer(){  
            customer c=new customer();  
              
            try{  
                
                Connection con=custDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from customer "); 
                
             //   ps.setInt(1,customer_id);  where customer_id=?
                ResultSet rs=ps.executeQuery();  
                if(rs.next()){
        c.setCustomer_id(rs.getInt(1));
        c.setCustomer(rs.getString(2));
        c.setCustomer_type(rs.getString(3));
        c.setCustomer_name(rs.getString(4));
        c.setInvoice_date(rs.getString(5));
        
        c.setStreet(rs.getString(6));
        c.setPostal_code(rs.getString(7));
        c.setCountry_id(rs.getString(8));
        c.setPhone(rs.getString(9));
        c.setEmail(rs.getString(10));
        c.setPan_no(rs.getString(11));
        c.setGst_no(rs.getString(12));
          
                    
                    
                }  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return c;  
        }  
        
       
    }  
 

