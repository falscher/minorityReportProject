import java.sql.SQLException;

public class JQueryControl {
	String row;
	MySQLControl control = new MySQLControl();

	// get row to add
	public String getRow() {
		return row;
	}

	public void setRow(String newRow) {
		row = newRow;
	}

	public void callMySQLServer() throws SQLException {
		control.connectServer();
		control.execute("CREATE DATABASE proj5;");
		control.execute("USE proj5;");
		control.execute("CREATE TABLE crimes(crime VARCHAR(20), x INT, y INT, calendarDate INT, time INT)");
		control.selectRow("*", "crimes", "");
	}
	
	public double calculateProbability() throws SQLException{
		int num = control.selectCount("*", "crimes", "crime='murder'");
		int total = control.selectCount(" DISTINCT (*)", "date", "");
		
		double avg=(double) num/total;
				
		PoissonCalculator calc = new PoissonCalculator();
		calc.calculateLambda(num);
		return calc.getLambda();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
