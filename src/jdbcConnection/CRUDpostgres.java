package jdbcConnection;

import java.sql.*;
import java.util.Scanner;

public class CRUDpostgres {

    private static Connection getConnection() throws Exception {
        String driver = "org.postgresql.Driver";
        Class.forName(driver);
        return DriverManager.getConnection("jdbc:postgresql://localhost:5432/CRUDdatabase?useSSL=false", "postgres", "12345");
    }

    private static void insertData() {
        try (Connection conn = getConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Student Registration Number:");
            int regNo = sc.nextInt();
            System.out.println("Enter Student Name:");
            String name = sc.next();
            System.out.println("Enter Student Age:");
            int age = sc.nextInt();

            PreparedStatement st = conn.prepareStatement("INSERT INTO crudoperations values(?,?,?)");
            st.setInt(1, regNo);
            st.setString(2, name);
            st.setInt(3, age);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("Insert success");
            } else {
                System.err.println("Insert fail");
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            System.err.println("Error: Duplicate entry for the primary key.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteData() {
        try (Connection conn = getConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Student Registration Number to delete:");
            int regNo = sc.nextInt();

            PreparedStatement st = conn.prepareStatement("DELETE FROM crudoperations WHERE id=?");
            st.setInt(1, regNo);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("Delete success");
            } else {
                System.err.println("Delete fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateData() {
        try (Connection conn = getConnection()) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Enter Student Name to update:");
            String name = sc.next();
            System.out.println("Enter new Student Registration Number:");
            int regNo = sc.nextInt();

            PreparedStatement st = conn.prepareStatement("UPDATE crudoperations SET name=? WHERE id=?");
            st.setString(1, name);
            st.setInt(2,regNo);

            int i = st.executeUpdate();
            if (i > 0) {
                System.out.println("Update success");
            } else {
                System.err.println("Update fail");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void retrieveData() {
        try (Connection conn = getConnection()) {
            PreparedStatement st = conn.prepareStatement("SELECT * FROM crudoperations");
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                System.out.println("RegNo: " + rs.getInt(1) + ", Name: " + rs.getString(2) + ", Age: " + rs.getInt(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Select an option: for manipulating postgress DB");
            System.out.println("1. Insert Data");
            System.out.println("2. Delete Data");
            System.out.println("3. Update Data");
            System.out.println("4. Retrieve Data");
            System.out.println("5. Exit");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertData();
                    break;
                case 2:
                    deleteData();
                    break;
                case 3:
                    updateData();
                    break;
                case 4:
                    retrieveData();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
