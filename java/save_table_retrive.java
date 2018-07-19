
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class save_table_retrive {

    public static List<save_table> getAll_save(int invoice) {

        List<save_table> list = new ArrayList<save_table>();

        try {
             
            

            String url = "jdbc:mysql://localhost:3306/exza?useSSL=true";
            String userMysql = "amruth";
            String pwdMysql = "root";
            String dbUser, dbPwd;

            Class.forName("com.mysql.jdbc.Driver");

            Connection con = DriverManager.getConnection(url, userMysql, pwdMysql);

            PreparedStatement ps = con.prepareStatement("select * from save_table where invoice=?");
            ps.setInt(1, invoice);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
               save_table s = new save_table();
                s.setSave_id(rs.getInt(1));
                s.setInvoice(rs.getInt(2));
                s.setInvoice_id(rs.getInt(3));
                s.setItem(rs.getString(4));
                s.setProduct_id(rs.getString(5));
                s.setProduct_name(rs.getString(6));
                s.setPrice(rs.getString(7));
                s.setQuantity(rs.getInt(8));
                s.setHsn_code(rs.getString(9));
                //
                s.setCustomer_id(rs.getInt(10));
                s.setCustomer(rs.getString(11));
                s.setCustomer_type(rs.getString(12));
                s.setCustomer_name(rs.getString(13));
                s.setInvoice_date(rs.getString(14));
                s.setCurrency(rs.getString(15));
                s.setCur_value(rs.getString(16));
                s.setStreet(rs.getString(17));
                s.setPostal_code(rs.getString(18));
                s.setCountry_id(rs.getString(19));
                s.setPhone(rs.getString(20));
                s.setEmail(rs.getString(21));
                s.setPan_no(rs.getString(22));
                s.setGst_no(rs.getString(23));
                s.setTax_name(rs.getString(24));
                s.setTax_value(rs.getString(25));
                s.setDiscount(rs.getString(26));
                list.add(s);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
