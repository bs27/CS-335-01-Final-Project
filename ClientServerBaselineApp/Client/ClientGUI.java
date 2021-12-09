package Client;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class ClientGUI extends Application
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
<<<<<<< Updated upstream
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
=======
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
                String username = usernameField2.getText();
                String password = passwordField2.getText();

                // Unsuccessful registration:
                boolean flag = false;
                String errorString = "Please correct the following issues with your registration:" + "\n";
                if(username.equals("")){
                    errorString = errorString + "No username inputted \n";
                    flag = true;
                }
                if(password.equals("")) {
                    errorString = errorString + "No password inputted \n";
                    flag = true;
                }else {
                    if (!RegexValidation.validSimplePassword(password)) {
                        flag = true;
                        errorString = errorString + "Password must be at least 8 characters long,\n" +
                                "have at least one digit, and have one upper or lower case letter\n";
                    }
                }
                if (email.equals("")) {
                    flag = true;
                    errorString = errorString + "No email inputted \n";
                }else {
                    if (!RegexValidation.validEmailAddress(email)) {
                        flag = true;
                        errorString = errorString + "Please enter a valid email address \n";
                    }

                }
                if (flag){
                    AlertBox.Display("Error!",errorString);
                }else {
                    String stringToSend = "register" + ";" + username + ";" + password + ";" + email;
                    client.getNetworkAccess().sendString(stringToSend,false);
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
>>>>>>> Stashed changes

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

        private Button disconnectbutton = new Button("Disconnect");
        private Button connectbutton = new Button("Connect");
        private Button messagebutton = new Button("Send Message");

        private TextField messagefield = new TextField();
        private TextArea responsearea = new TextArea();

<<<<<<< Updated upstream
        public ControlBoxInner()
        {
        	super();

        	// -- create button handlers
            messagebutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {

            		// -- send message to server and receive reply.
            		String commandString;
            		String replyString;

           			// -- send a String to the server and wait for the response
           			commandString = messagefield.getText();
           			System.out.println("CLIENT send:  " + commandString);
           			replyString = client.getNetworkAccess().sendString(commandString, true);
           			responsearea.appendText("CLIENT receive: " + replyString + "\n");
=======
                if(oldPassword.equals("") || newPassword.equals("") || verifyNewPassword.equals("")) {
                    AlertBox.Display("Empty Fields", "Enter your information in all fields");
                    success = false;
                }

                if(!RegexValidation.validSimplePassword(newPassword)) {
                    AlertBox.Display("Invalid Password", "Password must be at least 8 characters long," +
                            " have at least one digit, and have one upper or lower case letter");
                    success = false;
                }

                if(oldPassword.equals(newPassword)) {
                    AlertBox.Display("Invalid Password", "New password cannot match old password");
                    success = false;
                }

                if(!newPassword.equals(verifyNewPassword)) {
                    AlertBox.Display("Passwords do not match", "New password and verify new password must match");
                    success = false;
>>>>>>> Stashed changes
                }
            });

            connectbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                	// -- do not allow a client to connect multiple times
                	if (client == null) {
	            		String host = "127.0.0.1";// "192.168.1.8";//"127.0.0.1";
	            		int port = 8000;
	            		// -- instantiate a Client object
	            		//    the constructor will attempt to connect to the server
	            		client = new Client(host, port);
                	}
                }
            });

            disconnectbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
            		// -- disconnect from the server
            		client.disconnect();
            		client = null;
                	pane.requestFocus();
                }
            });

            // -- build a simple GUI
            int maxwidth = 100;
            messagefield.setMaxWidth(maxwidth);
            responsearea.setMaxWidth(maxwidth);
            responsearea.setMaxHeight(200);
    		this.getChildren().add(connectbutton);
            this.getChildren().add(disconnectbutton);
    		this.getChildren().add(messagebutton);
            this.getChildren().add(new Label("Message"));
    		this.getChildren().add(messagefield);
            this.getChildren().add(new Label("Response"));
    		this.getChildren().add(responsearea);

        }
     }
}
