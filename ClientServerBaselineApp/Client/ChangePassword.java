package Client;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ChangePassword extends Application {

    int WIDTH = 256;

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

        // -- display the application window
        mainStage.show();

        // -- set keyboard focus to the pane
        pane.requestFocus();
    }

//     -- launch the application
    public void launchApp(String[] args)
    {
        System.out.println("launch");
        launch(args);
    }


    // -- Inner class for Controls
    public class ControlBoxInner extends VBox {

        private Button updatePassword = new Button("Update Password");
        private Button backToLogin = new Button("Back to Login Page");
        private Button disconnectBtn = new Button("Disconnect");


        private TextField oldPasswordField = new TextField();
        private TextField newPasswordField = new TextField();
        private TextField verifyNewPasswordField = new TextField();

        private Text changePassword = new Text("Change Password");
        private Text oldPasswordText = new Text("Old Password");
        private Text newPasswordText = new Text("New Password");
        private Text verifyPasswordText = new Text("Verify New Password");

        public void errorPopup (String title, String contentText) {
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setTitle(title);
            error.setContentText(contentText);
        }

        public ControlBoxInner()
        {
            super();

            // -- create button handlers
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

            backToLogin.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    // refer to ben's loginPage scene
                    // mainStage.setScene(loginGUI);
                }
            });

            disconnectBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    // disconnects user and close client
                }
            });

            // -- build a simple GUI
            int maxwidth = 100;
            oldPasswordField.setMaxWidth(maxwidth);
            newPasswordField.setMaxWidth(maxwidth);
            verifyNewPasswordField.setMaxWidth(maxwidth);

            this.getChildren().add(changePassword);

            this.getChildren().add(oldPasswordText);
            this.getChildren().add(oldPasswordField);
            this.getChildren().add(newPasswordText);
            this.getChildren().add(newPasswordField);
            this.getChildren().add(verifyPasswordText);
            this.getChildren().add(verifyNewPasswordField);

            this.getChildren().add(updatePassword);
            this.getChildren().add(backToLogin);
            this.getChildren().add(disconnectBtn);

        }
    }
}
