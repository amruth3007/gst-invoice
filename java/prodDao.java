
       import java.util.*;  
    import java.sql.*;  
    
      
    public class prodDao {  
      
        public static Connection getConnection(){  
            Connection con=null;  
            
            
       
            try{  
                Class.forName("com.mysql.jdbc.Driver"); 
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exza?useSSL=true","amruth","root");  
            }catch(Exception e){System.out.println(e);}  
            return con;  
        }  
        public static int save(prod_list p){  
            int status=0;  
            try{  
                
                Connection con=prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "insert into prod_list(item,product_id,product_name,price,quantity,hsn_code) values (?,?,?,?,?,?);");  
                ps.setString(1,p.getItem());  
                ps.setString(2,p.getProduct_id());  
                ps.setString(3,p.getProduct_name());  
                ps.setString(4,p.getPrice()); 
                ps.setInt(5,p.getQuantity());
                ps.setString(6,p.getHsn_code());
                
                System.out.println("status");
               int i= ps.executeUpdate();  
                status=i;
                System.out.println("status calulated"+status);
  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        
        public static int update(prod_list p){  
            int status=0; 
            int i=0;
            try{  
                Connection con=prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "update prod_list set item=?,product_id=?,product_name=?,price=?,quantity=?,hsn_code=? where serial_id=?;");  
                ps.setString(1,p.getItem());  
                ps.setString(2,p.getProduct_id());  
                ps.setString(3,p.getProduct_name());  
                ps.setString(4,p.getPrice()); 
                ps.setInt(5,p.getQuantity());
                ps.setString(6,p.getHsn_code());  
                ps.setInt(7,p.getSerial_id());
                  System.out.println("update method");
                 i= ps.executeUpdate();  
                status=i;  
                  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        public static int delete(int serial_id){  
            int status=0;  
            try{  
                Connection con=prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("delete from prod_list where serial_id=?;");  
                ps.setInt(1,serial_id);  
                status=ps.executeUpdate();  
                  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static prod_list getProductById(int serial_id){  
            prod_list p=new prod_list();  
              
            try{  
                System.out.println(serial_id+"getProductByID");
                Connection con=prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from prod_list where serial_id=?"); 
                
                ps.setInt(1,serial_id);  
                ResultSet rs=ps.executeQuery();  
                if(rs.next()){
                    p.setSerial_id(rs.getInt(1));
                    p.setItem(rs.getString(2));
                    p.setProduct_id(rs.getString(3));
                    p.setProduct_name(rs.getString(4));
                    p.setPrice(rs.getString(5));
                    p.setQuantity(rs.getInt(6));
                    p.setHsn_code(rs.getString(7));
                    
                    
                }  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return p;  
        }  
        
        
        
        public static List<prod_list> getAllProducts(){  
            List<prod_list> list=new ArrayList<prod_list>();  
           
              
            try{  
                Connection con=prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from prod_list;");  
                ResultSet rs=ps.executeQuery();  
                while(rs.next()){  
                    
                    
                    prod_list p= new prod_list();  
                    p.setSerial_id(rs.getInt(1));
                    p.setItem(rs.getString(2));
                    p.setProduct_id(rs.getString(3));
                    p.setProduct_name(rs.getString(4));
                    p.setPrice(rs.getString(5));
                    p.setQuantity(rs.getInt(6));
                    p.setHsn_code(rs.getString(7));
                    list.add(p);
                    
                }  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return list;  
        }
        
    }  
 

