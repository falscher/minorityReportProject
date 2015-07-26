import java.sql.SQLException;

public class PoissonCalculator {
	double lambda;
	public double getLambda() {
		return lambda;
	}
	
	public void calculateLambda(double avg){
		lambda = Math.pow(Math.E, -avg) * (avg);
	}
	
	public void calculateDistribution(){
	}
	
	public void calculateLambdaUseLocation(int x, int y){
	}
	
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		MySQLControl control = new MySQLControl();
		control.connectServer();
		control.execute("USE proj5;");
		int num = control.selectCount("*", "crimes", "crime='murder'");
		int total = control.selectCount(" DISTINCT (*)", "crimes", "");
		
		double avg=(double) num/total;
		
		System.out.println(num);
		
		PoissonCalculator calc = new PoissonCalculator();
		calc.calculateLambda(num);
		System.out.println(calc.getLambda());
	}
}
