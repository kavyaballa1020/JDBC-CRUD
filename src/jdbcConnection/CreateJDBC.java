package jdbcConnection;
import java.sql.*;

public class CreateJDBC {

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","12345");
		System.out.println("Connection Established");

	}

}
