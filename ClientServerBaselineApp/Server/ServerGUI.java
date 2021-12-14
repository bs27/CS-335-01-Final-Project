package Server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ServerGUI extends Application
{
    int WIDTH = 490;


    private Scene mainScene;

    private Server server;

    // -- Main container
    private BorderPane pane;

    // -- Controls container
    private ControlBoxInner controlBox;

    @Override
    public void start(Stage mainStage)
    {
        // -- Application title
        mainStage.setTitle("Server");
        mainStage.setWidth(WIDTH);


        // -- construct the controls
        controlBox = new ControlBoxInner(this);

        // -- create the primary window structure
        pane = new BorderPane();

        // -- add the graphics canvas and the control box to the Border Pane
        pane.setLeft(controlBox);

        mainScene = new Scene(pane);
        mainStage.setScene(mainScene);

        // -- display the application window
        mainStage.show();

        // -- set keyboard focus to the pane
        pane.requestFocus();

        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {

                Platform.exit();
                System.exit(0);
            }
        });
    }


    // -- launch the application
    public void launchApp(String[] args)
    {
        launch(args);
    }

    public void addToTextArea(String s)
    {
        controlBox.addToTextArea(s);
    }

    // -- Inner class for Controls
    public class ControlBoxInner extends VBox {


        private Button getconnectionsbutton;
        private Button startserverbutton = new Button("Start server");
        private TextField primefield = new TextField();
        private TextArea textarea = new TextArea();
        private ServerGUI owner = null;

        private Button startServer;

        // num output
        private Button numOfUsersBtn;

        // text output
        private Button usersLoggedBtn;
        private Button numOfLoggedBtn;
        private Button numOfConnectedBtn;

        private Button usersLockedOutBtn;

        private Button getNumOfUsersLoggedOutBtn;
        private Button getUsersLoggedOutBtn;

        public ControlBoxInner(ServerGUI gui)
        {
            super();




            this.owner = gui;

            // -- create button handlers

            startServer = new Button("Start Server");

            numOfUsersBtn = new Button("Get Number of Registered Users");
            usersLoggedBtn = new Button("Get users Logged in");

            usersLockedOutBtn = new Button("Get Usernames of Locked Out Users");
            getUsersLoggedOutBtn = new Button("Get Usernames of Logged Out Users");
            getNumOfUsersLoggedOutBtn = new Button("Get Number of Logged Out Users");
            numOfLoggedBtn = new Button("Get Number of Logged In Users");
            numOfConnectedBtn = new Button("Get number of connected clients");
            getconnectionsbutton = new Button("List connected clients");



            startserverbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    server = new Server(owner);
                    server.start();
                    pane.requestFocus();
                    startserverbutton.setDisable(true);
                }
            });

            numOfConnectedBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    System.out.println(server);
                    int p = server.getconnectionsnum();
                    textarea.appendText("Connections : " + p + "");
                    pane.requestFocus();
                }
            });

            numOfUsersBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    int numberOfRegUsers = dbc.numberOfRegisteredUsers();
                    textarea.appendText("Number of registered users : " + numberOfRegUsers + "\n");


                }
            });

            numOfLoggedBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    int numberOfLoggedInUsers = dbc.numberOfLoggedInUsers();
                    textarea.appendText("Number of logged in users : " + numberOfLoggedInUsers+ "\n");
                }
            });

            usersLockedOutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    String[] usersLockedOut = dbc.usersLockedOut();

                    textarea.appendText("Locked Out Users : \n");
                    for (String user:
                         usersLockedOut) {
                        textarea.appendText(user + "\n");
                    }

                }
            });

            usersLoggedBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    String[] usersLoggedIn = dbc.usersLoggedIn();

                    textarea.appendText("Logged In Users : \n");
                    for (String user:
                            usersLoggedIn) {
                        textarea.appendText(user + "\n");
                    }

                }
            });
            getNumOfUsersLoggedOutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    int numberOfLoggedOutUsers = dbc.numberOfLoggedOutUsers();
                    textarea.appendText("Number of logged out users : " + numberOfLoggedOutUsers+ "\n");
                }
            });
            getUsersLoggedOutBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    DBaseConnection dbc = null;
                    if(System.getProperty("user.name").equals("bjsot")){
                        dbc = new DBaseConnection("root","?Vagus39");
                    } else if(System.getProperty("user.name").equals("Kashod Cagnolatti")) {
                        dbc = new DBaseConnection("root", "ravenisdark32!");
                    }
                    String[] usersLockedOut = dbc.getUsersLoggedOut();

                    textarea.appendText("Logged Out Users : \n");
                    for (String user:
                            usersLockedOut) {
                        textarea.appendText(user + "\n");
                    }

                }
            });
            getconnectionsbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int count = 0;
                    while (count < server.getconnectionsnum()){
                        textarea.appendText("ID : " + server.getconnections().get(count).getID() + "\n");
                        textarea.appendText("IP :" + server.getconnections().get(count).getIPAd() + "\n");
                        textarea.appendText("Port :" + server.getconnections().get(count).getPort() + "\n");
                        count++;
                    }

                }
            });




            // -- build a simple GUI
            int maxwidth = 100;
            primefield.setMaxWidth(maxwidth);
            textarea.setMaxWidth(500);
            textarea.setMaxHeight(300);
            this.getChildren().add(startserverbutton);
            this.getChildren().add(getconnectionsbutton);
            this.getChildren().add(numOfUsersBtn);
            this.getChildren().add(usersLockedOutBtn);
            this.getChildren().add(usersLoggedBtn);
            this.getChildren().add(numOfLoggedBtn);
            this.getChildren().add(getNumOfUsersLoggedOutBtn);
            this.getChildren().add(getUsersLoggedOutBtn);
            this.getChildren().add(numOfConnectedBtn);
            this.getChildren().add(new Label("Command Output"));
            this.getChildren().add(textarea);

        }
        public void addToTextArea(String s)
        {
            textarea.appendText("SERVER receive: " + s + "\n");
        }

    }
}