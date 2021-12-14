package Tetris;

import java.awt.*;

public class tetrisPiece{
    //Will NOT work for Pieces with More than 4 Pips
    String[] dataOfPiece;
    int numberOfPips;
    Color colorOfPips;
    Tetris.point pipLocation1;
    Tetris.point pipLocation2;
    Tetris.point pipLocation3;
    Tetris.point pipLocation4;

    tetrisPiece(String[] dataArr){
        dataOfPiece = dataArr;
        numberOfPips = Integer.parseInt(dataArr[0]);
        colorOfPips = new Color(Integer.parseInt(dataArr[1]), Integer.parseInt(dataArr[2]), Integer.parseInt(dataArr[3]));
        if(numberOfPips == 1){
            pipLocation1 = new Tetris.point(Integer.parseInt(dataArr[4]), Integer.parseInt(dataArr[5]));
        }else if(numberOfPips == 2){
            pipLocation1 = new Tetris.point(Integer.parseInt(dataArr[4]), Integer.parseInt(dataArr[5]));
            pipLocation2 = new Tetris.point(Integer.parseInt(dataArr[6]), Integer.parseInt(dataArr[7]));
        }else if(numberOfPips == 3){
            pipLocation1 = new Tetris.point(Integer.parseInt(dataArr[4]), Integer.parseInt(dataArr[5]));
            pipLocation2 = new Tetris.point(Integer.parseInt(dataArr[6]), Integer.parseInt(dataArr[7]));
            pipLocation3 = new Tetris.point(Integer.parseInt(dataArr[8]), Integer.parseInt(dataArr[9]));
        }else if(numberOfPips == 4){
            pipLocation1 = new Tetris.point(Integer.parseInt(dataArr[4]), Integer.parseInt(dataArr[5]));
            pipLocation2 = new Tetris.point(Integer.parseInt(dataArr[6]), Integer.parseInt(dataArr[7]));
            pipLocation3 = new Tetris.point(Integer.parseInt(dataArr[8]), Integer.parseInt(dataArr[9]));
            pipLocation4 = new Tetris.point(Integer.parseInt(dataArr[10]), Integer.parseInt(dataArr[11]));
        }else{

        }}

        public Tetris.point getPipLocation1(){
         return pipLocation1;
        }

    public Color getColorOfPips() {
        return colorOfPips;
    }

    public int getNumberOfPips() {
        return numberOfPips;
    }

    public Tetris.point getPipLocation2() {
        return pipLocation2;
    }

    public Tetris.point getPipLocation3() {
        return pipLocation3;
    }

    public Tetris.point getPipLocation4() {
        return pipLocation4;
    }

    public String[] getDataOfPiece() {
        return dataOfPiece;
    }

    public void setColorOfPips(Color colorOfPips) {
        this.colorOfPips = colorOfPips;
    }

    public void setDataOfPiece(String[] dataOfPiece) {
        this.dataOfPiece = dataOfPiece;
    }

    public void setNumberOfPips(int numberOfPips) {
        this.numberOfPips = numberOfPips;
    }

    public void setPipLocation1(Tetris.point pipLocation1) {
        this.pipLocation1 = pipLocation1;
    }

    public void setPipLocation2(Tetris.point pipLocation2) {
        this.pipLocation2 = pipLocation2;
    }

    public void setPipLocation3(Tetris.point pipLocation3) {
        this.pipLocation3 = pipLocation3;
    }

