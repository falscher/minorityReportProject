import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MySQLControl {
	private Connection conn = null;
	private final String userName = "root";
	private final String serverName = "localhost";
	private final int portNumber = 3306;
	private final String dbName = "events";
	private List<Comparable[]> rows = new ArrayList<>();

	// create table
	public void createTable(String name, String[] attributes) {
		try {
			String createString = "CREATE TABLE " + name + " (" + attributes + ")";
			this.executeUpdate(conn, createString);
			System.out.println("Created a table");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}

	// add row to table
	public void addRow(String[] input, String name, String[] attributes) {
		try {
			String createString = "INSERT INTO " + name + " (" + attributes + ")";
			this.executeUpdate(conn, createString);
			System.out.println(createString);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not create the table");
			e.printStackTrace();
			return;
		}
	}

	// select row from table
	public void selectRow(String attribute, String table, String predicate) throws SQLException{
		Statement stmt = null;
		List<Comparable> array = new ArrayList<>();

		try {
			stmt = conn.createStatement();
			String createString = "SELECT " + attribute + " FROM " + table ;
			if(!predicate.equals("")){
				createString += " Where " + predicate;
			}
			ResultSet rs = stmt.executeQuery(createString);

			while (rs.next()) {
				// add stuff to table
				for(int i=1; i<rs.getMetaData().getColumnCount()+1; i++){
					array.add((Comparable) rs.getObject(i));
				}
				/*
				array.add(rs.getString("crime"));
				array.add(rs.getInt("x"));
				array.add(rs.getInt("y"));
				array.add(rs.getInt("calendarDate"));
				array.add(rs.getInt("time"));
				*/
				Comparable[] foo = array.toArray(new Comparable[array.size()]);
				//System.out.println(foo[0]);
				array.clear();
				rows.add(foo);
			}
			System.out.println(createString);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute select");
			e.printStackTrace();
			return;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
	//does select count
	public int selectCount(String attribute, String table, String predicate) throws SQLException{
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			String createString = "SELECT COUNT(" + attribute + ") FROM " + table;
			if(!predicate.equals("")){
				createString += " WHERE " + predicate;
			}
			createString += ";";
			createString = "SELECT COUNT(*) FROM crimes WHERE crime='murder';";
			ResultSet rs = stmt.executeQuery(createString);
			System.out.println(createString);
			//return 0;
			int avg = 0;
			while (rs.next()) { // common way to write JDBC
				avg = rs.getInt(1);
			}
			return avg;
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute select");
			e.printStackTrace();
			return 0;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	// connect to server
	public void connectServer() {
		try {
			conn = this.getConnection();
			System.out.println("Connected to database");
		} catch (SQLException e) {
			System.out.println("ERROR: Could not connect to the database");
			e.printStackTrace();
			return;
		}
	}

	// put together connection path
	public Connection getConnection() throws SQLException {
		Connection conn = null;
		Properties connectionProps = new Properties();
		connectionProps.put("user", this.userName);
		String password = "hello";
		connectionProps.put("password", password);

		conn = DriverManager.getConnection(
				"jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" + this.dbName, connectionProps);

		return conn;
	}

	// update table with command
	public boolean executeUpdate(Connection conn, String command) throws SQLException {
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(command); // This will throw a SQLException if it
											// fails
			return true;
		} finally {

			// This will run whether we throw an exception or not
			if (stmt != null) {
				stmt.close();
			}
		}
	}

	public void execute(String command) {
		try {
			this.executeUpdate(conn, command);
			System.out.println("Executed " + command);
		} catch (SQLException e) {
			System.out.println("ERROR: Could not execute " + command);
			e.printStackTrace();
			return;
		}
	}

	public static void main(String[] args) throws SQLException {
		MySQLControl app = new MySQLControl();
		app.connectServer();
		// app.execute("CREATE DATABASE proj5;");
		app.execute("USE proj5;");
		// app.execute("CREATE TABLE crimes(crime VARCHAR(20), x INT, y INT,
		// calendarDate INT, time INT)");
		// app.execute(
		// "LOAD DATA INFILE '/home/esc/Documents/data.txt' INTO TABLE crimes
		// TERMINATED BY ',' LINES TERMINATED BY '\n';");
		// app.execute("SELECT * FROM crimes;");
		//app.executeSelectTest("SELECT * FROM crimes;");
		app.selectRow("*", "crimes", "");
		// app.execute(INSERT);
		// app.execute("source
		// /home/esc/workspace/Project5/mysql/createTables.sql;");
		// app.execute("DROP DATABASE proj5;");
		// app.selectRow("crime", "crimes", "");
	}
}
