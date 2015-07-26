import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class DataGenerator {
	static int numRow = 10;
	static Random randomGenerator = new Random();
	static FileWriter writer = null;
	
	public static final void main(String... aArgs) throws IOException{
		genCrime();
		genWeather();
		genGeog();
		genPolice();
	  }
	 
	  
	private static void genPolice() throws IOException {
		writer = new FileWriter("policeData.txt");
		String [] firstName = {"Li","Shengming","Jiakuan","Elliot"};
		String [] lastName = {"Wang","Zhang","Li","Chung"};
		  for (int idx = 1; idx <= numRow; ++idx){
		      String randomFirstName = firstName[randomGenerator.nextInt(firstName.length)];
		      String randomLastName = lastName[randomGenerator.nextInt(lastName.length)];

		      writer.append(randomFirstName + ", " + randomLastName);
		      writer.append("\n");
		    }
		  writer.flush();
		  writer.close();	
	}


	private static void genGeog() throws IOException {
		  writer = new FileWriter("geogData.txt");
		  
		  int [][] xyArray = {{102,151},{186,173},{165,255},{209,100},{273,202},{356,225},
		  {461,195},{440,251},{529,272},{541,221},{612,297},{630,237}};
		  
		  String [] location = {"N Church St & W Hancock Ave","N Pope Str & W Hancock Ave", "N Pope Str & Megis Str",
				  "N Pope St & Reese St", "N Finley St & W Hancock Ave", "N Newton St & W Hancock Ave"
				  ,"Pulanski St & W Clayton St", "Pulanski St & W Hancock Ave", "N Hull St & W Hancock Ave", "N Hull St & W Clayton St",
				  "N Lumpkin St & E Hancock Ave" , "N Lumpkin St & W Clayton St"};
		  for (int idx = 1; idx <= numRow; ++idx){
		      
		      int randNum=randomGenerator.nextInt(xyArray.length);
		      int x = xyArray[randNum][0];
		      int y = xyArray[randNum][1];
		      String randomLoc = location[randNum];
		      
		      writer.append(x + ", " + y + ", " + randomLoc);
		      writer.append("\n");
		    }
		  writer.flush();
		  writer.close();

		
	}


	private static void genWeather() throws IOException {
		  writer = new FileWriter("weatherData.txt");

		  int [][] xyArray = {{102,151},{186,173},{165,255},{209,100},{273,202},{356,225},
		  {461,195},{440,251},{529,272},{541,221},{612,297},{630,237}};
		  
		  for (int idx = 1; idx <= numRow; ++idx){
		      
		      int randNum=randomGenerator.nextInt(xyArray.length);
		      int x = xyArray[randNum][0];
		      int y = xyArray[randNum][1];
		      
		      int randomDay = randomGenerator.nextInt(365);
		      int randomTime = randomGenerator.nextInt(24);
		      int randomTemp = randomGenerator.nextInt(101);
		      writer.append(x + ", " + y + ", " + randomDay + ", " + randomTime + ", " + randomTemp);
		      writer.append("\n");
		    }
		  writer.flush();
		  writer.close();
		
	}


	public static void genCrime () throws IOException{
		  writer = new FileWriter("crimeData.txt");
		  String [] crimeType = {"assault","drug","burgalry","arson","disorderly conduct",
				  "kidnapping","prostitution","rape"};
		  int [][] xyArray = {{102,151},{186,173},{165,255},{209,100},{273,202},{356,225},
		  {461,195},{440,251},{529,272},{541,221},{612,297},{630,237}};
		  for (int idx = 1; idx <= numRow; ++idx){
		      String randomCrime = crimeType[randomGenerator.nextInt(crimeType.length)];
		      
		      int randNum=randomGenerator.nextInt(xyArray.length);
		      int x = xyArray[randNum][0];
		      int y = xyArray[randNum][1];
		      
		      int randomDay = randomGenerator.nextInt(365);
		      int randomTime = randomGenerator.nextInt(24);
		      writer.append(randomCrime + ", ");
		      writer.append(x + ", ");
		      writer.append(y + ", ");
		      writer.append(randomDay + ", ");
		      writer.append(randomTime + ", ");
		      writer.append("\n");
		    }
		  writer.flush();
		  writer.close();
			
	  }
}

