package ireena;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Settings extends JPanel implements ActionListener{
	Timer t;
	Image bg,img;
	int option=1;
	public Settings(){
		addKeyListener(new AL2());
		setFocusable(true);
		bg= new ImageIcon("src/zJava/w3.jpg").getImage();
		t=new Timer(55,this);
		t.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	static Font font=new Font("Snap ITC",Font.PLAIN,28);
	Graphics2D g2d;
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(bg, 0,0,null);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString("SOUND",350,150);
		g2d.drawString("Press Enter to go Back",30,300);
		
		if(option==1){
			g2d.drawString("OFF",500,180);
			g2d.setColor(Color.RED);
			g2d.drawString("ON",250,180);
		}
		else if(option==-1){
			g2d.drawString("ON",250,180);
			g2d.setColor(Color.RED);
			g2d.drawString("OFF",500,180);			
		}
				
		
		
		
	}
	
	class AL2 extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int key=e.getKeyCode();		
			if(key==KeyEvent.VK_ENTER && option==1){
				if(!Sound.KEY){
					Menu.SD.play();
				}					
				Sound.KEY=true;
				MainClass.SETTINGS.setVisible(false);		
				MainClass.SETTINGS.dispose();				
				MainClass.MENU.setVisible(true);
			}
			else if(key==KeyEvent.VK_ENTER && option==-1){
				if(Sound.KEY)
					Menu.SD.silence();
				Sound.KEY=false;				
				MainClass.SETTINGS.setVisible(false);		
				MainClass.SETTINGS.dispose();				
				MainClass.MENU.setVisible(true);
			}
			
		}
		public void keyReleased(KeyEvent e){	
			int key=e.getKeyCode();
			if(key==KeyEvent.VK_LEFT){
				option*=-1;
			}
			if(key==KeyEvent.VK_RIGHT){
				option*=-1;
			}
		}
	}
	
}


