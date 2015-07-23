import java.sql.*;

public class statistics {

	Connection conn;
	PreparedStatement select_count;

	// constructor
	public  statistics() {
		// create a connection
		String url = "jdbc:mysql://localhost:3306/proj5";
		String userame = "root";
		String password = "hello";

		// java.sql.*: use sql comments
		// create a connect;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IllegalAccessException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (ClassNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(url, userame, password);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			select_count = conn.prepareStatement("SELECT COUNT(*) FROM crimes WHERE crime='murder';");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}// end
		// of
		// constructor

	double poisson(String crime, int x, int y, int date, int hour) {
		try {
		/*
		this.select_count.setString(1, crime);
		this.select_count.setInt(2, x);
		this.select_count.setInt(3, y);
		this.select_count.setInt(4, date);
		this.select_count.setInt(5, hour);
*/
		ResultSet rs;
			rs = select_count.executeQuery();
		int avg = 0;
		while (rs.next()) { // common way to write JDBC
			System.out.println(rs.getInt(1));
			avg = rs.getInt(1);
		}
		//System.out.println(avg);
		return Math.pow(Math.E, -avg) * (avg); // Poisson Probability
												// calculation
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hour;
	}
	
	public static void main(String[] args) throws SQLException {
		statistics stat = new statistics();
		System.out.println(stat.poisson("murder", 10, 20, 169, 12));
	}
}
