
       import java.util.*;  
    import java.sql.*;  
    
      
    public class cp_prodDao {  
      
        public static Connection getConnection(){  
            Connection con=null;  
            
            
       
            try{  
                Class.forName("com.mysql.jdbc.Driver"); 
                con=DriverManager.getConnection("jdbc:mysql://localhost:3306/exza?useSSL=true","amruth","root");  
            }catch(Exception e){System.out.println(e);}  
            return con;  
        }  
        public static int save(cp_product p){  
            int status=0;  
            try{  
                
                Connection con=cp_prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "insert into cp_product(item,product_id,product_name,price) values (?,?,?,?);");  
                ps.setString(1,p.getItem());  
                ps.setString(2,p.getProduct_id());  
                ps.setString(3,p.getProduct_name());  
                ps.setString(4,p.getPrice()); 
 
                
                System.out.println("status");
               int i= ps.executeUpdate();  
                status=i;
                System.out.println("status calulated"+status);
  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        
        public static int update(cp_product p,String product_edit){  
            int status=0; 
            int i=0;
            try{  
                Connection con=cp_prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement(  
                             "update cp_product set item=?,product_id=?,product_name=?,price=? where product_id=?;");  
                ps.setString(1,p.getItem());  
                ps.setString(2,p.getProduct_id());  
                ps.setString(3,p.getProduct_name());  
                ps.setString(4,p.getPrice()); 
                ps.setString(5,product_edit);
                  
                 i= ps.executeUpdate();  
                status=i;  
                  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return status;  
        }  
        public static int delete(int pro_id){  
            int status=0;  
            try{  
                Connection con=cp_prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("delete from cp_product where pro_id=?;");  
                ps.setInt(1,pro_id);  
                status=ps.executeUpdate();  
                  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return status;  
        }  
        
        
        
        public static cp_product getProductById(String product_id){  
            cp_product p=new cp_product();  
              
            try{  
                
                Connection con=cp_prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from cp_product where product_id=?"); 
                
                ps.setString(1,product_id);  
                ResultSet rs=ps.executeQuery();  
                if(rs.next()){
                    p.setPro_id(rs.getInt(1));
                    p.setItem(rs.getString(2));
                    p.setProduct_id(rs.getString(3));
                    p.setProduct_name(rs.getString(4));
                    p.setPrice(rs.getString(5));
                    
                    
                    
                }  
                con.close();  
            }catch(Exception ex){ex.printStackTrace();}  
              
            return p;  
        }  
        
        
        
        public static List<cp_product> getAllProducts(){  
            List<cp_product> list=new ArrayList<cp_product>();  
           
              
            try{  
                Connection con=cp_prodDao.getConnection();  
                PreparedStatement ps=con.prepareStatement("select * from cp_product;");  
                ResultSet rs=ps.executeQuery();  
                while(rs.next()){  
                    
                    
                    cp_product p= new cp_product();  
                    p.setPro_id(rs.getInt(1));
                    p.setItem(rs.getString(2));
                    p.setProduct_id(rs.getString(3));
                    p.setProduct_name(rs.getString(4));
                    p.setPrice(rs.getString(5));
                    
                    list.add(p);
                    
                }  
                con.close();  
            }catch(Exception e){e.printStackTrace();}  
              
            return list;  
        }
        
    }  
 

