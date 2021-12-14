package Tetris;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.Arrays;
import javax.swing.JFrame;


//1. Game play area repaint
//a. Draw a filled rectangle somewhere in the game play area

//c. When running modifies the game play area in some way (draw shapes, change colors,
//etc.) It must change the game play area with each event (tick)
//4. Next piece display repaint
//a. Draw a filled rectangle that changes color each time the timer listener is called
//Your running GUI may look like this when the timer is runni
public class Tetris {
    static int nextPiece;
    static boolean gameStarting = true;
    static String[][] databaseOfPieces;
    static tetrisPiece steve;
    static boolean neverCallAGAIN = false;
    static int trueScore = 0;
    static String playerName;
    static rankedScore[] china;

    public static void main(String[] args) throws InterruptedException, IOException, LineUnavailableException, UnsupportedAudioFileException {
        String csvFile = "PiecesDatabase.csv";
        databaseOfPieces = CSVReaderTest.read(csvFile);
        int numberOfSongs = 2;
        Background_Music music = new Background_Music("Music.wav");
/*        Background_Music music2 = new Background_Music("Tetris Towers.wav");
        Background_Music[] musicArr = new Background_Music[numberOfSongs];
        musicArr[0] = music;
        musicArr[1] = music2;*/
//        music.run();
     //   Playlist gameMix = new Playlist(musicArr);
       // gameMix.run();
 //       int randomNumber = (int) ((Math.random() * 3)%2);
 //       if (randomNumber == 1){
 //           music.run();
//
 //       }else {
            music.run();

 //       }

        /*MyCanvas m = new MyCanvas();

        JFrame f = new JFrame();
        JFrame HowToPlay = new JFrame();
        f.setLayout(new BorderLayout(10, 10));
         f.add(m, BorderLayout.CENTER);
        f.setSize(1600, 1600);
        f.setTitle("Ben's Blocks");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
        Thread.sleep(5000);
        f.setVisible(false);

        HowToPlay.setSize(100, 100);

        //Ability to add html found in forum
        //Html tag info found at https://www.elated.com/first-10-html-tags/#:~:text=An%20HTML%20tag%20is%20a,closing%20tag.
        JLabel text = new JLabel("<html>"+"<h1> <title>BENS BLOCKS</title></h1>"+"" +
                "<h2>"+ "How To Play:" +  "</h2>" + "<p1>" + "STACK THOSE BLOCKS. "+
                "The More Blocks you Stack in a complete horizontal line the Happier Ben is." +
                "When Ben's Happy, your Happy " +
                "When your Happy, you've won at life. " +
                "Stack those Blocks - Win at Life"+"</p1>" +
                "<h2>"+ "Controls:" + "</h2>"+ "<p2>" +"a. Move falling piece left by the left arrow key(or the A Key) " +
                "b. Move falling piece right by the right arrow key(or D Key) " +
                "c. Rotate falling piece 90° by the up arrow key(or the W key)" +
                "d. Drop falling piece one space by the down arrow key( or the S key)"+
                "</p2>" +"</body>"+"</html>");
        //Info found: https://www.tutorialspoint.com/how-to-add-icon-to-jbutton-in-java#:~:text=To%20add%20icon%20to%20a,an%20image%20to%20the%20button.&text=Icon%20icon%20%3D%20new%20ImageIcon(%22,set%20icon%20for%20button%207.
        Icon icon = new ImageIcon("Start Button.jpg");
        JButton startButton = new JButton(icon);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HowToPlay.setVisible(false);
                try {
                    new GraphicsSwing();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        HowToPlay.setLayout(new BorderLayout(1,1));
        startButton.setSize(100, 100);
        HowToPlay.add(text, BorderLayout.NORTH);
        HowToPlay.add(startButton);
        HowToPlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HowToPlay.setSize(500,500);
        HowToPlay.setVisible(true);*/


    }
    public static class HighScores extends JFrame {
        private final int WIDTH = 350;
        private final int HEIGHT = 540;
        JLabel text = new JLabel("High Scores!!!");
        Timer animationTimer;
        int counter = 0;
        JLabel[] highWinners = new JLabel[5];

