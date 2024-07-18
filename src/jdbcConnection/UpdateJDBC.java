package jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class UpdateJDBC {

    public static void main(String[] args) throws Exception {
        String driver = "com.mysql.cj.jdbc.Driver";
        Class.forName(driver);
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "12345");

        PreparedStatement st = conn.prepareStatement("UPDATE studentsData SET StudentRegNo=? WHERE StudentName=?");
        st.setInt(1, 126);
        st.setString(2, "Ankita");

        int i = st.executeUpdate();
        if (i > 0) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }

        st.close();
        conn.close();
    }
}
