package ireena;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Ireena {
	int x,y,nx,nx2;
	int dx,dy;
	int right_flag=1;
	int left_flag=1;
	boolean up_flag=false;
	boolean LR_flag=true;
	boolean isOver=false;
	
	int health=5;
	int lives = 3;
	int left;
	Level1 ob;
	Image imgR1,imgR2,imgR3,imgR4,imgL1,imgL2,imgL3,imgL4,img;
	public static int ammo=5;

	public static Sound SD_hit;
	Sound SD_knife;
	@SuppressWarnings("rawtypes")
	static ArrayList knives;
	
	@SuppressWarnings("rawtypes")
	public Ireena(){
		//System.out.println(Level1.initLevel1 +" "+ Level2.initLevel2);
		if(Level1.initLevel1){
			imgL1=new ImageIcon("src/zJava/normalWalk_PNG/1l.png").getImage();
			imgL2=new ImageIcon("src/zJava/normalWalk_PNG/2l.png").getImage();
			imgL3=new ImageIcon("src/zJava/normalWalk_PNG/3l.png").getImage();
			imgL4=new ImageIcon("src/zJava/normalWalk_PNG/4l.png").getImage();
			
			imgR1=new ImageIcon("src/zJava/normalWalk_PNG/1r.png").getImage();
			imgR2=new ImageIcon("src/zJava/normalWalk_PNG/2r.png").getImage();
			imgR3=new ImageIcon("src/zJava/normalWalk_PNG/3r.png").getImage();
			imgR4=new ImageIcon("src/zJava/normalWalk_PNG/4r.png").getImage();
		}		
		else if(Level2.initLevel2||Level3.initLevel3){
			imgL1=new ImageIcon("src/zJava/knifeWalk_PNG/1l.png").getImage();
			imgL2=new ImageIcon("src/zJava/knifeWalk_PNG/2l.png").getImage();
			imgL3=new ImageIcon("src/zJava/knifeWalk_PNG/3l.png").getImage();
			imgL4=new ImageIcon("src/zJava/knifeWalk_PNG/4l.png").getImage();
			
			imgR1=new ImageIcon("src/zJava/knifeWalk_PNG/1r.png").getImage();
			imgR2=new ImageIcon("src/zJava/knifeWalk_PNG/2r.png").getImage();
			imgR3=new ImageIcon("src/zJava/knifeWalk_PNG/3r.png").getImage();
			imgR4=new ImageIcon("src/zJava/knifeWalk_PNG/4r.png").getImage();
		}
		
		//System.out.println(Level1.levelComplete +"   "+   Level2.levelComplete);
		img=imgR1;

		x=100;
		y=200;
		nx2=800;
		nx=0;
		
		left=x;
		knives=new ArrayList();		
		SD_hit=new Sound("src/zJava/Sounds/enemy.wav");
		SD_knife=new Sound("src/zJava/Sounds/knife.wav");
		
	}
	public Rectangle getBounds(){
		return new Rectangle(left, y, 51,100);		
	}
	@SuppressWarnings("unchecked")
	public void fire(){
		if(ammo>0){
			Knife z;
			if(!Level2.levelComplete)
				z=new Knife(left+30,Level2.v+30);
			else
				z=new Knife(left+30,Level3.v+30);
			knives.add(z);
			ammo--;
			if(Sound.KEY)
				SD_knife.play();
		}
	}
	@SuppressWarnings("rawtypes")
	public static ArrayList getBullets(){
		return knives;
	}
	public void move(){
		if(dx!=-10){
			if(left+dx<=100){
				left+=dx;
				//x+=dx;
			}
			else{
				x=x+dx;
				//y=y+dy;			
				nx2+=dx;
				nx+=dx;					
			}	
		}
		else
			if(left+dx>0){
				left+=dx;
				//x+=10;
			}
		if(Level1.v>=200)
			up_flag=false;
		else if(Level1.v<200)
			up_flag=true;
	}
	
	public int getX(){
		return x;
	}
	public int getY()
	{
		return y;
	}
	public Image getImage(){
		if(up_flag&&LR_flag){
			right_flag=3;
			img=imgR2;
		}
		else if(up_flag&&!LR_flag){
			left_flag=3;
			img=imgL2;
		}

		else if(dx>0&&right_flag==1){
			right_flag=2;
			img=imgR1;
		}
		else if(dx>0&&right_flag==2){
			right_flag=3;
			img=imgR2;
		}
		else if(dx>0&&right_flag==3){
			right_flag=1;
			img=imgR3;
		}
		
		else if(dx<0&&left_flag==1){
			left_flag=2;
			img=imgL1;
		}
		else if(dx<0&&left_flag==2){
			left_flag=3;
			img=imgL2;
		}
		else if(dx<0&&left_flag==3){
			left_flag=1;
			img=imgL3;
		}
		else if(dx==0&&LR_flag){
			right_flag=1;
			img=imgR1;
		}
		else if(dx==0&&!LR_flag){
			left_flag=1;
			img=imgL1;
		}
		return img;
	}
	public void keyPressed(KeyEvent e){
		
		
		int key=e.getKeyCode();		
		
		if(key==KeyEvent.VK_LEFT){
			dx=-10;
			LR_flag=false;
			
		}
		if(key==KeyEvent.VK_RIGHT){
			dx=10;
			LR_flag=true;
		}
		
		if(key==KeyEvent.VK_UP){
			dy=1;
			up_flag=true;

		}
		
		if(key==KeyEvent.VK_ENTER && isOver){
			//System.exit(0);

		}		
		
	}
	public void keyReleased(KeyEvent e){
		int key=e.getKeyCode();
		
		if(key==KeyEvent.VK_LEFT){
			dx=0;
		}
		if(key==KeyEvent.VK_RIGHT){			
			dx=0;
		}
		if(key==KeyEvent.VK_UP){
			dy=1;
		}
		if(key==KeyEvent.VK_ESCAPE){
			Level1.MD=true;
			//Level2.MD=true;
			
			MainClass.BOARD_2.setVisible(false);
			MainClass.BOARD_3.setVisible(false);
			MainClass.BOARD_4.setVisible(false);	
			
			MainClass.BOARD_2.disable();
			MainClass.BOARD_3.disable();
			MainClass.BOARD_4.disable();
			//MainClass.BOARD_2.remove(new Level2());
			//MainClass.BOARD_3.removeAll();
			//new MainClass();
			MainClass.MENU.setVisible(true);
			
			if(Sound.KEY){
				Level1.SD.silence();
				Menu.SD.play();
			}
			
			Level1.time1.stop();
			Level2.time1.stop();
			Level3.time1.stop();
				
			
		}
		if(key==KeyEvent.VK_SPACE && LR_flag){
			fire();
		}

	}

}