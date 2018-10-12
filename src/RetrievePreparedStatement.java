import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RetrievePreparedStatement {

	public static void main(String args[]) throws SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			// establish connection
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", " ");
			//query to retrieve data from database
			String sql = "select * from employees where account < ?";
			pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			
			//setting the value to the placeholder
			pstmt.setInt(1, 103);
			//getback the data in rs object 
			rs = pstmt.executeQuery();
			
			//using some format to display the data
			String format  = "%-3s %-2s \n";
			while(rs.next())
			{
				System.out.format(format,rs.getString("id"),rs.getString("account"));
			}
			

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
