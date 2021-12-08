package Client;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class logoutGUI extends Application {
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
        mainStage.setTitle("Logout?");
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


        private Label logout = new Label("Do you wish to logout");
        private Button yesButton = new Button("Yes");
        private Button noButton = new Button("No");



        public ControlBoxInner()
        {
            super();

            // -- create button handlers
            yesButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //logout
                }
            });
            noButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    //Hide panel
                }
            });



            // -- build a simple GUI
            this.getChildren().add(logout);
            this.getChildren().add(yesButton);
            this.getChildren().add(noButton);


        }
    }
}
