package ireena;
import java.awt.*;

import javax.swing.ImageIcon;
public class Knife {
	int x,y;
	Image img;
	boolean visible;
	
	public Knife(int startX, int startY){
		x=startX;
		y=startY;
		img=new ImageIcon("src/zJava/Knife.gif").getImage();
		visible = true;
		
	}
	public void move(){
		x+=10;
		if(x>800){
			visible=false;
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
		return new Rectangle(x,y,30,30);
	}
}
