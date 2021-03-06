import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
public class UpdatingRecordPreparedStatement {
	
	
	public static void main(String args[]) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", " ");
			String sql = "update employees set account = ? where id  = ?";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//scanning values for placeholders
			Scanner s  = new Scanner(System.in);
			int account = s.nextInt();
			int id = s.nextInt();
			pstmt.setInt(1, account);
			pstmt.setInt(2, id);
			//as we are executing insert statement so will execute executeupdate method
			int result = pstmt.executeUpdate();
			System.out.println("no of rows manipulated"  + result);
			if(result == 1)
			System.out.println("success");
			else System.out.println("not success");

		} catch (ClassNotFoundException e1) {
			System.out.println("driver not loaded");
			e1.printStackTrace();
		} finally {
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
