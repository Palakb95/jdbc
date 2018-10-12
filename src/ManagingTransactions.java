import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class ManagingTransactions {
	
	public static void main(String args[]) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", " ");
			Scanner s = new Scanner(System.in);
			System.out.println("enter from account");
			int fromacc = s.nextInt();
			System.out.println("enter amount withdraw:");
			int amount = s.nextInt();
			String sql = "update transfer set balance = balance - ? where account = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, fromacc);
			pstmt.execute();
			System.out.println("deducted");
			System.out.println("enter toaccount");
			int toaccount = s.nextInt();
			String to = "update transfer set balance = balance + ? where account = ?";
			pstmt = conn.prepareStatement(to);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, toaccount);
			pstmt.execute();
			System.out.println("transferred");
			
			

		} catch (ClassNotFoundException e1) {
			System.out.println("driver not loaded");
			e1.printStackTrace();
		} finally {
			//closing all connections in reverse order
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
