package jdbcConnection;

import java.sql.DriverManager;
import java.sql.*;

public class InsertJDBC {
    public static void main(String[] args) {
        try {
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "12345");
            PreparedStatement st = conn.prepareStatement("INSERT INTO studentsData values(?,?,?)");
            st.setInt(1,123 ); 
            st.setString(2, "Ankita");
            st.setInt(3, 20);
            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("success");
            } else {
                System.err.println("fail");
            }
            st.close();
            conn.close();
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Duplicate entry for the primary key.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
