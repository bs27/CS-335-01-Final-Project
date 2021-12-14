package Tetris;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class musicOneShot {

    //Found info on this from https://www.youtube.com/watch?v=TErboGLHZGA
        String filelocation;
        musicOneShot(String Filelocation) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
            filelocation = Filelocation;
        }


        public void run() {
            try {
                File musicPath = new File(filelocation);
                if (musicPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip steve = AudioSystem.getClip();
                    steve.open(audioInput);
                    steve.start();
                    steve.loop(Clip.LOOP_CONTINUOUSLY);

                    //     steve.close();

                }else {
                    System.out.println("Can't find File");
                }


            }catch (Exception e) {
                System.out.println(e);
            }
        }





    }
