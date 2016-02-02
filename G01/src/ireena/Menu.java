package ireena;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Menu extends JPanel implements ActionListener{
	int option=1;
	Timer t;
	Image bg,img;
	public static Sound SD;
	Sound SD_D,SD_U;
	static Font font=new Font("Snap ITC",Font.PLAIN,28);
	
	public Menu(){		
		addKeyListener(new AL2());
		setFocusable(true);
		
		bg= new ImageIcon("src/zJAVA/w3.jpg").getImage();
		img= new ImageIcon("src/zJava/Walk2.gif").getImage();
		SD=new Sound("src/zJava/Sounds/menuMusic.wav");
		SD_D=new Sound("src/zJava/Sounds/down.wav");
		SD_U=new Sound("src/zJava/Sounds/up.wav");
		
		if(Sound.KEY)
			Menu.SD.play();
		
		t=new Timer(55,this);
		t.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(option>5)
			option=1;
		else if(option<1)
			option=5;
		repaint();
		
	}
	
	Graphics2D g2d;
	public void paint(Graphics g){
		
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		
		g2d.setFont(font);
		g2d.drawImage(bg, 0,0,null);
		g2d.drawImage(img, 50,80,null);
		
		switch(option){

		case 1:{			
			g2d.setColor(Color.RED);
			g2d.drawString("Play",380,100);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Settings",350,130);
			g2d.drawString("About",370,160);
			g2d.drawString("Help",380,190);
			g2d.drawString("Exit",380,220);		
			break;
		}

		case 2:{
			g2d.setColor(Color.RED);
			g2d.drawString("Settings",350,130);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Play",380,100);	
			g2d.drawString("About",370,160);
			g2d.drawString("Help",380,190);
			g2d.drawString("Exit",380,220);	
			break;
		}
		
		case 3:{
			About.y=350;
			g2d.setColor(Color.RED);
			g2d.drawString("About",370,160);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Play",380,100);
			g2d.drawString("Settings",350,130);			
			g2d.drawString("Help",380,190);
			g2d.drawString("Exit",380,220);
			break;
		}
		case 4:{
			g2d.setColor(Color.RED);
			g2d.drawString("Help",380,190);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Play",380,100);
			g2d.drawString("Settings",350,130);
			g2d.drawString("About",370,160);			
			g2d.drawString("Exit",380,220);
			break;
		}
		case 5:{
			g2d.setColor(Color.RED);
			g2d.drawString("Exit",380,220);
			g2d.setColor(Color.WHITE);
			g2d.drawString("Play",380,100);
			g2d.drawString("Settings",350,130);
			g2d.drawString("About",370,160);
			g2d.drawString("Help",380,190);
			break;
		}
		}
		
		
	}
	
	class AL2 extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int key=e.getKeyCode();				
			if(key==KeyEvent.VK_ENTER && option==1){  		//New Game
				Menu.SD.silence();
				if(Sound.KEY){
					Level1.SD.play();

					
				}
				Level1.time1.start();
				Level1.MD=true;
				Level2.MD=true;
				Level3.MD=true;
				Level1.levelComplete=false;
				Level2.levelComplete=false;
				Level3.levelComplete=false;
				Level1.initLevel1=false;
				Level2.initLevel2=false;
				Level3.initLevel3=false;
				MainClass.t1=System.currentTimeMillis();
				MainClass.score=0;
				MainClass.score2=0;
				MainClass.score3=0;
				
				MainClass.t1=System.currentTimeMillis();
				MainClass.MENU.setVisible(false);		
				MainClass.MENU.dispose();				
				MainClass.BOARD_2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainClass.BOARD_2.setSize(800,350);
				MainClass.BOARD_2.setResizable(false);
				MainClass.BOARD_2.setVisible(true);
				MainClass.BOARD_2.setLocationRelativeTo(null);
				MainClass.BOARD_2.enable();
				
			}

			else if(key==KeyEvent.VK_ENTER && option==2){  	//Settings
				MainClass.MENU.setVisible(false);		
				MainClass.MENU.dispose();
				
				MainClass.SETTINGS.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainClass.SETTINGS.setSize(800,350);
				MainClass.SETTINGS.setResizable(false);
				MainClass.SETTINGS.setVisible(true);
				MainClass.SETTINGS.setLocationRelativeTo(null);
			}
			else if(key==KeyEvent.VK_ENTER && option==3){	//About
				MainClass.MENU.setVisible(false);		
				MainClass.MENU.dispose();
				
				MainClass.ABOUT.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainClass.ABOUT.setSize(800,350);
				MainClass.ABOUT.setResizable(false);
				MainClass.ABOUT.setVisible(true);
				MainClass.ABOUT.setLocationRelativeTo(null);
			}
			else if(key==KeyEvent.VK_ENTER && option==4){	//Help
				MainClass.MENU.setVisible(false);		
				MainClass.MENU.dispose();
				
				MainClass.HELP.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				MainClass.HELP.setSize(800,350);
				MainClass.HELP.setResizable(false);
				MainClass.HELP.setVisible(true);
				MainClass.HELP.setLocationRelativeTo(null);
			}
			else if(key==KeyEvent.VK_ENTER && option==5){	//Exit
				System.exit(0);
			}
		}
		public void keyReleased(KeyEvent e){
			int key=e.getKeyCode();
			
			if(key==KeyEvent.VK_UP){
				if(Sound.KEY)
					SD_U.play();
				option--;
			}
			if(key==KeyEvent.VK_DOWN){
				if(Sound.KEY)
					SD_D.play();
				option++;
			}
			if(key==KeyEvent.VK_ESCAPE){
				System.exit(0);
			}	
		}
	}
	
}