package ireena;

import java.io.*;
class ReadFile
{
	public static String HS;
	String strLine;
	public void read(){
	HS=String.valueOf(0);
      try{
		FileInputStream fstream = new FileInputStream("src/zJava/SCORE.txt");
		DataInputStream in = new DataInputStream(fstream);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
		while ((strLine = br.readLine()) != null) 	{
			HS=strLine;

		}
		in.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
	public void write(){
		try{
			FileWriter f = new FileWriter("src/zJava/SCORE.txt");
			f.write(HS);
			f.close();
		}catch(Exception e){
			System.err.println("Error: " + e.getMessage());
		}
	}
	public void compare(long x){
		long y;
		y=Long.valueOf(HS);
		if(x>y){
			y=x;
			HS=String.valueOf(y);
			write();
		}
	}
}
