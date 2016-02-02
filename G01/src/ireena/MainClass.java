package ireena;

import javax.swing.JFrame;

public class MainClass {
	public static Level1 ob1 = new Level1();	
	public static long t1,t2,t3,t4,timeDifference=0;
	public static long score,score2,score3;

	public static JFrame MENU = new JFrame("Ireena");
	public static JFrame BOARD_2 = new JFrame("Ireena");
	public static JFrame BOARD_3 = new JFrame("Ireena");
	public static JFrame BOARD_4 = new JFrame("Ireena"); 
	public static JFrame SETTINGS = new JFrame("Ireena");
	public static JFrame ABOUT = new JFrame("Ireena");
	public static JFrame HELP = new JFrame("Ireena");
	
	public static void main(String args[]){
		new MainClass();
	}
	
	public MainClass(){
		MENU.add(new Menu());
		MENU.setVisible(false);
		BOARD_2.add(ob1);
		BOARD_2.setVisible(false);
		BOARD_3.setVisible(false);
		BOARD_4.setVisible(false);
		
		ABOUT.add(new About());
		ABOUT.setVisible(false);
		HELP.add(new Help());
		HELP.setVisible(false);
		SETTINGS.add(new Settings());
		SETTINGS.setVisible(false);
		
		go();
	}
	
	public void go(){
			MENU.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			MENU.setSize(800,350);
			MENU.setResizable(false);
			//f1.setExtendedState(f1.MAXIMIZED_BOTH);  
			//f1.setUndecorated(true);  
			MENU.setVisible(true);
			
			MENU.setLocationRelativeTo(null);

	}

}
