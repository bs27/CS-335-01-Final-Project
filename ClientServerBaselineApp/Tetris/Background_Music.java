package Tetris;

import javax.sound.sampled.*;
import java.io.File;

//Found info on this from https://www.youtube.com/watch?v=TErboGLHZGA
public class Background_Music extends Thread {
    String filelocation;
    Background_Music(String Filelocation){
        filelocation = Filelocation;
    }
    public void run() {
        try {
            File musicPath = new File(filelocation);
            if (musicPath.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip clip = AudioSystem.getClip();
                clip.open(audioInput);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }else {
                System.out.println("Can't find File");
            }
        }catch (Exception e) {
            System.out.println(e);
        }




    }




}



