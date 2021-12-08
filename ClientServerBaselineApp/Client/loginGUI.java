package Client;


import Client.Client;
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

public class loginGUI extends Application
{
    Client client = null;

    int WIDTH = 256;
    int HEIGHT = 256;

    private Scene mainScene;

    // -- Main container
    private BorderPane pane;

    // -- Controls container
    private ControlBoxInner controlBox;

    @Override
    public void start(Stage mainStage)
    {
        System.out.println("start");
        // -- Application title
        mainStage.setTitle("JavaFX Graphics Application");
        mainStage.setWidth(WIDTH);

        // -- construct the controls
        controlBox = new ControlBoxInner();

        // -- create the primary window structure
        pane = new BorderPane();

        // -- add the graphics canvas and the control box to the Border Pane
        pane.setLeft(controlBox);

        mainScene = new Scene(pane);
        mainStage.setScene(mainScene);

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

        // -- display the application window
        mainStage.show();

        // -- set keyboard focus to the pane
        pane.requestFocus();

    }


    // -- launch the application
    public void launchApp(String[] args)
    {
        System.out.println("launch");
        launch(args);
    }


    // -- Inner class for Controls
    public class ControlBoxInner extends VBox {
        //Label
        //Usernamefield
        //Label
        //password field
        //Submit button
        //Register
        //Forgot Password




        private Button submitButton = new Button("Submit");
        private Button forgotPassButton = new Button("Forgot your Password?");
        private Button registerButton = new Button("Register");
        private TextField usernameField = new TextField();
        private PasswordField passwordField = new PasswordField();
        private Button disconnect = new Button("Disconnect");

        public ControlBoxInner()
        {
            super();

            // -- create button handlers
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

                }
            });

            // -- build a simple GUI
            int maxwidth = 100;
            this.getChildren().add(new Label("WELCOME!"));
            this.getChildren().add(new Label("Username:"));
            this.getChildren().add(usernameField);
            this.getChildren().add(new Label("Password:"));
            this.getChildren().add(passwordField);

            this.getChildren().add(submitButton);
            this.getChildren().add(forgotPassButton);
            this.getChildren().add(registerButton);


        }
    }
}
