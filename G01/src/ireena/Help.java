package ireena;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Help extends JPanel implements ActionListener{
	Timer t;
	Image bg,img,img1;
	public Help(){
		addKeyListener(new AL2());
		setFocusable(true);
		bg= new ImageIcon("src/zJava/w3.jpg").getImage();
		img= new ImageIcon("src/zJava/help.png").getImage();
		img1= new ImageIcon("src/zJava/Help1.png").getImage();
		t=new Timer(55,this);
		t.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		
	}
	static Font font=new Font("Snap ITC",Font.PLAIN,24);
	Graphics2D g2d;
	public void paint(Graphics g){
		super.paint(g);
		Graphics2D g2d=(Graphics2D)g;
		g2d.drawImage(bg, 0,0,null);
		g2d.drawImage(img, -80,0,null);
		g2d.drawImage(img1, 0,150,null);
		g2d.setFont(font);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Press Enter to go Back",30,300);
		
	}
	
	class AL2 extends KeyAdapter{
		
		public void keyPressed(KeyEvent e){
			int key=e.getKeyCode();		
			if(key==KeyEvent.VK_ENTER){
				MainClass.HELP.setVisible(false);		
				MainClass.HELP.dispose();
				
				MainClass.MENU.setVisible(true);
			}
			
		}
		public void keyReleased(KeyEvent e){		

		}
	}
	
}


