package jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

public class insertPostgres {

    public static void main(String[] args) {
        try {
            String driver = "org.postgresql.Driver";
            Class.forName(driver);
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/my_flask_db?useSSL=false", "postgres", "12345");
            PreparedStatement st = conn.prepareStatement("INSERT INTO users values(?,?,?,?,?)");
            st.setInt(1, 3); 
            st.setInt(2, 156);
            st.setString(3, "Kavya");
            st.setInt(4, 20);
            st.setString(5, "address");
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
