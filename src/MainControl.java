import java.sql.SQLException;

public class MainControl {

	public static void main(String[] args) throws SQLException {
		MySQLControl sql = new MySQLControl();
		JQueryControl jqu = new JQueryControl();
		PoissonCalculator poi = new PoissonCalculator();
		
		//setup connection to database
		sql.connectServer();
		sql.execute("USE proj5;");
		

	}

}
