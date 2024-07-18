package jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class DeleteJDBC {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "12345");

        PreparedStatement st = conn.prepareStatement("DELETE FROM studentsData WHERE StudentRegNo=?");
        st.setInt(1, 157);

        int i = st.executeUpdate();
        if (i > 0) {
            System.out.println("Success");
        } else {
            System.out.println("Fail");
        }
        st.close();
        conn.close();
    }
}
