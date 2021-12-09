package Server;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class ServerQueryGUI extends Application {
    int WIDTH = 400;
    int HEIGHT = 650;

    Scene serverGUI;
    private Button startServer;

    // num output
    private ToggleButton numOfUsersBtn;
    private TextField numOfUsers;

    private ToggleButton numOfLoggedBtn;
    private TextField numOfLogged;

    private ToggleButton numOfConnectedBtn;
    private TextField numOfConnected;

    // text output
    private ToggleButton usersLoggedBtn;
    private TextField usersLogged;

    private ToggleButton usersLockedOutBtn;
    private TextField usersLockedOut;


    @Override
    public void start(Stage mainStage) throws Exception
    {
        startServer = new Button("Start Server");

        numOfUsersBtn = new ToggleButton("Start/Stop");
        numOfLoggedBtn = new ToggleButton("Start/Stop");
        numOfConnectedBtn = new ToggleButton("Start/Stop");
        numOfUsers = new TextField("0");
        numOfLogged = new TextField("0");
        numOfConnected = new TextField("0");

        usersLoggedBtn = new ToggleButton("Start/Stop");
        usersLockedOutBtn = new ToggleButton("Start/Stop");
        usersLogged = new TextField("[User info populates here]");
        usersLockedOut = new TextField("[User info populates here]");

        BorderPane root = new BorderPane();

        // set up
        VBox server = new VBox();
        server.getChildren().add(new Label("~Server~"));
        server.getChildren().add(startServer);

        VBox admin = new VBox();
        admin.setSpacing(10);
        admin.getChildren().add(new Label("~Admin Query Panel~"));

        admin.getChildren().add(new Label("Number of Registered Users:"));
        admin.getChildren().add(numOfUsers);
        admin.getChildren().add(numOfUsersBtn);
        admin.getChildren().add(new Label(" ")); // empty space

        admin.getChildren().add(new Label("Number of Logged In Users:"));
        admin.getChildren().add(numOfLogged);
        admin.getChildren().add(numOfLoggedBtn);
        admin.getChildren().add(new Label(" ")); // empty space

        admin.getChildren().add(new Label("Number of Connected Users:"));
        admin.getChildren().add(numOfConnected);
        admin.getChildren().add(numOfConnectedBtn);
        admin.getChildren().add(new Label(" ")); // empty space

        admin.getChildren().add(new Label("Which Users are Logged In:"));
        admin.getChildren().add(usersLogged);
        admin.getChildren().add(usersLoggedBtn);
        admin.getChildren().add(new Label(" ")); // empty space

        admin.getChildren().add(new Label("Which Users are Locked Out:"));
        admin.getChildren().add(usersLockedOut);
        admin.getChildren().add(usersLockedOutBtn);

        root.setRight(admin);
        root.setLeft(server);

        BorderPane.setMargin(root.getRight(), new Insets(10));
        BorderPane.setMargin(root.getLeft(), new Insets(10));

        serverGUI = new Scene(root, WIDTH, HEIGHT);
        mainStage.setScene(serverGUI);
        mainStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}