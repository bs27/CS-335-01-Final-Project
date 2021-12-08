package Client;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ForgotPasswordGUI extends Application {
    int WIDTH = 400;
    int HEIGHT = 400;

    Scene recoverPasswordGUI;

    private TextField username;
    private TextField email;

    private Button toLoginPage;
    private Button toRegiPage;
    private Button disconnect;
    private Button submitRequest;

    @Override
    public void start(Stage mainStage) throws Exception
    {
        username = new TextField();
        email = new TextField();
        toLoginPage = new Button("Login Page");
        toRegiPage = new Button("to Registration Page");
        disconnect = new Button("Disconnect");
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
        links.getChildren().add(disconnect);
        root.setRight(links);
        BorderPane.setMargin(links, new Insets(10));
        root.setBottom(disconnect);
        BorderPane.setMargin(root.getBottom(), new Insets(10,10,10,WIDTH-100));
        recoverPasswordGUI = new Scene(root, WIDTH, HEIGHT);
        mainStage.setScene(recoverPasswordGUI);
        mainStage.show();
        root.requestFocus();
    }

    public static void main(String[] args) {
        launch(args);
    }
}