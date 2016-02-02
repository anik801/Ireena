package ireena;

import java.awt.*;

import javax.swing.ImageIcon;

public class Star1{
	Image img;
	int x,y;
	boolean isAlive=true;
	
	Sound SD_killStar;

	public Star1(int startX, int startY){
		x=startX;
		y=startY;
		img=new ImageIcon("src/zJava/star1.gif").getImage();
		SD_killStar=new Sound("src/zJava/Sounds/star.wav");
	}
	
	public Image getImage(){
		return img;
	}
	public void killStar(){
		if(Sound.KEY)
			SD_killStar.play();
		isAlive=false;
	}
}