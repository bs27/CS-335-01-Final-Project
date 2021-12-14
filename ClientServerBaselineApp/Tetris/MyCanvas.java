package Tetris;

import java.awt.*;

public class MyCanvas extends Canvas {
    private String fileName;

    //Class created by editing code from Stack OverFlow
    MyCanvas(){

    }

    public void paint(Graphics g) {

        Toolkit t = Toolkit.getDefaultToolkit();
        Image i = t.getImage("Title Screen.jpg");
        g.drawImage(i, -300,-75,this);

    }


}