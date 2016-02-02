package ireena;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class About extends JPanel implements ActionListener{
	Timer t;
	Image bg,img,bg2;
	public About(){
		addKeyListener(new AL2());
		setFocusable(true);
		bg= new ImageIcon("src/zJava/w3.jpg").getImage();
		bg2= new ImageIcon("src/zJava/about.png").getImage();
		t=new Timer(55,this);
		t.start();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	static Font font=new Font("Snap ITC",Font.PLAIN,20);
	Graphics2D g2d;
	public static int y=350;
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.setColor(Color.WHITE);
		g2d.drawImage(bg, 0,0,null);
		g2d.drawImage(bg2, 0,y,null);
		g2d.setFont(font);
		g2d.drawString("Press Enter to go Back",30,300);
		
		y-=2;
		if(y==-1300)
			y=300;
		
	}
	
	class AL2 extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int key=e.getKeyCode();		
			if(key==KeyEvent.VK_ENTER){
				MainClass.ABOUT.setVisible(false);		
				MainClass.ABOUT.dispose();
				
				MainClass.MENU.setVisible(true);
			}
			
		}
		public void keyReleased(KeyEvent e){		

		}
	}
	
}


