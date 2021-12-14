package Tetris;

import javax.swing.*;

public class scoreField extends JTextField {
    public int Score;
    scoreField(){
        super("0",5);
        Score = 0;
        this.setEditable(false);
    }
    public void setScore(int newScore){
        Score = newScore;
        this.setEditable(true);
        this.setText(Integer.toString(newScore));
        this.setEditable(false);
    }
    public void incrementScore(){
        Score++;
        this.setEditable(true);
        this.setText(Integer.toString(Score));
        this.setEditable(false);
    }

    public int getScore() {
        return Score;
    }
}