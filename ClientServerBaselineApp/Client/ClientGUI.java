package Client;


import com.sun.corba.se.impl.orb.ParserTable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
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
    Scene registrationGUI;
    Scene recoverPasswordGUI;
    Scene postLoginGUI;
    Scene changePasswordGUI;

//Login GUI
    Button submitButton = new Button("Submit");
    Button forgotPassButton = new Button("Forgot your Password?");
    Button registerButton = new Button("Register");
    TextField usernameField = new TextField();
    PasswordField passwordField = new PasswordField();
    Button connectbutton = new Button("Connect");
    private Button disconnect = new Button("Disconnect");
//Connect GUI
    Label enterIP = new Label("Enter IP Address Below");
    TextField ipAddressField = new TextField("0.0.0.0");
    Label errorIncorrectIPFormat = new Label("This isn't an IP Address DUMBASS!");
    //Registration GUI
    private Button registerButton2 = new Button("Submit");
    private Button loginPageButton = new Button("Back to Login Page");
    private Button disconnectBtn = new Button("Disconnect");
    private TextField usernameField2 = new TextField();
    private PasswordField passwordField2 = new PasswordField();
    private TextField emailField = new TextField();
    //Forgot/Recover Password
    private TextField username;
    private TextField email;
    private Button toLoginPage;
    private Button toRegiPage;
    private Button disconnect2;
    private Button submitRequest;
    //Post Login GUI
    private Label welcomeMesAGE = new Label("Welcome to Booked Blocks! " + getUsername());
    private Button highScoresButton = new Button("High Scores");
    private Button howToPlayButton = new Button("How to Play");
    private Button changePasswordButton = new Button("Change Password");
    private Button playButton = new Button("Play Now!");
    private Button disconnect3 = new Button("Disconnect");
    private Button logout = new Button("Log Out");
    //Change Login GUI
    private Button updatePassword = new Button("Update Password");
    private Button backToPostLogin = new Button("Back to Menu");
    private Button disconnectBtn2 = new Button("Disconnect");
    private TextField oldPasswordField = new TextField();
    private TextField newPasswordField = new TextField();
    private TextField verifyNewPasswordField = new TextField();
    private Text changePassword = new Text("Change Password");
    private Text oldPasswordText = new Text("Old Password");
    private Text newPasswordText = new Text("New Password");
    private Text verifyPasswordText = new Text("Verify New Password");






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
        disconnect.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // -- disconnect from the server
                client.disconnect();
                client = null;
                mainStage.setScene(connectGUI);

            }
        });


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
        //Recover Password GUI
        username = new TextField();



        email = new TextField();
        toLoginPage = new Button("Login Page");
        toRegiPage = new Button("to Registration Page");
        disconnect2 = new Button("Disconnect");

        submitRequest = new Button("Submit");
        BorderPane root = new BorderPane();
        // set up
        VBox input = new VBox();
        input.setSpacing(10);
        input.getChildren().add(new Label("~Recover Password~"));
        input.getChildren().add(new Label("Username"));
        input.getChildren().add(username);
        input.getChildren().add(new Label("Email"));
        input.getChildren().add(email);
        input.getChildren().add(submitRequest);
        root.setLeft(input);
        BorderPane.setMargin(input, new Insets(10));
        VBox links = new VBox();
        links.setSpacing(10);
        links.getChildren().add(toRegiPage);
        links.getChildren().add(toLoginPage);
        links.getChildren().add(new Label(" ")); // empty space
        links.getChildren().add(disconnect2);
        root.setRight(links);
        BorderPane.setMargin(links, new Insets(10));
        root.setBottom(disconnect2);
        BorderPane.setMargin(root.getBottom(), new Insets(10,10,10,WIDTH-100));
        recoverPasswordGUI = new Scene(root, WIDTH, HEIGHT);
        disconnect2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // -- disconnect from the server
                client.disconnect();
                client = null;
                mainStage.setScene(connectGUI);

            }
        });

        //post Login GUI
        VBox postLayout = new VBox(welcomeMesAGE, playButton, highScoresButton, howToPlayButton, changePasswordButton, logout, disconnect3);
        postLoginGUI = new Scene(postLayout);
        disconnect3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // -- disconnect from the server
                // DON't Forget to Logout
                System.out.println("Don't forget to logout");
                client.disconnect();
                client = null;
                mainStage.setScene(connectGUI);

            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // DON't Forget to Logout
                System.out.println("Don't forget to logout");
                mainStage.setScene(loginGUI);

            }
        });


        loginGUI = new Scene(new VBox(new Label("WELCOME!"), new Label("Username:"), usernameField,new Label("Password:"),passwordField,submitButton,forgotPassButton,registerButton,disconnect));
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(registrationGUI);
            }
        });

        forgotPassButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(recoverPasswordGUI);
                mainStage.show();
                root.requestFocus();

            }
        });

        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String message = "signIn/"+usernameField.getText()+","+passwordField.getText();
                boolean x = true;

                String response = client.getNetworkAccess().sendString(message,x);
                System.out.println(response+"No sign in implemented");
                mainStage.setScene(postLoginGUI);




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
        VBox layout = new VBox(new Label("Register Page: "),new Label("Email:"),emailField,new Label("Username:"),usernameField2,new Label("Password:"),passwordField2,registerButton2,loginPageButton,disconnectBtn);
        registrationGUI = new Scene(layout);
        // -- create button handlers
        registerButton2.setOnAction(new EventHandler<ActionEvent>() {
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
                   mainStage.setScene(loginGUI);
            }
        });
        toLoginPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(loginGUI);
            }
        });
        toRegiPage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(registrationGUI);
            }
        });

        // -- create button handlers for Post Login
        highScoresButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


            }
        });
        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {


            }
        });

        howToPlayButton.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent actionEvent) {

                                        }
                                    }
        );

        changePasswordButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                mainStage.setScene(changePasswordGUI);

            }
        });
        disconnectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // -- disconnect from the server
                client.disconnect();
                client = null;
                mainStage.setScene(connectGUI);

            }
        });





        int maxwidth = 100;
        oldPasswordField.setMaxWidth(maxwidth);
        newPasswordField.setMaxWidth(maxwidth);
        verifyNewPasswordField.setMaxWidth(maxwidth);
        VBox cPlayout = new VBox(changePassword, oldPasswordText, oldPasswordField, newPasswordText, newPasswordField,verifyPasswordText,verifyNewPasswordField,updatePassword,backToPostLogin,disconnectBtn);
        changePasswordGUI = new Scene(cPlayout);
        updatePassword.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                String oldPassword = oldPasswordField.getText();
                String newPassword = newPasswordField.getText();
                String verifyNewPassword = verifyNewPasswordField.getText();

                boolean success = true;

                if(oldPassword.equals("") || newPassword.equals("") || verifyNewPassword.equals("")) {
                    errorPopup("Empty Fields", "Enter your information in all fields");
                    success = false;
                }

                if(!RegexValidation.validSimplePassword(newPassword)) {
                    errorPopup("Invalid Password", "Password must be at least 8 characters long," +
                            " have at least one digit, and have one upper or lower case letter");
                    success = false;
                }

                if(oldPassword.equals(newPassword)) {
                    errorPopup("Invalid Password", "New password cannot match old password");
                    success = false;
                }

                if(!newPassword.equals(verifyNewPassword)) {
                    errorPopup("Passwords do not match", "New password and verify new password must match");
                    success = false;
                }

                if(success == true) {
                    // updates password in database
                    // refer to ben's loginPage
                    // mainStage.setScene(loginGUI);
                } else {
                    // empty fields for change password (go back to changePassword scene)
                    // mainStage.setScene(changePasswordGUI);
                }

            }
        });

        backToPostLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                // refer to ben's loginPage scene
                mainStage.setScene(postLoginGUI);
            }
        });

        disconnectBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // -- disconnect from the server
                client.disconnect();
                client = null;
                mainStage.setScene(connectGUI);
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
    public void errorPopup (String title, String contentText) {
        Alert error = new Alert(Alert.AlertType.ERROR);
        error.setTitle(title);
        error.setContentText(contentText);
    }private String getUsername() {
    return "No username";
}

}