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

public class RegistrationGUI extends Application
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
        //Register Username field
        //Label
        //Register Password field
        //Label
        //Register Email field
        //Register button
        //Back to login page

        private Button registerButton = new Button("Register");
        private Button loginPageButton = new Button("Back to Login Page");
        private Button disconnectBtn = new Button("Disconnect");

        private TextField usernameField = new TextField();
        private PasswordField passwordField = new PasswordField();
        private TextField emailField = new TextField();

        public void errorPopup (String title, String contentText) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(title);
            error.setContentText(contentText);
        }

        public ControlBoxInner()
        {
            super();

            // -- create button handlers
            registerButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    String email = emailField.getText();
                    String username = usernameField.getText();
                    String password = passwordField.getText();

                    // Unsuccessful registration:
                    if(email.equals("") || username.equals("") || password.equals("")) {
                        errorPopup("Empty fields", "Enter your registration information in all fields");
                    }

                    if(!RegexValidation.validEmailAddress(email)) {
                        errorPopup("Invalid Email Address", "Please enter a valid email address");
                    }

                    if(!RegexValidation.validSimplePassword(password)) {
                        errorPopup("Invalid Password",
                                "Password must be at least 8 characters long," +
                                        " have at least one digit, and have one upper or lower case letter");
                    }

                    // Successful Registration: Send email, username, password to database
                    // Then, go back to login page (refer to ben's login page scene)

                    // mainStage.setScene(loginGUI);
                }
            });

            loginPageButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // refer to ben's login page scene
//                    mainStage.setScene(loginGUI);
                }
            });

            // -- build a simple GUI
            int maxwidth = 100;
            this.getChildren().add(new Label("Register Page: "));
            this.getChildren().add(new Label("Email:"));
            this.getChildren().add(emailField);
            this.getChildren().add(new Label("Username:"));
            this.getChildren().add(usernameField);
            this.getChildren().add(new Label("Password:"));
            this.getChildren().add(passwordField);

            this.getChildren().add(registerButton);
            this.getChildren().add(loginPageButton);
            this.getChildren().add(disconnectBtn);

        }
    }
}