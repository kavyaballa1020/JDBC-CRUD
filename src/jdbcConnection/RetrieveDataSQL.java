package jdbcConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RetrieveDataSQL {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/student?useSSL=false", "root", "12345");

        PreparedStatement st = conn.prepareStatement("SELECT * FROM student");
        ResultSet rs=st.executeQuery();
        while(rs.next())
        {
        	System.out.println(rs.getString(1));
        }

         st.close();
        conn.close();
    }
}
