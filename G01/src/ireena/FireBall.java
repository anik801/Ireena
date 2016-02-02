package ireena;
import java.awt.*;

import javax.swing.ImageIcon;
public class FireBall {
	int x,y;
	Image img;
	boolean visible;
	
	public static Sound SD_killFireBall;
	public FireBall(int startX, int startY){
		x=startX;
		y=startY;
		img=new ImageIcon("src/zJava/FireBall.gif").getImage();
		SD_killFireBall=new Sound("src/zJava/Sounds/enemy2.wav");
		visible = true;
	}
	public void move(){
		if(Level2.levelComplete){
			x-=30;
			if(x<=-100){
				visible=false;
			}
		}
		else{
			x-=20;
			if(x<=-100){
				visible=false;
			}
		}

	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public Image getImage(){
		return img;
	}
	public Boolean getVisible(){
		return visible;
	}
	public Rectangle getBounds(){
		return new Rectangle(x,y,64,64);
	}
}
