package Tetris;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Playlist extends Thread {
    Background_Music[] musicArray;
    public Playlist(Background_Music[] musicArr) {
        musicArray = new Background_Music[musicArr.length];
        for (int i = 0; i< musicArr.length; i++){
            musicArray[i] = musicArr[i];
        }


    }
    public void run(){

        int randomNumber = (int) ((Math.random() * 3)%2);
        File musicPath = new File(musicArray[randomNumber].filelocation);
        if (musicPath.exists()) {
            try{
            AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
            Clip steve = AudioSystem.getClip();
            steve.open(audioInput);
            steve.start();
            steve.wait(steve.getMicrosecondLength());
            steve.close();
            //clip.loop(Clip.LOOP_CONTINUOUSLY);
                }catch (Exception e){

            }

        }else {
            System.out.println("Can't find File");
        }
        randomNumber = (int) ((Math.random() * 3)%2);
        musicPath = new File(musicArray[randomNumber].filelocation);
        if (musicPath.exists()) {
            try{
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                Clip steve = AudioSystem.getClip();
                steve.open(audioInput);
                steve.start();
                steve.wait(steve.getMicrosecondLength());
                steve.close();
                //clip.loop(Clip.LOOP_CONTINUOUSLY);
            }catch (Exception e){

            }

        }else {
            System.out.println("Can't find File");
        }


        }
    }

