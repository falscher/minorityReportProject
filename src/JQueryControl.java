import java.sql.SQLException;

public class JQueryControl {
	private String crime;
	private String row;
	private MySQLControl control = new MySQLControl();


	public JQueryControl(String crime) throws SQLException {
		this.crime = crime;
		callMySQLServer();
	}

	// get row to add
	public String getRow() {
		return row;
	}

	public void setRow(String newRow) {
		row = newRow;
	}

	public void callMySQLServer() throws SQLException {
		control.connectServer();
		//control.execute("CREATE DATABASE proj5;");
		control.execute("USE proj5;");
		//control.execute("CREATE TABLE crimes(crime VARCHAR(20), x INT, y INT, calendarDate INT, time INT)");
		//control.selectRow("*", "crimes", "");
	}
	
	public double calculateProbability() throws SQLException{
		int num = control.selectCount("*", "crimes", "crime='"+this.crime+"'");
		int total = control.selectCount(" DISTINCT (*)", "date", "");
		
		double avg=(double) num/total;
				
		PoissonCalculator calc = new PoissonCalculator();
		calc.calculateLambda(num);
		return calc.getLambda();
	}
	
	public int[][] calculateSchedule() throws SQLException{
		//control.selectRow("crime, x, y, time, temp, description", "crimes, geographic, weather", "x=x, y=y, time=time, cal=cal");
		
		int [][] xyArray = {{102,151},{186,173},{165,255},{209,100},{273,202},{356,225},
				  {461,195},{440,251},{529,272},{541,221},{612,297},{630,237}};
		//go through x, y
		for(int i=0; i<xyArray.length; i++){
			//calculate chance of crime happening in area
			int x = xyArray[i][0];
			int y = xyArray[i][1];
			int num = control.selectCount("*", "crimes", "x="+x + " AND y="+y);
			int total = control.executeCount("SELECT COUNT(DISTINCT x) FROM crimes");

			double avg=(double) num/total;
			PoissonCalculator calc = new PoissonCalculator();
			calc.calculateLambda(num);
			System.out.println(num + ", " + total + ", " + calc.lambda);
			
			//find most common crime
			control.selectRow("crime,crimes.x,crimes.y,crimes.time,temp,description, MAX(crime)", "crimes, weather, geographic", "crimes.x="+x + " AND crimes.y="+y);
			control.printLastRow();
			
			System.out.println();
			System.out.println();
		}
			//go through time
		return null;
		
	}

}
