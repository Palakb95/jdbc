import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class InParamCallStoreProcedure {
	public static void main(String args[]) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", " ");
			CallableStatement cs = conn.prepareCall("{call storedprocedure(?,?,?,?}");
			Scanner s = new Scanner(System.in);
			System.out.println("enter id : ");
			int id = s.nextInt();
			System.out.println("enter firstname : ");
			String firstname = s.nextLine();
			System.out.println("enter lastname :");
			String lastname = s.nextLine();
			System.out.println("enter salary :");
			int salary = s.nextInt();
			cs.setInt(1, id);
			cs.setString(2, firstname);
			cs.setString(3, lastname);
			cs.setInt(4, salary);
			cs.execute();
			System.out.println("updated successfully");
			
			
			
		} 
		
		catch (ClassNotFoundException e1)
		{
			System.out.println("driver not loaded");
			e1.printStackTrace();
		} 
		finally 
		{
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}

		}

	}



}
