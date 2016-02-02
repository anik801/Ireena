package ireena;

import java.io.*;

import javax.sound.sampled.*;

public class Sound {
	String n;
	Clip clip = null;
	public static boolean KEY=true;
	public Sound(String a){
		n=a;
	}
    public void play() {
        AudioInputStream audio = null;
        try {
            audio = AudioSystem.getAudioInputStream(new File(n));
        } catch(Exception e){
        	System.out.println(e);
        }

        try {
            clip = AudioSystem.getClip();
        }  catch(Exception e){
        	System.out.println(e);
        }

        try {
            clip.open(audio);
        } catch(Exception e){
        	System.out.println(e);
        }
        
        clip.start();
    }
    public void silence(){
    	clip.stop();
    }
}
