package Tetris;

import java.awt.*;

public class GameMatrix {
    Tetris.pip[][] gMatrix = new Tetris.pip[10][30];

    GameMatrix() {
        for (int x = 0; x < gMatrix.length; x++) {
            for (int y = 0; y < gMatrix[0].length; y++) {
                gMatrix[x][y] = new Tetris.pip();
            }
        }
    }

    public int getWidth() {
        return gMatrix.length;
    }

    public int getheight() {
        return gMatrix[0].length;
    }

    public Tetris.pip getPip(int xIndex, int yIndex) {
        return gMatrix[xIndex][yIndex];

    }

    public void appendPip(int xIndex, int yIndex, Color colorOfPip) {
        gMatrix[xIndex][yIndex].color = colorOfPip;
        gMatrix[xIndex][yIndex].isPainted = true;
    }

    public void clearPip(int xIndex, int yIndex) {
        gMatrix[xIndex][yIndex].isPainted = false;
    }

    public void appendPiece(tetrisPiece pieceToBeAppended) {
        int i = 1;
        while (pieceToBeAppended.getNumberOfPips() >= i) {
            if (i == 1) {
                gMatrix[pieceToBeAppended.getPipLocation1().getX()][pieceToBeAppended.getPipLocation1().getY()] = new Tetris.pip(pieceToBeAppended.getColorOfPips());
                i++;
            } else if (i == 2) {
                gMatrix[pieceToBeAppended.getPipLocation2().getX()][pieceToBeAppended.getPipLocation2().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation2().getX()][pieceToBeAppended.getPipLocation2().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else if (i == 3) {
                gMatrix[pieceToBeAppended.getPipLocation3().getX()][pieceToBeAppended.getPipLocation3().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation3().getX()][pieceToBeAppended.getPipLocation3().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else if (i == 4) {
                gMatrix[pieceToBeAppended.getPipLocation4().getX()][pieceToBeAppended.getPipLocation4().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation4().getX()][pieceToBeAppended.getPipLocation4().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else {

            }

        }

    }

    public void deletePiece(tetrisPiece pieceToBeAppended) {
        int i = 1;
        while (pieceToBeAppended.getNumberOfPips() >= i) {
            if (i == 1) {
                gMatrix[pieceToBeAppended.getPipLocation1().getX()][pieceToBeAppended.getPipLocation1().getY()].isPainted = false;
                i++;
            } else if (i == 2) {
                gMatrix[pieceToBeAppended.getPipLocation2().getX()][pieceToBeAppended.getPipLocation2().getY()].isPainted = false;
                i++;
            } else if (i == 3) {
                gMatrix[pieceToBeAppended.getPipLocation3().getX()][pieceToBeAppended.getPipLocation3().getY()].isPainted = false;
                i++;
            } else if (i == 4) {
                gMatrix[pieceToBeAppended.getPipLocation4().getX()][pieceToBeAppended.getPipLocation4().getY()].isPainted = false;
                i++;
            } else {

            }
        }
    }

    public void appendPieceOnGameBoard(tetrisPiece pieceToBeAppended) {
        int i = 1;
        while (pieceToBeAppended.getNumberOfPips() >= i) {
            if (i == 1) {
                gMatrix[pieceToBeAppended.getPipLocation1().getX()][pieceToBeAppended.getPipLocation1().getY()] = new Tetris.pip(pieceToBeAppended.getColorOfPips());
                i++;
            } else if (i == 2) {
                gMatrix[pieceToBeAppended.getPipLocation2().getX()][pieceToBeAppended.getPipLocation2().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation2().getX()][pieceToBeAppended.getPipLocation2().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else if (i == 3) {
                gMatrix[pieceToBeAppended.getPipLocation3().getX()][pieceToBeAppended.getPipLocation3().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation3().getX()][pieceToBeAppended.getPipLocation3().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else if (i == 4) {
                gMatrix[pieceToBeAppended.getPipLocation4().getX()][pieceToBeAppended.getPipLocation4().getY()].isPainted = true;
                gMatrix[pieceToBeAppended.getPipLocation4().getX()][pieceToBeAppended.getPipLocation4().getY()].color = pieceToBeAppended.getColorOfPips();
                i++;
            } else {

            }
        }
        //check for line clear
    }
}