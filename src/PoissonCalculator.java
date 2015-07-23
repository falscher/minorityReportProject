import java.sql.SQLException;

public class PoissonCalculator {
	double lambda;
	public double getLambda() {
		return lambda;
	}
	
	public void calculateLambda(int avg){
		lambda = Math.pow(Math.E, -avg) * (avg);
	}
	
	public void calculateDistribution(){
		
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MySQLControl control = new MySQLControl();
		control.connectServer();
		control.execute("USE proj5;");
		int num = control.selectCount("*", "crimes", "crime='murder'");
		System.out.println(num);
		
		PoissonCalculator calc = new PoissonCalculator();
		calc.calculateLambda(num);
		System.out.println(calc.getLambda());
	}
}
