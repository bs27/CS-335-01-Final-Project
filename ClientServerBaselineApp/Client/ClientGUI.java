package Client;


import com.sun.corba.se.impl.orb.ParserTable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.WindowEvent;

import javax.swing.*;
import java.io.IOException;

public class ClientGUI extends Application
{
    Client client = null;

    int WIDTH = 256;
    int HEIGHT = 256;

    Scene connectGUI;
    Scene loginGUI;

    Button submitButton = new Button("Submit");
    Button forgotPassButton = new Button("Forgot your Password?");
    Button registerButton = new Button("Register");
    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button connectbutton = new Button("Connect");
    private Button disconnect = new Button("Disconnect");

    Label enterIP = new Label("Enter IP Address Below");
    TextField ipAddressField = new TextField("0.0.0.0");
    Label errorIncorrectIPFormat = new Label("This isn't an IP Address DUMBASS!");



    @Override
    public void start(Stage mainStage) {

        System.out.println("start");
        // -- Application title
        mainStage.setTitle("Connection Client");
        mainStage.setWidth(WIDTH);
        connectGUI = new Scene(new VBox(enterIP,ipAddressField,connectbutton,errorIncorrectIPFormat));
        errorIncorrectIPFormat.setStyle("-fx-text-fill: red; -fx-font-size: 16px;");
        errorIncorrectIPFormat.setVisible(false);

        mainStage.setScene(connectGUI);
        // -- display the aplication window
        mainStage.show();




        // -- clean up on close
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                // -- if client is connected to the server, disconnect
                //    before shutting down
                if (client != null) {
                    client.disconnect();
                    client = null;
                }
                Platform.exit();
                System.exit(0);
            }
        });




        // -- add the graphics canvas and the control box to the Border Pane



        loginGUI = new Scene(new VBox(new Label("WELCOME!"), new Label("Username:"), usernameField,new Label("Password:"),passwordField,submitButton,forgotPassButton,registerButton,disconnect));
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        forgotPassButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = "signIn/"+usernameField.getText()+","+passwordField.getText();
                boolean x = true;

                String response = client.getNetworkAccess().sendString(message,x);
                System.out.println(response);


            }
        });

        // -- clean up on close
        connectbutton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // -- do not allow a client to connect multiple times
                if (client == null) {

                    String host = ipAddressField.getText();// "192.168.1.8";//"127.0.0.1";
                    host = host.trim();
                    //Case Sensitive
                    //Leading and trailing white space
                    String localHost = "localhost";
                    String localHostIP = "127.0.0.1";
                    int port = 8000;
                    // -- instantiate a Client object
                    //    the constructor will attempt to connect to the server
                    if(host.equals(localHost)){
                        host = localHostIP;
                    }

                    if(checkIP(host)){
                        client = new Client(host, port);
                        mainStage.setTitle("Booked Blocks");
                        mainStage.setScene(loginGUI);

                    }else {
                        errorIncorrectIPFormat.setVisible(true);
                    }
                    //Check for 256.256.256.256





                }
            }
        });

    }
    // -- launch the application
    public void launchApp(String[] args)
    {
        System.out.println("launch");
        launch(args);
    }
    private boolean checkIP(String host) {
        try {
            String[] ipArray = host.split("\\.");
            for(int i = 0; i<=3; i++){
                if ((Integer.parseInt(ipArray[i])) <= 256 && (Integer.parseInt(ipArray[i]) >= 0)){
                    continue;
                }else {
                    return false;
                }

            }
            return true;
        }catch (Exception e){
            return false;
        }
    }
}
