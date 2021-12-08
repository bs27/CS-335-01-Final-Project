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

public class PostLoginGUI extends Application
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
        mainStage.setTitle("Booked Blocks");
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
        //Welcome to Booked Blocks Username
        // High Scores Button
        //How to play Button
        //Change Password
        // Play Button


        private Label welcomeMesAGE = new Label("Welcome to Booked Blocks! " + getUsername());
        private Button highScoresButton = new Button("High Scores");
        private Button howToPlayButton = new Button("How to Play");
        private Button changePasswordButton = new Button("Change Password");
        private Button playButton = new Button("Play Now!");
        private Button disconnect = new Button("Disconnect");
        private Button logout = new Button("Log Out");


        public ControlBoxInner()
        {
            super();

            // -- create button handlers
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
                }
            });

            // -- build a simple GUI
            this.getChildren().add(welcomeMesAGE);
            this.getChildren().add(playButton);
            this.getChildren().add(highScoresButton);
            this.getChildren().add(howToPlayButton);
            this.getChildren().add(changePasswordButton);
            this.getChildren().add(logout);
            this.getChildren().add(disconnect);


        }
    }

    private String getUsername() {
        return "No username";
    }
}