    public void setPipLocation4(Tetris.point pipLocation4) {
        this.pipLocation4 = pipLocation4;
    }
    public boolean dropPieceTransformation(GameMatrix gameMatrix){
        int newY;
        int newY2;
        int newY3;
        int newY4;
        if(numberOfPips == 1){
            newY = (pipLocation1.getY())+1;
            if ((newY >=30)||(gameMatrix.gMatrix[pipLocation1.getX()][newY]).isPainted){
                return true;
            }else {
                pipLocation1.setY((pipLocation1.getY()+1));
                return false;
            }
        }else if(numberOfPips == 2){
            newY = (pipLocation1.getY())+1;
            newY2 = (pipLocation2.getY())+1;
            if ((newY >=30)||(gameMatrix.gMatrix[pipLocation1.getX()][newY]).isPainted||(newY2 >=30)||(gameMatrix.gMatrix[pipLocation2.getX()][newY2]).isPainted){
                return true;
            }else {
                pipLocation1.setY((pipLocation1.getY()+1));
                pipLocation2.setY((pipLocation2.getY()+1));
                return false;
            }

        }else if(numberOfPips == 3){
            newY = (pipLocation1.getY())+1;
            newY2 = (pipLocation2.getY())+1;
            newY3 = (pipLocation3.getY()+1);
            if ((newY >=30)||(gameMatrix.gMatrix[pipLocation1.getX()][newY]).isPainted||(newY2 >=30)||(gameMatrix.gMatrix[pipLocation2.getX()][newY2]).isPainted||(newY3 >=30)||(gameMatrix.gMatrix[pipLocation3.getX()][newY3]).isPainted){
                return true;
            }else {
                pipLocation1.setY((pipLocation1.getY()+1));
                pipLocation2.setY((pipLocation2.getY()+1));
                pipLocation3.setY((pipLocation3.getY()+1));
                return false;
            }
        }else if(numberOfPips == 4){
            newY = (pipLocation1.getY())+1;
            newY2 = (pipLocation2.getY())+1;
            newY3 = (pipLocation3.getY()+1);
            newY4 = (pipLocation4.getY()+1);
            if ((newY >=30)||(gameMatrix.gMatrix[pipLocation1.getX()][newY]).isPainted||(newY2 >=30)||(gameMatrix.gMatrix[pipLocation2.getX()][newY2]).isPainted||(newY3 >=30)||(gameMatrix.gMatrix[pipLocation3.getX()][newY3]).isPainted||(newY4 >=30)||(gameMatrix.gMatrix[pipLocation4.getX()][newY4]).isPainted){
                return true;
            }else {
                pipLocation1.setY((pipLocation1.getY()+1));
                pipLocation2.setY((pipLocation2.getY()+1));
                pipLocation3.setY((pipLocation3.getY()+1));
                pipLocation4.setY((pipLocation4.getY()+1));
                return false;
            }

        }else{
            System.out.println("Skipped else if and came here!!!");
            return false;

        }

    }

