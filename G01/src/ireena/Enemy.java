package ireena;

import java.awt.*;

import javax.swing.ImageIcon;

public class Enemy implements Runnable{
	Image imgL,imgR,img;
	int x,y;
	int sX,sY;
	boolean isAlive=true;
	Thread animator;
	Sound SD_killEnemy,SD_killEnemy2;

	public Enemy(int startX, int startY){
		x=sX=startX;
		y=sY=startY;
		ImageIcon i=new ImageIcon("src/zJava/Enemy_1L.png");
		imgL=i.getImage();
		i=new ImageIcon("src/zJava/Enemy_1R.png");
		imgR=i.getImage();
		SD_killEnemy=new Sound("src/zJava/Sounds/enemy.wav");
		SD_killEnemy2=new Sound("src/zJava/Sounds/enemy2.wav");
	}
	public Rectangle getBounds(){
		return new Rectangle(x, y, 50,70);		
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean Alive(){
		return isAlive;
	}
	public void killEnemy(){
		isAlive=false;
		if(Sound.KEY)
			SD_killEnemy.play();
	}
	public void killEnemy2(){
		isAlive=false;
		if(Sound.KEY)
			SD_killEnemy2.play();
	}
	
	public Image getImage(){
		if(me==true)
			img=imgR;
		else if(me==false)
			img=imgL;
		return img;
	}
	public void move(){	
		if(k==false){
			animator=new Thread(this);
			animator.start();
			k=true;
		}
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		long beforeTime, timeDiff, sleep;
		beforeTime=System.currentTimeMillis();
		while(done==false){
			cycle();
			timeDiff=System.currentTimeMillis()-beforeTime;
			sleep=10-timeDiff;
			if(sleep<0)
				sleep=2;
			try{
				Thread.sleep(sleep);
			}catch(Exception e){}
			beforeTime=System.currentTimeMillis();
		}
		
	}
	boolean me=true;
	boolean k=false;
	boolean done =false;
	public void cycle(){

		if(me==true && x<=sX+150)
			x++;
		if(x>=sX+150)
			me=false;
		if(me==false && x>=sX)
			x--;
		if(x<=sX)
			me=true;
		
			
		
	}

}
