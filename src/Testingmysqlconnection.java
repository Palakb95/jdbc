import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Testingmysqlconnection {

	public static void main(String args[]) throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/world", "root", " ");
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery("select * from employees where Account <= 103");
			rs.beforeFirst();
			System.out.println("first 3 rownums:");
			String format  = "%-3s %-2s \n";
			while(rs.next())
			{
				System.out.format(format,rs.getString("id"),rs.getString("account"));
			}
			System.out.println("last row is :");
			rs.afterLast();
			while(rs.previous())
			{
				System.out.format(format,rs.getInt("id"),rs.getInt("account"));
			}
			rs.first();
			System.out.println("first row");
			System.out.format(format,rs.getInt("id"),rs.getInt("account"));
			
			rs.last();
			System.out.println("last row");
			System.out.format(format,rs.getInt("id"),rs.getInt("account"));
			
			rs.absolute(3);
			System.out.println("third row");
			System.out.format(format,rs.getInt("id"),rs.getInt("account"));
			
			rs.first();
			rs.relative(2);
			System.out.println("relative");
			System.out.format(format,rs.getInt("id"),rs.getInt("account"));
			
			
		}
		catch (ClassNotFoundException e1) {
			System.out.println("driver not loaded");
			e1.printStackTrace();
		}
		finally
		{
			if(rs != null)
			{
				rs.close();
			}
			if(stmt != null)
			{
				stmt.close();
			}
			if(conn != null)
			{
				conn.close();
			}
			
		}

	}

}