    public boolean LeftMovePieceTransformation(GameMatrix gameMatrix) {
        int newX;
        int newX2;
        int newX3;
        int newX4;
        if(numberOfPips == 1){
            newX = (pipLocation1.getX())-1;
            if ((newX <= -1)||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()-1));
                return false;
            }
        }else if(numberOfPips == 2){
            newX = (pipLocation1.getX())-1;
            newX2 = (pipLocation2.getX())-1;
            if ((newX <= -1) ||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)|| (newX2 <= -1)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()-1));
                pipLocation2.setX((pipLocation2.getX()-1));
                return false;
            }
        }else if(numberOfPips == 3){
            newX = (pipLocation1.getX())-1;
            newX2 = (pipLocation2.getX())-1;
            newX3 = (pipLocation3.getX()-1);
            if ((newX <= -1) || ((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)|| (newX2 <= -1)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)||(newX3 <= -1)|| ((gameMatrix.gMatrix[newX3][pipLocation3.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()-1));
                pipLocation2.setX((pipLocation2.getX()-1));
                pipLocation3.setX((pipLocation3.getX()-1));
                return false;
            }
        }else if(numberOfPips == 4){
                newX = (pipLocation1.getX())-1;
                newX2 = (pipLocation2.getX())-1;
                newX3 = (pipLocation3.getX()-1);
                newX4 = (pipLocation4.getX()-1);
                if ((newX <= -1) || ((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)|| (newX2 <= -1)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)||(newX3 <= -1)|| ((gameMatrix.gMatrix[newX3][pipLocation3.getY()]).isPainted)||(newX4 <= -1)||(gameMatrix.gMatrix[newX4][pipLocation4.getY()]).isPainted){
                    return true;
                }else {
                    pipLocation1.setX((pipLocation1.getX()-1));
                    pipLocation2.setX((pipLocation2.getX()-1));
                    pipLocation3.setX((pipLocation3.getX()-1));
                    pipLocation4.setX((pipLocation4.getX()-1));
                    return false;
            }

        }else{
            System.out.println("Skipped else if and came here!!!");
            return false;

        }
    }

    public boolean RightMovePieceTransformation(GameMatrix gameMatrix) {
        int newX;
        int newX2;
        int newX3;
        int newX4;
        if(numberOfPips == 1){
            newX = (pipLocation1.getX())+1;
            if ((newX >= 10)||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()+1));
                return false;
            }
        }else if(numberOfPips == 2){
            newX = (pipLocation1.getX())+1;
            newX2 = (pipLocation2.getX())+1;
            if ((newX >= 10)||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted)||(newX2 >= 10)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()+1));
                pipLocation2.setX((pipLocation2.getX()+1));
                return false;
            }
        }else if(numberOfPips == 3){
            newX = (pipLocation1.getX())+1;
            newX2 = (pipLocation2.getX())+1;
            newX3 = (pipLocation3.getX()+1);
            if ((newX >= 10)||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted) || (newX2 >= 10)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)||(newX3 >= 10)||((gameMatrix.gMatrix[newX3][pipLocation3.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()+1));
                pipLocation2.setX((pipLocation2.getX()+1));
                pipLocation3.setX((pipLocation3.getX()+1));
                return false;
            }
        }else if(numberOfPips == 4){
            newX = (pipLocation1.getX())+1;
            newX2 = (pipLocation2.getX())+1;
            newX3 = (pipLocation3.getX()+1);
            newX4 = (pipLocation4.getX()+1);
            if ((newX >= 10)||((gameMatrix.gMatrix[newX][pipLocation1.getY()]).isPainted) || (newX2 >= 10)||((gameMatrix.gMatrix[newX2][pipLocation2.getY()]).isPainted)||(newX3 >= 10)||((gameMatrix.gMatrix[newX3][pipLocation3.getY()]).isPainted)||(newX4 >= 10)||((gameMatrix.gMatrix[newX4][pipLocation4.getY()]).isPainted)){
                return true;
            }else {
                pipLocation1.setX((pipLocation1.getX()+1));
                pipLocation2.setX((pipLocation2.getX()+1));
                pipLocation3.setX((pipLocation3.getX()+1));
                pipLocation4.setX((pipLocation4.getX()+1));
                return false;
            }

        }else{
            System.out.println("Skipped else if and came here!!!");
            return false;

        }
    }

    public boolean rotatePiece90DegreesPieceTransformation(GameMatrix gameMatrix) {
        //Thanks 9 Grade Geometry Videos On Youtube!!!
        int newX;
        int newX2;
        int newX3;
        int newX4;
        int newY;
        int newY2;
        int newY3;
        int newY4;
        if (numberOfPips ==1){
            return true;
        }else if (numberOfPips == 2){
            newX2 = (-(pipLocation2.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY2 = (pipLocation2.getX() - pipLocation1.getX())+ pipLocation1.getY();
            if (newY2<= -1){
                return true;
            }
            if ((newX2<=-1)||(newX2>=10)||gameMatrix.gMatrix[newX2][newY2].isPainted||(newY2>=30)){
                return true;
            }else {
                pipLocation2.setX(newX2);
                pipLocation2.setY(newY2);
                return false;
            }

        }else if(numberOfPips == 3){
            newX2 = (-(pipLocation2.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY2 = (pipLocation2.getX() - pipLocation1.getX())+ pipLocation1.getY();
            newX3 = (-(pipLocation3.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY3 = (pipLocation3.getX() - pipLocation1.getX())+ pipLocation1.getY();
            if ((newY2<= -1)||(newY3 <= -1)){
                return true;
            }
            if ((newX2<=-1)||(newX2>=10)||gameMatrix.gMatrix[newX2][newY2].isPainted||(newY2>=30)||(newX3<=-1)||(newX3>=10)||gameMatrix.gMatrix[newX3][newY3].isPainted||(newY3>=30)){
                return true;
            }else {
                pipLocation2.setX(newX2);
                pipLocation2.setY(newY2);
                pipLocation3.setX(newX3);
                pipLocation3.setY(newY3);
                return false;
            }
        }else if (numberOfPips ==4){
            newX2 = (-(pipLocation2.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY2 = (pipLocation2.getX() - pipLocation1.getX())+ pipLocation1.getY();
            newX3 = (-(pipLocation3.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY3 = (pipLocation3.getX() - pipLocation1.getX())+ pipLocation1.getY();
            newX4 = (-(pipLocation4.getY() - pipLocation1.getY()))+pipLocation1.getX();
            newY4 = (pipLocation4.getX() - pipLocation1.getX())+ pipLocation1.getY();
            if ((newY2<= -1)||(newY3 <= -1)||(newY4 <= -1)){
                return true;
            }
            if ((newX2<=-1)||(newX2>=10)||gameMatrix.gMatrix[newX2][newY2].isPainted||(newY2>=30)||(newX3<=-1)||(newX3>=10)||gameMatrix.gMatrix[newX3][newY3].isPainted||(newY3>=30)||(newX4<=-1)||(newX4>=10)||gameMatrix.gMatrix[newX4][newY4].isPainted||(newY4>=30)){
                return true;
            }else {
                pipLocation2.setX(newX2);
                pipLocation2.setY(newY2);
                pipLocation3.setX(newX3);
                pipLocation3.setY(newY3);
                pipLocation4.setX(newX4);
                pipLocation4.setY(newY4);
                return false;
            }
        }else {
            System.out.println("Skipped else if and came here!!!");
            return false;
        }

    }
}