package ireena;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class Level3 extends JPanel implements ActionListener,Runnable{
	Ireena p;
	Image bg1,bg2,gr1,grL,grR,grM;
	public static Timer time1;
	Thread animator;
	public static int v=200;
	Image HB1,HB2,HB3,HB4,HB5; //health
	Image HEART1;  //life
	Image star;	
	Enemy2 en,en2;
	Enemy en3;
	String line;
	Star1 st1,st2,st3,st4;
	int kX,kY;
	
	boolean lost =false;
	boolean initComp=false;
	boolean onRepaint=true;
	boolean jump=false;
	public static boolean levelComplete=false;
	public static boolean MD=false;
	public static boolean levelStart=false;
	public static boolean initLevel3 =true;
	static Font font=new Font("French Script MT",Font.PLAIN,28);

	@SuppressWarnings("rawtypes")
	static ArrayList fireBalls;
	ReadFile RF;
	public Level3(){	
		if(initComp==false)
			initComponents();
		setFocusable(true);
		
		time1=new Timer(55, this);
		//time1.start();
		
	}
	
	@SuppressWarnings("rawtypes")
	public void initComponents(){
		initComp=true;
		p=new Ireena();
		addKeyListener(new AL());
		//initializing images
		bg1=new ImageIcon("src/zJava/w1.png").getImage();
		bg2=new ImageIcon("src/zJava/Trees1.png").getImage();
		gr1=new ImageIcon("src/zJava/Grass.png").getImage();
		grL=new ImageIcon("src/zJava/GrassL.png").getImage();
		grR=new ImageIcon("src/zJava/GrassR.png").getImage();
		grM=new ImageIcon("src/zJava/GrassM.png").getImage();
		star=new ImageIcon("src/zJava/star.png").getImage();

		HB1=new ImageIcon("src/zJava/HealthBar/H1.png").getImage();
		HB2=new ImageIcon("src/zJava/HealthBar/H2.png").getImage();
		HB3=new ImageIcon("src/zJava/HealthBar/H3.png").getImage();
		HB4=new ImageIcon("src/zJava/HealthBar/H4.png").getImage();
		HB5=new ImageIcon("src/zJava/HealthBar/H5.png").getImage();
		HEART1=new ImageIcon("src/zJava/heart1.png").getImage();
		
		//Initializing objects
		en=new Enemy2(800,230);
		en2=new Enemy2(1250,230);
		en3=new Enemy(400,230);
		
		st1= new Star1(800,100);
		st2= new Star1(1000,100);
		st3= new Star1(1200,100);
		st4= new Star1(1500,100);
		fireBalls=new ArrayList();
		RF=new ReadFile();
		RF.read();
	}
	public void throwFireBall(){
		FireBall FB = new FireBall(800,100);
		fireBalls.add(FB);
	}
	public void makeDefault(){
		p.lives=Level2.lives;
		p.health=Level2.health;
		p.dx=0;
		p.dy=0;
		p.x=100;
		p.y=200;
		v=200;
		p.left=p.x;
		p.nx=0;
		p.nx2=800;
		en.isAlive=true;
		en2.isAlive=true;
		en3.isAlive=true;
		Ireena.ammo=10;
		
		k1=h1=done1=false;
		jump=false;
	}
	public void halfDead(){		
		p.lives--;
		p.health=5;
		p.dx=0;
		p.dy=0;
		p.x=100;
		p.y=200;
		v=200;
		p.left=p.x;
		p.nx=0;
		p.nx2=800;
		en.isAlive=true;
		en2.isAlive=true;
		en3.isAlive=true;
		Ireena.ammo=10;
		
		k1=h1=done1=false;
		jump=false;
		MainClass.timeDifference=0;
		
	}
	public void actionPerformed(ActionEvent e){
		//kn1=new Knife(p.x+30,p.y+40);
		checkCollisions();
		p.move();
		if(MD){
			makeDefault();
			MD=false;
		}
		
		@SuppressWarnings("rawtypes")
		ArrayList bullets = Ireena.getBullets();
		for(int w=0;w<bullets.size();w++)
		{
			Knife m=(Knife)bullets.get(w);
			if(m.getVisible()==true)
				m.move();
			else
				bullets.remove(w);
		}
		
		for(int w=0;w<fireBalls.size();w++)
		{
			FireBall FB=(FireBall)fireBalls.get(w);
			if(FB.getVisible()){
				FB.move();
			}
			else
				fireBalls.remove(FB);
		}
		
		
		if(en.getX()-p.x<=800)
			en.move();
		if(en2.getX()-p.x<=800)
			en2.move();	
		if(en3.getX()-p.x<=800)
			en3.move();
		if(onRepaint){
			repaint();
		}
		RF.compare(MainClass.score3);
	}


	public void checkCollisions(){
		
		if(!en.isAlive && (en.getX()-p.nx)-p.left<=250+600 && (en.getX()-p.nx)-p.left>=200+600){
			en.isAlive=true;
		}
		if(!en2.isAlive && (en2.getX()-p.nx)-p.left<=250+600&& (en2.getX()-p.nx)-p.left>=200+600){
			en2.isAlive=true;
		}
		if(!en3.isAlive && (en3.getX()-p.nx)-p.left<=250+600&& (en3.getX()-p.nx)-p.left>=200+600){
			en3.isAlive=true;
		}
		
		@SuppressWarnings("rawtypes")
		ArrayList bullets = Ireena.getBullets();
		for(int w=0;w<bullets.size();w++)
		{
			Knife m=(Knife)bullets.get(w);
			if((en.getX()-p.nx)-m.x<=50 && (en.getX()-p.nx)-m.x>=0 && en.getY()-m.y<=50 && en.Alive()){
				en.killEnemy2();
				m.visible=false;
				MainClass.score3+=500;
			}
			else if((en2.getX()-p.nx)-m.x<=50 && (en2.getX()-p.nx)-m.x>=0 && en2.getY()-m.y<=50 && en2.Alive()){
				en2.killEnemy2();
				m.visible=false;
				MainClass.score3+=500;
			}
			else if((en3.getX()-p.nx)-m.x<=50 && (en3.getX()-p.nx)-m.x>=0 && en3.getY()-m.y<=50 && en3.Alive()){
				en3.killEnemy2();
				m.visible=false;
				MainClass.score2+=500;
			}
			for(int w1=0;w1<fireBalls.size();w1++)
			{
				FireBall FB=(FireBall)fireBalls.get(w1);
				
				if((FB.getX()-m.x)<=64 && FB.getX()-m.x>=0 && m.y-FB.getY()<=64 && FB.getVisible()){
					FB.visible=false;
					m.visible=false;
					fireBalls.remove(FB);
					MainClass.score3+=500;
					if(Sound.KEY)
						FireBall.SD_killFireBall.play();
				}
				
			}
			
		}
		
		//Hit FireBall		
		for(int w=0;w<fireBalls.size();w++)
		{
			FireBall FB=(FireBall)fireBalls.get(w);
			if((FB.getX()-p.left)<=64 && FB.getX()-p.left>=0 && v-FB.getY()<=64 && FB.getVisible()){
				p.health--;
				FB.visible=false;
				fireBalls.remove(FB);
				MainClass.score3-=500;
				if(Sound.KEY)
					Ireena.SD_hit.play();
			}
		}
		
		//Hit enemy from left Player-Enemy
		if((en.getX()-p.nx)-p.left<=50 && (en.getX()-p.nx)-p.left>=0 && en.getY()-v<=50 && en.Alive()){
			p.health--;
			en.killEnemy();
			MainClass.score3-=1000;
		}
		//Hit enemy from right Enemy-Player
		else if((en.getX()-p.nx)-p.left>=-50 && (en.getX()-p.nx)-p.left<=0 && en.getY()-v<=50 && en.Alive()){
			p.health--;
			en.killEnemy();
			MainClass.score3-=1000;
		}
		//for 2nd enemy
		else if((en2.getX()-p.nx)-p.left<=50 && (en2.getX()-p.nx)-p.left>=0 && en2.getY()-v<=50 && en2.Alive()){
			p.health--;
			en2.killEnemy();
			MainClass.score3-=1000;

		}
		else if((en2.getX()-p.nx)-p.left>=-50 && (en2.getX()-p.nx)-p.left<=0 && en2.getY()-v<=50 && en2.Alive()){
			p.health--;
			en2.killEnemy();
			MainClass.score3-=1000;
		}
		//for 3rd enemy
		else if((en3.getX()-p.nx)-p.left<=50 && (en3.getX()-p.nx)-p.left>=0 && en3.getY()-v<=50 && en3.Alive()){
			p.health--;
			en3.killEnemy();
			MainClass.score-=500;
		}
		else if((en3.getX()-p.nx)-p.left>=-50 && (en3.getX()-p.nx)-p.left<=0 && en3.getY()-v<=50 && en3.Alive()){
			p.health--;
			en3.killEnemy();
			MainClass.score-=500;
		}		
		
		//star collision
		if(!st1.isAlive && (st1.x-p.nx)-p.left<=250+600 && (st1.x-p.nx)-p.left>=200+600){
			st1.isAlive=true;
		}
		if(!st2.isAlive && (st2.x-p.nx)-p.left<=250+600&& (st2.x-p.nx)-p.left>=200+600){
			st2.isAlive=true;
		}
		if(!st3.isAlive && (st3.x-p.nx)-p.left<=250+600&& (st3.x-p.nx)-p.left>=200+600){
			st3.isAlive=true;
		}
		if(!st4.isAlive && (st4.x-p.nx)-p.left<=250+600&& (st4.x-p.nx)-p.left>=200+600){
			st4.isAlive=true;
		}
		
		//for 1st star
		if((st1.x-p.nx)-p.left<=50 && (st1.x-p.nx)-p.left>=0 && st1.y-v>=0 && st1.isAlive){
			st1.killStar();
			MainClass.score3+=1000;
		}

		else if((st1.x-p.nx)-p.left>=-50 && (st1.x-p.nx)-p.left<=0 && st1.y-v>=0 && st1.isAlive){
			st1.killStar();
			MainClass.score3+=1000;
		}
		//for 2nd star
		else if((st2.x-p.nx)-p.left<=50 && (st2.x-p.nx)-p.left>=0 && st2.y-v>=0 && st2.isAlive){
			st2.killStar();
			MainClass.score3+=1000;
		}
		else if((st2.x-p.nx)-p.left>=-50 && (st2.x-p.nx)-p.left<=0 && st2.y-v>=0 && st2.isAlive){
			st2.killStar();
			MainClass.score3+=1000;
		}
		//for 3rd star
		else if((st3.x-p.nx)-p.left<=50 && (st3.x-p.nx)-p.left>=0 && st3.y-v>=0 && st3.isAlive){
			st3.killStar();
			MainClass.score3+=1000;
		}
		else if((st3.x-p.nx)-p.left>=-50 && (st3.x-p.nx)-p.left<=0 && st3.y-v>=0 && st3.isAlive){
			st3.killStar();
			MainClass.score3+=1000;
		}
		//for 4th star
		else if((st4.x-p.nx)-p.left<=50 && (st4.x-p.nx)-p.left>=0 && st4.y-v>=0 && st4.isAlive){
			st4.killStar();
			MainClass.score3+=1000;
		}
		else if((st4.x-p.nx)-p.left>=-50 && (st4.x-p.nx)-p.left<=0 && st4.y-v>=0 && st4.isAlive){
			st4.killStar();
			MainClass.score3+=1000;
		}
		
		
	}
	boolean k1=false;
	Graphics2D g2d;
	public void paint(Graphics g){
		if(p.dy==1&&k1==false){
			animator=new Thread(this);
			animator.start();
			k1=true;
			jump=true;
		}
		

		super.paint(g);
		g2d=(Graphics2D)g;
		//Graphics2D g2d=(Graphics2D)g;
		
		
		int l,j;
		//int grass_flag[]={2,1,1,3,0,2,3,0,0,2,1,1,3,0,2,0};
		int grass_flag[]={1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

		if((p.getX()-100)%1600==0)
			p.nx=0;
		if((p.getX()-900)%1600==0)
			p.nx2=0;
		
		g2d.drawImage(bg1, 0, -25, null);
		g2d.drawImage(bg2, 800-p.nx2, -20, null);
		if(p.getX()>=100)
			g2d.drawImage(bg2, 800-p.nx, -20, null);
		//g2d.drawImage(p.getImage(),p.getX(),p.getY(), null);
		
		
		l=800-p.nx2;
		for(j=0;j<16;j++){
			if(grass_flag[j]==1)
				g2d.drawImage(gr1, l, 250,null);
			else if(grass_flag[j]==2)
				g2d.drawImage(grL, l, 250,null);
			else if(grass_flag[j]==3)
				g2d.drawImage(grR, l, 250,null);
			else if(grass_flag[j]==4)
				g2d.drawImage(grM, l, 250,null);
			l+=50;
		}
		if(p.getX()>=100){
			l=800-p.nx;

			for(j=0;j<16;j++){
				if(grass_flag[j]==1)
					g2d.drawImage(gr1, l, 250,null);
				else if(grass_flag[j]==2)
					g2d.drawImage(grL, l, 250,null);
				else if(grass_flag[j]==3)
					g2d.drawImage(grR, l, 250,null);
				else if(grass_flag[j]==4)
					g2d.drawImage(grM, l, 250,null);
				l+=50;
			}
		}
		
		
		if(p.left<100&&grass_flag[(((p.getX()+10)-(100-p.left))%800)/50]==0 && v>=200){
			halfDead();
		}
		
		else if(grass_flag[((p.getX()+30)%800)/50]==0 && v>=200){
			halfDead();
		}
		g2d.drawImage(p.getImage(),p.left,v, null);
		kX=p.left;
		kY=v;
				
		g2d.setColor(Color.WHITE);
		g2d.setFont(font);
		g2d.drawString("Created By Haunted", 500, 20);
		g2d.drawString("Level 03", 500, 50);
		printHealth();
		//g2d.drawString("Health: "+ p.health, 500, 30);
		
		
		//printing enemies
		if(en.getX()-p.getX()<=800)
			if(en.Alive()==true){
				g2d.drawImage(en.getImage(),p.dx+en.getX()-p.nx,en.getY(),null);
				//System.out.println(en.getX());
			}		
		if(en2.getX()-p.getX()<=500)
			if(en2.Alive()==true)
				g2d.drawImage(en2.getImage(),en2.getX()-p.nx,en2.getY(),null);
		if(en3.getX()-p.getX()<=800)
			if(en3.Alive()==true)
				g2d.drawImage(en3.getImage(),en3.getX()-p.nx,en3.getY(),null);
		
		//printing star		
		if(st1.x-p.getX()<=800)
			if(st1.isAlive==true){
				g2d.drawImage(st1.getImage(),p.dx+st1.x-p.nx,st1.y,null);
				//System.out.println(en.getX());
			}		
		if(st2.x-p.getX()<=800)
			if(st2.isAlive==true)
				g2d.drawImage(st2.getImage(),st2.x-p.nx,st2.y,null);
		if(st3.x-p.getX()<=800)
			if(st3.isAlive==true)
				g2d.drawImage(st3.getImage(),st3.x-p.nx,st3.y,null);
		if(st4.x-p.getX()<=800)
			if(st4.isAlive==true)
				g2d.drawImage(st4.getImage(),st4.x-p.nx,st4.y,null);
		//g2d.drawImage(star,500,100,null);
		

		
		@SuppressWarnings("rawtypes")
		ArrayList knives = Ireena.getBullets();  //printing knives
		for(int w=0;w<knives.size();w++)
		{
			Knife m=(Knife)knives.get(w);
			g2d.drawImage(m.getImage(),m.getX(),m.getY(),null);
			
			
		}
		
		g2d.setFont(font);
		g2d.setColor(Color.BLUE);

		
		MainClass.t3=System.currentTimeMillis();
		//MainClass.timeDifference=((MainClass.t3-MainClass.t1)/1000);
		
		if(MainClass.score3<0)
			MainClass.score3=0;
		line=String.valueOf(MainClass.score3);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Score      " + line, 615, 50);
		g2d.drawString("Knife Left: "+ Ireena.ammo,330,20);
		g2d.drawString("High Score               " + ReadFile.HS, 500, 80);
		
		//printing fireballs
		for(int w=0;w<fireBalls.size();w++)
		{
			FireBall FB=(FireBall)fireBalls.get(w);
			g2d.drawImage(FB.getImage(),FB.getX(),FB.getY(),null);
		}
		
		//Throwing fireBalls
		if((MainClass.t3-MainClass.t1)/1000-MainClass.timeDifference>=2){
			MainClass.timeDifference=((MainClass.t3-MainClass.t1)/1000);			
			//System.out.println(MainClass.timeDifference);
			throwFireBall();
		}
		
		//Game Over conditions
		if(p.lives==0){
			MainClass.BOARD_3.setVisible(false);		
			MainClass.BOARD_3.dispose();  
			MainClass.MENU.setVisible(true);
			if(Sound.KEY){
				Level1.SD.silence();
				Menu.SD.play();
			}
			Level3.time1.stop();
		}
		//End of level 3
		int endX=9600;
		if(p.x>=endX-900){
			g2d.drawImage(star,900-p.nx2,200,null);
			if(p.x>=endX+100){
				
				if(MD){
					makeDefault();
					MD=false;
				}
				endLevel();			
			}			
		}
		//g2d.drawImage(kn1.getImage(),kn1.x,kn1.y,null);
		
		
	}
	public void endLevel(){
		levelComplete=true;
		if(levelComplete==true){
			Level1.levelComplete=true;
			Level2.levelComplete=true;
			Level3.levelComplete=true;
			Level1.initLevel1=false;
			Level2.initLevel2=false;
			Level3.initLevel3=false;
			
			MainClass.BOARD_4.setVisible(false);
			MainClass.MENU.setVisible(true);
			
			if(Sound.KEY){
				Level1.SD.silence();
				Menu.SD.play();
			}
			
			MainClass.BOARD_4.disable();
				
			Level3.time1.stop();
		}
		
	}
	
	private void printHealth() {
		switch(p.health){
		case 1:{
			g2d.drawImage(HB1,10,10,null);	
			break;
		}
		case 2:{
			g2d.drawImage(HB2,10,10,null);
			break;
		}
		case 3:{
			g2d.drawImage(HB3,10,10,null);
			break;
		}
		case 4:{
			g2d.drawImage(HB4,10,10,null);
			break;
		}
		case 5:{
			g2d.drawImage(HB5,10,10,null);
			break;
		}
		default:{
			halfDead();
			break;
		}
		}
		for(int i=p.lives;i>0;i--)
			g2d.drawImage(HEART1,790-i*30,10,null);
		//g2d.drawString("Health: "+ p.health, 500, 30);
		
	}

	private class AL extends KeyAdapter{
		public void keyReleased(KeyEvent e){
			p.keyReleased(e);
			
		}
		public void keyPressed(KeyEvent e){
			p.keyPressed(e);
		}
	}

	@Override
	public void run() {
		if(jump){
			long beforeTime1, timeDiff1, sleep1;
			beforeTime1=System.currentTimeMillis();
			while(done1==false){
				cycle1();
				timeDiff1=System.currentTimeMillis()-beforeTime1;
				sleep1=10-timeDiff1;
				if(sleep1<0)
					sleep1=2;
				try{
					Thread.sleep(sleep1);
				}catch(Exception e){}
				beforeTime1=System.currentTimeMillis();
			}
			k1=h1=done1=false;
			jump=false;
		}
		
	}
	boolean h1=false;
	boolean done1 =false;
	public void cycle1(){
		if(h1==false)
			v-=4;		
		if(v==80)
			h1=true;
		if(h1==true && v<=200)
			v+=4;
		if(v==200)
			done1=true;
		p.dy=0;
		
	}



}