        public HighScores() throws IOException {

            super();
            File scoreList = new File("TopScores.csv");
            FileReader fr = new FileReader(scoreList);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            String[][] scores = new String[5][2];
            String[] singleArr = new String[2];
            final String deliminator = ",";
            int count = 0;
            while ((line = br.readLine()) != null) {
                singleArr = line.split(deliminator);
                scores[count] = singleArr;
                count++;
            }
            for(int i = 0; i < 5; i++){
                highWinners[i] = new JLabel((i+1) + " : " + scores[i][0] + "--->" + scores[i][1]);
            }
            animationTimer = new Timer(1000,
                    // -- ActionListener for the timer event
                    //    an example of Real-Time Programming
                    //    events occur at arbitrary times and our program
                    //    must be prepared to deal with them
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            if(counter < 10) {
                                counter++;
                            }else if(counter==10){
                                setVisible(false);
                                gameOver.main("");
                                animationTimer.stop();
                                dispose();
                            }else{

                            }

                        }
                    }
            );
            this.setResizable(false);
            // -- set the application title
            this.setTitle("High Scores");
            this.setLayout(new FlowLayout());

            // -- initial size of the frame: width, height
            this.setSize(WIDTH, HEIGHT);

            // -- center the frame on the screen
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(text);
            for(int i = 0; i < 5; i++){
                add(highWinners[i]);
            }
            setVisible(true);
            animationTimer.start();
        }
        //

    }
    public static class NewHighScore extends JFrame {
        private final int WIDTH = 350;
        private final int HEIGHT = 540;
        JLabel text = new JLabel("New High Score Congratulations!!!");
        JLabel text2 = new JLabel("Enter Name");
        JTextField newName = new JTextField("Ben ");
        JButton enterbutton = new JButton("Enter");




        NewHighScore() {
            super();


            newName.setEditable(true);


            this.setResizable(true);
            // -- set the application title
            this.setTitle("New High Score");


            // -- initial size of the frame: width, height
            this.setSize(WIDTH, HEIGHT);
            setLayout(new FlowLayout());

            // -- center the frame on the screen
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.add(text);
            this.add(text2);
            this.add(newName);
            this.add(enterbutton);
            setVisible(true);
            this.requestFocus();

            enterbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    playerName = newName.getText();
                    try {
                        endOfLife(playerName);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                }

                private void endOfLife(String playerName) throws IOException {
                    setVisible(false);
                    dispose();
                    int i;
                    boolean fifthPlaceLoser = true;
                    rankedScore newHigh = new rankedScore(playerName,trueScore);
                    for (i = 0; i  < 4; i++){
                        if (china[i].getScore() > newHigh.getScore()){
                            continue;
                        }else{
                            rightAdjust(china , newHigh,i);
                            fifthPlaceLoser = false;
                            break;
                        }
                    }
                    if(fifthPlaceLoser){
                        china[4].setPlayer(newHigh.getPlayer());
                        china[4].setScore(newHigh.getScore());
                    }
                    sortData(china);
                    FileWriter myWriter = new FileWriter("TopScores.csv");
                    for(i = 0; i  <= 4; i++) {
                        myWriter.write(china[i].getPlayer() + "," + china[i].getScore() +"\n");
                    }
                    myWriter.close();
                    showHighScores();

                }



                private void rightAdjust(rankedScore[] china, rankedScore newHigh, int i) {
                    if(i == 4){
                        china[i].setScore(newHigh.getScore());
                        china[i].setPlayer(newHigh.getPlayer());
                    }else {
                        rankedScore tempScore = new rankedScore(china[i].player, china[i].score);
                        china[i].setPlayer(newHigh.getPlayer());
                        china[i].setScore(newHigh.getScore());
                        rightAdjust(china, tempScore, i + 1);
                    }
                }
            });




        }
        private void sortData(rankedScore[] thailand) {


            /*for(int a = 4; a > 0; a-- ){
                for(int i = 5; i > 0; i--) {
                    if (china[i].getScore() < china[a].getScore()) {
                        rankedScore temp = china[i];
                        china[i] = china[a];
                        china[a] = temp;
                    }
                }}*/
            Integer[] scores = new Integer[thailand.length];
            for (int i = 0; i < thailand.length; i++) {
                scores[i] = thailand[i].getScore();
            }
            Arrays.sort(scores, Collections.reverseOrder());
            rankedScore[] taiwan = new rankedScore[5];
            for (int b = 0; b < taiwan.length; b++) {
                taiwan[b] = new rankedScore();
            }



            // Search for matching score and add in arry


            for (int i = 0; i < scores.length; i++) {
                for (int b = 0; b < thailand.length; b++) {
                    if (scores[i].equals(thailand[b].getScore())) {
                        taiwan[i] = thailand[b];
                    }

                }
            }
            china = taiwan;
        }


    }
    public static class GraphicsSwing extends JFrame {
        int nextPiece;
        boolean gameStarting = true;
        String[][] databaseOfPieces;
        tetrisPiece steve;
        boolean neverCallAGAIN = false;
        int trueScore = 0;
        String playerName;
        rankedScore[] china;
   //     databaseOfPieces = CSVReaderTest.read("PiecesDatabase.csv")
        int numberOfSongs = 2;
        Background_Music music = new Background_Music("Music.wav");
        GameMatrix gMatrix = new GameMatrix();


        private static final long serialVersionUID = 1L;

        // -- set the size of the JFrame. JPanels will adapt to this size
        private final int WIDTH = 350;
        private final int HEIGHT = 540;

        private Timer animationTimer = null;

        private GraphicPanelInner graphicsPanel;
        private ControlPanelInner controlPanel;


        public GraphicsSwing () throws IOException {

            // -- construct the base JFrame first
            super();
            this.setResizable(false);
            // -- set the application title
            this.setTitle("Ben's Blocks");

            // -- initial size of the frame: width, height
            this.setSize(WIDTH, HEIGHT);

            // -- center the frame on the screen
            this.setLocationRelativeTo(null);

            // -- shut down the entire application when the frame is closed
            //    if you don't include this the application will continue to
            //    run in the background and you'll have to kill it by pressing
            //    the red square in eclipse
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // -- set the layout manager and add items
            //    5, 5 is the border around the edges of the areas
            setLayout(new BorderLayout(5, 5));

            // -- construct a JPanel for graphics
            graphicsPanel = new GraphicPanelInner();
            this.add(graphicsPanel, BorderLayout.CENTER);

            // -- construct a JPanel for controls
            controlPanel = new ControlPanelInner();
            this.add(controlPanel, BorderLayout.WEST);

            // -- Timer will generate an event every 10mSec once it is started
            //    First parameter is the delay in mSec, second is the ActionListener
            //    that will handle the timer events
            animationTimer = new Timer(500,
                    // -- ActionListener for the timer event
                    //    an example of Real-Time Programming
                    //    events occur at arbitrary times and our program
                    //    must be prepared to deal with them
                    new ActionListener() {
                        public void actionPerformed(ActionEvent arg0) {
                            try {
                                dropPiece();
                            } catch (InterruptedException | IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
            );

            // -- paint the graphics canvas before the initial display
            graphicsPanel.repaint();

            // -- show the frame on the screen
            this.setVisible(true);

            /*try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(false);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.setVisible(true);*/

            // -- set keyboard focus to the graphics panel
            graphicsPanel.setFocusable(true);
            graphicsPanel.requestFocus();

        }

        private void endGame() throws InterruptedException, IOException {
            if(!neverCallAGAIN){
                neverCallAGAIN = true;
                this.dispose();
                animationTimer.stop();
                this.setVisible(false);
                File scoreList = new File("TopScores.csv");
                FileReader fr = new FileReader(scoreList);
                BufferedReader br = new BufferedReader(fr);
                String line = "";
                String[][] scores = new String[5][2];
                String[] singleArr = new String[2];
                final String deliminator = ",";
                int count = 0;
                while ((line = br.readLine()) != null) {
                    singleArr = line.split(deliminator);
                    scores[count] = singleArr;
                    count++;
                }
                count = 0;
                   rankedScore[] rankedArr = new rankedScore[5];
                for(int i = 0; i < 5; i++){
                    rankedArr[i] = new rankedScore();
                }
                int[] justScores = new int[5];
                boolean dataSorted = true;
                try{
                for(int i = 0; i < 5; i++){
                    justScores[i] = Integer.parseInt(scores[i][1]);
                }
                }catch (NumberFormatException e){
                    for(int i = 0; i < 5; i++){
                        if(scores[i][1] == null){
                            scores[i][1] = String.valueOf(0);
                        }
                    }
                    for(int i = 0; i < 5; i++){
                        if(scores[i][0] == null){
                            scores[i][0] = String.valueOf(0);
                        }
                    }
                }
                /*for(int i = 0 ; i < 4; i++){
                    if(justScores[i] > justScores[i+1]){
                        continue;
                    }else{
                        dataSorted =false;
                        break;
                    }
                }*/
                //Incorrect
                if(Integer.parseInt(scores[4][1]) < trueScore){
                    for (int i = 1; i<=8; i++){
                        if((i % 2)==1){
                             rankedArr[count].setPlayer(scores[count][0]);
                        }else {
                            rankedArr[count].setScore(Integer.parseInt(scores[count][1]));
                            count++;
                        }

                    }
                    sortData(rankedArr);
                    newHighScoreEnd(rankedArr);
                }else {
                    showHighScores();
                }

            }










        }


            private void sortData (rankedScore[] thailand){
                Integer[] scores = new Integer[thailand.length];
                for (int i = 0; i < thailand.length; i++) {
                    scores[i] = thailand[i].getScore();
                }
                Arrays.sort(scores, Collections.reverseOrder());
                rankedScore[] taiwan = new rankedScore[5];
                for (int b = 0; b < taiwan.length; b++) {
                    taiwan[b] = new rankedScore();
                }



                // Search for matching score and add in arry


                for (int i = 0; i < scores.length; i++) {
                    for (int b = 0; b < thailand.length; b++) {
                        if (scores[i].equals(thailand[b].getScore())) {
                            taiwan[i] = thailand[b];
                        }

                    }
                }
                china = taiwan;
            }



        // varring Speed, Varrying pieces , levels, Improved ways to score
// Se - Games new Plan, Books, Justins B day
        public void newHighScoreEnd(rankedScore[] scores) throws InterruptedException, IOException {

            //Create Ranked List to be written
            // Get player name in Frame and give a random one if they don't give one.
            //Re Rank and Bump Off Bottom Score
            china = scores;
            new NewHighScore();

        }


        public void dropPiece() throws InterruptedException, IOException {
            gMatrix.deletePiece(steve);
            if(steve.dropPieceTransformation(gMatrix)){
                gMatrix.appendPieceOnGameBoard(steve);
                controlPanel.repaint();
                while (true){
                if(checkForFullLine(gMatrix)){
                    controlPanel.scoreField.setEditable(true);
                    controlPanel.scoreField.setText(Integer.toString(Integer.parseInt(controlPanel.scoreField.getText())+5));
                    trueScore = trueScore + 5;
                    controlPanel.scoreField.setEditable(false);
                }else {
                    break;
                }}
                gameStart();
            }else{
                gMatrix.appendPiece(steve);
                graphicsPanel.repaint();
            }


        }
//13. Implement the algorithm to detect and remove a completed row
//a. Loop though the game board counting the number of true values in a row
//b. If the row is complete, set the row values to false and move all cells above down
//one row
//c. Increment the score counter
        public boolean checkForFullLine(GameMatrix gameMatrix) {
            for(int a = 0; a< (gameMatrix.getheight()); a++){
            for(int i = 0; i< gameMatrix.getWidth(); i++ ){
               if(gameMatrix.gMatrix[i][a].isPainted){
                   if((gameMatrix.gMatrix[i][a].isPainted)&&(i == 9)) {
                       int rowBeingDeleted = a;
                       for (i = 0; i< gameMatrix.getWidth(); i++){
                           //clear
                           gameMatrix.gMatrix[i][rowBeingDeleted] = new pip();
                       }
                       for( int b = rowBeingDeleted; b >= 0; b--){
                           for( i = (gameMatrix.getWidth()-1); i>=0; i-- ){
                               if(gameMatrix.gMatrix[i][b].isPainted){
                                   gameMatrix.gMatrix[i][b+1] = new pip(gameMatrix.gMatrix[i][b].color);
                                   gameMatrix.gMatrix[i][b] = new pip();

                               }
                           }}
                       return true;
                   }else {

                   }
               }else {
                   break;
               }
            }

        }
            return false;}

        public void dropPieceDefinitive() throws InterruptedException, IOException {
            gMatrix.deletePiece(steve);
            if(steve.dropPieceTransformation(gMatrix)){
                gMatrix.appendPieceOnGameBoard(steve);
                while (true){
                    if(checkForFullLine(gMatrix)){
                        controlPanel.scoreField.setEditable(true);
                        controlPanel.scoreField.setText(Integer.toString(Integer.parseInt(controlPanel.scoreField.getText())+5));
                        trueScore = trueScore + 5;
                        controlPanel.scoreField.setEditable(false);
                    }else {
                        break;
                    }}
                controlPanel.repaint();
                gameStart();
            }else{
                gMatrix.appendPiece(steve);
                graphicsPanel.repaint();
                dropPieceDefinitive();
            }


        }
        public void movePieceToTheLeft() {
            gMatrix.deletePiece(steve);
            if(steve.LeftMovePieceTransformation(gMatrix)){
                gMatrix.appendPiece(steve);
            }else{
                gMatrix.appendPiece(steve);
                graphicsPanel.repaint();
            }
        }
        public void movePieceToTheRight() {
            gMatrix.deletePiece(steve);
            if(steve.RightMovePieceTransformation(gMatrix)){
                gMatrix.appendPiece(steve);
            }else{
                gMatrix.appendPiece(steve);
                graphicsPanel.repaint();
            }
        }
        public void rotatePiece90Degrees() {
            gMatrix.deletePiece(steve);
            if(steve.rotatePiece90DegreesPieceTransformation(gMatrix)){
                gMatrix.appendPiece(steve);
            }else{
                gMatrix.appendPiece(steve);
                graphicsPanel.repaint();
            }
        }


        // -- Inner class for the graphics panel
        public class GraphicPanelInner extends JPanel {

            public GraphicPanelInner ()
            {

                super();


                this.setBackground(Color.WHITE);
                this.prepareActionHandlers();

            }
            /*@Override
            public void repaint(Graphics g)
            {
                // -- the base class paintComponent(g) method ensures
                //    the drawing area will be cleared properly. Do not
                //    modify any attributes of g prior to sending it to
                //    the base class
                super.paintComponent(g);

                // -- for legacy reasons the parameter comes in as type Graphics
                //    but it is really a Graphics2D object. Cast it up since the
                //    Graphics2D class is more capable
                Graphics2D graphicsContext = null;
                if (g instanceof Graphics2D) {
                    graphicsContext = (Graphics2D)g;
                }
                else {
                    System.out.println("you have an old JVM");
                    return;
                }

                int height = this.getHeight();
                int width = this.getWidth();
            }
*/
            // -- prepare the controls and their associated action listeners
            private void prepareActionHandlers()
            {


                // -- keyboard listener
                //    note that the JPanel must have focus for these to
                //    generate events. You can click the mouse in the JPanel
                //    or call graphicsPanel.requestFocus();
                this.addKeyListener(new KeyListener() {


                    @Override
                    public void keyTyped(KeyEvent event) {
                        //Typing is for clicking something that can be printed- Useless here
                    }

                    @Override
                    public void keyPressed(KeyEvent event) {
                        // May edit later to add timer that counts how many long the key is held and moves the piece acordingly.
                        if(animationTimer.isRunning()){
                            int keycode = event.getKeyCode();
                            switch (keycode){
                                case 65:
                                    movePieceToTheLeft();
                                    break;
                                case 37:
                                    movePieceToTheLeft();
                                    break;
                                case 87 :
                                    rotatePiece90Degrees();
                                    break;
                                case 38:
                                    rotatePiece90Degrees();
                                    break;
                                case 68:
                                    movePieceToTheRight();
                                    break;
                                case 39:
                                    movePieceToTheRight();
                                    break;
                                case 83 :
                                    try {
                                        dropPiece();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 40:
                                    try {
                                        dropPiece();
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                case 32:
                                    try {
                                        dropPieceDefinitive();
                                    } catch (InterruptedException | IOException e) {
                                        e.printStackTrace();
                                    }
                                    break;
                                default :
                                    //System.out.println("unregisterKeyboardAction-Nothing Happens");
                                    //System.out.println("key pressed: " + event.getKeyCode());
                            }
                            graphicsPanel.repaint();
                        }else {
                            //System.out.println("Nothing Happens Game is Not in Play");
                        }
                    }



                    @Override
                    public void keyReleased(KeyEvent event){
                        //May Edit for press and hold controls.
                    }

                });
            }



            public Dimension getPreferredSize()
            {
                return new Dimension(10, 30);
            }


            // -- this override is where all the painting should be done.
            //    DO NOT call it directly. Rather, call repaint() and let the
            //    event handling system decide when to call it
            //    DO NOT put graphics function calls elsewhere in the code, although
            //    legal, it's bad practice and could destroy the integrity of the
            //    display
            //    This function is used for all "permanent" painting
            @Override
            public void paint(Graphics g)
            {
                // -- the base class paintComponent(g) method ensures
                //    the drawing area will be cleared properly. Do not
                //    modify any attributes of g prior to sending it to
                //    the base class
                super.paint(g);

                // -- for legacy reasons the parameter comes in as type Graphics
                //    but it is really a Graphics2D object. Cast it up since the
                //    Graphics2D class is more capable
                Graphics2D graphicsContext = null;
                if (g instanceof Graphics2D) {
                    graphicsContext = (Graphics2D)g;
                }
                else {
                    System.out.println("you have an old JVM");
                    return;
                }

                int height = this.getHeight();
                int width = this.getWidth();

                /*// -- draw an image of random color on the panel
                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
                for (int i = 0; i < bi.getHeight(); ++i) {
                    for (int j = 0; j < bi.getWidth(); ++j) {
                        int pixel =	((int)(Math.random() * 255) << 16) | ((int)(Math.random() * 255) << 8) | ((int)(Math.random() * 255));
                        bi.setRGB(j, i, pixel);
                    }
                }*/
                //graphicsContext.drawImage(bi, 0, 0, this.getWidth(), this.getHeight(), null);

                // -- overlay a grid to fill the entire space evenly
                // 10 spaces
                int horzs = 10;
                //horzspacing = 35px
                double horzspacing = width / (double)horzs;
                double x0 = 0.0;
                graphicsContext.setColor(Color.BLACK);
                for (int i = 0; i < horzs; ++i) {
                    graphicsContext.drawLine((int)x0, 0, (int)x0, height);
                    x0 += horzspacing;
                }

                int verts = 30;
                double vertspacing = height / (double)verts;
                double y0 = 0.0;
                graphicsContext.setColor(Color.BLACK);
                for (int i = 0; i < verts; ++i) {
                    graphicsContext.drawLine(0, (int)y0, width, (int)y0);
                    y0 += vertspacing;
                }
                //Here it reads data structure and paints what is in it
                for(int x = 0; x < gMatrix.getWidth(); x++){
                    for(int y = 0; y < gMatrix.getheight(); y++){
                        pip pipBeingRead = gMatrix.getPip(x,y);
                        if (pipBeingRead.isPainted){
                            graphicsContext.setColor(pipBeingRead.color);
                            graphicsContext.fillRect((int) (x * horzspacing),(int) (y*vertspacing),(int) horzspacing,(int) vertspacing);
                            graphicsContext.setColor(Color.black);
                            graphicsContext.drawRect((int) (x * horzspacing),(int) (y*vertspacing),(int) horzspacing,(int) vertspacing);
                        }else{

                        }
                    }
                }


               /* // -- draw a red ellipse in the center of the graphics area
                graphicsContext.setColor(Color.RED);
                graphicsContext.fillOval(width / 2 - 25, height / 2 - 25, 50, 50);*/
            }


        }

        // -- Inner class for control panel
        public class ControlPanelInner extends JPanel {

            // -- push buttons
            private JButton GoAndPauseButton;
            private JButton saveButton;
            private JButton loadButton;
            private JLabel scoreLable = new JLabel("Score");
            private JLabel nextBLabel = new JLabel("Next Block");
            // -- field to hold 1 line of text
            private JTextField scoreField;


            public ControlPanelInner () throws IOException {
                // -- set up buttons
                prepareButtonHandlers();

                // -- set the layout manager
                //    this will determine how items are added to the JPanel
                //setLayout(new GridLayout(10, 1, 2, 2));
                setLayout(new FlowLayout());


                // -- construct the JTextField, 5 characters wide
                scoreField = new scoreField();

                // -- add items to the JPanel in order (FlowLayout)
                this.add(GoAndPauseButton);
                this.add(scoreLable);
                this.add(scoreField);
                this.add(nextBLabel);
                this.add(new smallGraphicPanel(80, 80));
                this.add(loadButton);
                this.add(saveButton);



            }

            // -- construct JButtons and add event handlers
            private void prepareButtonHandlers()
            {
                GoAndPauseButton = new JButton("Start Game");
                GoAndPauseButton.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if((animationTimer.isRunning())){
                                    GoAndPauseButton.setText("Continue Game");
                                    animationTimer.stop();
                                    graphicsPanel.requestFocus();
                                }else{
                                    if(gameStarting){
                                    gameStarting = false;
                                        try {
                                            gameStart();
                                        } catch (InterruptedException | IOException interruptedException) {
                                            interruptedException.printStackTrace();
                                        }
                                    }
                                    GoAndPauseButton.setText("Pause Game");
                                    animationTimer.start();
                                    graphicsPanel.requestFocus();
                                }
                            }
                        }
                );
                saveButton = new JButton("Save");
                saveButton.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFileChooser jfc = new JFileChooser();
                                if (jfc.showDialog(null, "Save") == JFileChooser.APPROVE_OPTION) {
                                    System.out.println(jfc.getSelectedFile().getName());
                                }
                                // -- send focus back to the graphicsPanel
                                graphicsPanel.requestFocus();
                            }
                        }
                );

                loadButton = new JButton("Load");
                loadButton.addActionListener(
                        new ActionListener() {
                            public void actionPerformed(ActionEvent arg0) {
                                JFileChooser jfc = new JFileChooser();
                                if (jfc.showDialog(null, "Load") == JFileChooser.APPROVE_OPTION) {
                                    System.out.println(jfc.getSelectedFile().getName());
                                }
                                // -- send focus back to the graphicsPanel
                                graphicsPanel.requestFocus();
                            }
                        }
                );

            }

            // -- sets the size of the JPanel
            public Dimension getPreferredSize()
            {
                return new Dimension(100, 500);
            }

        }

        public void gameStart() throws InterruptedException, IOException {
            steve = new tetrisPiece(databaseOfPieces[nextPiece]);
            if(checkForGameOver(steve,gMatrix)) {
                /*MyCanvas gOver = new MyCanvas("GAMEEND.jpg");
                JFrame gO = new JFrame();
                gO.setLayout(new BorderLayout(10,10));
                gO.add(gOver, BorderLayout.CENTER);
                gO.setSize(1600,1600);
                gO.setTitle("Game Over: Don't Blame the Blocks, Blame your weak mind!");
                gO.setVisible(true);
                gO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);*/

                endGame();
            }else {
                gMatrix.appendPiece(steve);
                controlPanel.repaint();
                graphicsPanel.repaint();
            }

        }




        private boolean checkForGameOver(tetrisPiece steve, GameMatrix gameMatrix) {
            if (Integer.parseInt(steve.dataOfPiece[0]) == 1){
                if(gameMatrix.gMatrix[steve.pipLocation1.getX()][steve.pipLocation1.getY()].isPainted){
                    return true;
                }else {
                    return false;
                }
            }else if(Integer.parseInt(steve.dataOfPiece[0]) == 2){
                if(gameMatrix.gMatrix[steve.pipLocation1.getX()][steve.pipLocation1.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation2.getX()][steve.pipLocation2.getY()].isPainted){
                    return true;
                }else {
                    return false;
                }
            }else if(Integer.parseInt(steve.dataOfPiece[0]) == 3){
                if(gameMatrix.gMatrix[steve.pipLocation1.getX()][steve.pipLocation1.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation2.getX()][steve.pipLocation2.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation3.getX()][steve.pipLocation3.getY()].isPainted){
                    return true;
                }else{
                    return false;
                }
            }else if(Integer.parseInt(steve.dataOfPiece[0]) == 4){
                if(gameMatrix.gMatrix[steve.pipLocation1.getX()][steve.pipLocation1.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation2.getX()][steve.pipLocation2.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation3.getX()][steve.pipLocation3.getY()].isPainted||gameMatrix.gMatrix[steve.pipLocation4.getX()][steve.pipLocation4.getY()].isPainted){
                    return true;
                }else {
                    return false;
                }
            }else {

            }
           return false;
        }

        // -- define a small JPanel to be included in the
        //    control panel. Note that this has its own
        //    paint function that can be used to draw
        //    graphics
        public class smallGraphicPanel extends JPanel {

            private int height, width;
            // csv file of pieces. Next piece will be in charge of knowing the pieces and randonly putting them in play
            String csvFile = "PiecesDatabase.csv";
            int c = 0;
            String[][] databaseOfPieces;

            public smallGraphicPanel (int height, int width) throws IOException {
                this.height = height;
                this.width = width;

                this.setLayout(new FlowLayout());
                this.setBackground(Color.WHITE);
                //Read pieces from database. If one follows CSV format someone could add blocks that are made up of up to 4 blocks
                databaseOfPieces = CSVReaderTest.read(csvFile);
                //count pieces in data base








            }

            public int generateRandomNextPiece(int c) {
                int randomNumber;
                //Generate Random Number from zero to 6
                randomNumber = (int) ((Math.random()*(c-1) % c));
                return randomNumber;

            }

            // -- sets the size of the JPanel
            public Dimension getPreferredSize()
            {
                return new Dimension(this.width, this.height);
            }

            @Override
            public void paint (Graphics g)
            {
                int count = 0;
                super.paintComponent(g);
                int w = this.getWidth();
                int h = this.getHeight();
                for (String[] tempArr : databaseOfPieces) {
                    c++;
                    count++;
                }
                nextPiece = generateRandomNextPiece(count);
                int widspace = w/4;
                int vertspace = h/4;
                int numberOfPips = Integer.parseInt(databaseOfPieces[Tetris.nextPiece][0]);
                Color penColor = new Color(Integer.parseInt(databaseOfPieces[Tetris.nextPiece][1]), Integer.parseInt(databaseOfPieces[Tetris.nextPiece][2]), Integer.parseInt(databaseOfPieces[Tetris.nextPiece][3]));
                int i = 1;
                int index = 4;

                while (i <= numberOfPips){
                    g.setColor(penColor);
                    int firstpipIndex = (Integer.parseInt(databaseOfPieces[Tetris.nextPiece][index]))-4;
                    int secoundpipIndex = ((Integer.parseInt(databaseOfPieces[Tetris.nextPiece][index+1])))-4;
                    int value = 10;
                    int tempindex = (int) (width/(2.9) + value*firstpipIndex);
                    int tempindex2 = (int) (height/1.25 + value*secoundpipIndex);
                    g.fillRect(tempindex, tempindex2, value,value);
                    g.setColor(Color.black);
                    g.drawRect(tempindex, tempindex2, value,value);
                    index += 2;
                    i++;
                }




            }
        }
    }
    static class pip{
        Color color;
        boolean isPainted;
        pip(){
            color = null;
            isPainted = false;
        }
        pip(Color colorEntered){
            color = colorEntered;
            isPainted = true;
        }
        public void turnOffPip(){
            isPainted = false;
            color = null;
        }
        public void turnOnPip(String colorEntered){
            color = Color.getColor(colorEntered);
            isPainted = true;
        }
    }
    static class point{
        int x;
        int y;
        point(int a, int b){
            x = a;
            y = b;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }
    }
    static class CSVReaderTest {
        public static final String delimiter = ",";

        public static String[][] read(String csvFile) throws IOException {
            File file = new File(csvFile);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            //Arbitrary Number
            br.mark(1000);
            int count;
            count = 0;
            while ((line = br.readLine()) != null) {
                count++;
            }
            br.reset();
            String[][] database = new String[count][12];
            String[] tempArr;
            count = 0;
            while ((line = br.readLine()) != null) {

                tempArr = line.split(delimiter);
                database[count] = tempArr;
                count++;
            }
            return database;
        }}
    public static void showHighScores() throws IOException {
        new HighScores();



    }






}



