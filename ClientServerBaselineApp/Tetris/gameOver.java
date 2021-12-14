package Tetris;

import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Toolkit;
// CODE from Stack OverFlow

public class gameOver extends JFrame
{
    Image imageToBeDraw;
    ImageIcon ii;

    public gameOver()
    {
        //set JFrame title
        super("Game Over");

        //Get image. You must change image location follow to image location in your computer.
        imageToBeDraw= Toolkit.getDefaultToolkit().getImage("C:\\Users\\bjsot\\Desktop\\Tetris\\GAMEEND.jpg");

        //Create an ImageIcon object
        ii=new ImageIcon(imageToBeDraw);

        //set close operation for JFrame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set JFrame size follow the image size
        setSize(ii.getIconWidth(),ii.getIconHeight());

        //make you JFrame cannot resizable
        setResizable(false);
        repaint();

        //make JFrame visible. So we can see it.
        setVisible(true);
    }

    public void paint(Graphics g)
    {
        //This will draw drawImageIntoJFrame.jpg into JFrame
        g.drawImage(imageToBeDraw,0,0,this);
    }

    public static void main(String args)
    {
        gameOver diojf=new gameOver();
    }
}
