package quanlysva;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
 
public class ConnectMysqlExample {
    private static String DB_URL = "jdbc:mysql://localhost:3306/qlsv";
    private static String USER_NAME = "root";
    private static String PASSWORD = "";
	private static List<Student> studentList;
	static Connection conn = null;
    static PreparedStatement stmt = null;

    public static void insert(Student st) {
    	try {
    		Connection conn = Connections.getConnection(DB_URL, USER_NAME, PASSWORD);
    		String sql = "insert into person(masv, name, class, diem)"
    	                + "values(?,?,?,?)";
    		PreparedStatement pstmt = conn.prepareStatement(sql);
    		pstmt.setString(1,st.getMasv());
    		pstmt.setString(2, st.getHoTen());
    		pstmt.setString(3, st.getLop());
    		pstmt.setFloat(4, st.getDiem());
    		pstmt.execute();
    		System.out.println("Insert successfully!");
    	}catch (Exception ex) {
    		System.out.println("Insert failure!");
            ex.printStackTrace();
        }
    }
    
    public static List<Student>getStudentList() {
    	List<Student> studentList = new ArrayList<>();
        try {
            // connnect to database 'testdb'
            Connection conn = Connections.getConnection(DB_URL, USER_NAME, PASSWORD);
            // crate statement
            Statement stmt = conn.createStatement();
            // get data from table 'student'
            ResultSet rs = stmt.executeQuery("select * from person");
            System.out.printf("%-20s %-20s %-10s %-10s" , "MaSV","Ho Ten","Lop","Diem");
        	System.out.println ();
            // show data
            while (rs.next()) {
            	Student st = new Student (rs.getString("masv"), rs.getString("name"), rs.getString("class"),rs.getFloat("diem"));
            	System.out.printf("%-21s",rs.getString("masv"));
            	System.out.printf("%-21s",rs.getString("name"));
            	System.out.printf("%-11s",rs.getString("class"));
            	System.out.printf("%-21s",rs.getFloat("diem"));
            	System.out.println ();
            	studentList.add(st);
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		return studentList;
    }
 
} 