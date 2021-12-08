package Server;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class ServerGUI extends Application
{
    int WIDTH = 250;
    int HEIGHT = 256;
    
    private Scene mainScene;
    
    private Server server;
    
    // -- Main container
    private BorderPane pane;
    
    // -- Controls container
    private ControlBoxInner controlBox;   

    @Override
    public void start(Stage mainStage)
    {
    	// -- Application title
        mainStage.setTitle("JavaFX Graphics Application");
        mainStage.setWidth(WIDTH);
        
    	// -- construct the controls
    	controlBox = new ControlBoxInner(this);
         
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
        
        mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
             Platform.exit();
             System.exit(0);
            }
          });       
    }
    

    // -- launch the application 
    public void launchApp(String[] args)
    {
        launch(args);
    }

    public void addToTextArea(String s)
    {
    	controlBox.addToTextArea(s);
    }
    
    // -- Inner class for Controls
    public class ControlBoxInner extends VBox {
        
        private Button getconnectionsbutton = new Button("Get Connections");
        private Button startserverbutton = new Button("Start server");
        private TextField primefield = new TextField();
        private TextArea textarea = new TextArea();
        private ServerGUI owner = null;
        
        public ControlBoxInner(ServerGUI gui)
        {
        	super();
        	
        	this.owner = gui;
        	
        	// -- create button handlers
        	getconnectionsbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                	System.out.println(server);
            		int p = server.getconnections();
            		primefield.setText(p + "");
                 	pane.requestFocus();
                }
            });
            
       	
            startserverbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                	server = new Server(owner);
                	server.start();
                	pane.requestFocus();
                	startserverbutton.setDisable(true);
                }
            });
            
            // -- build a simple GUI
            int maxwidth = 100;
            primefield.setMaxWidth(maxwidth);
            textarea.setMaxWidth(maxwidth);
            textarea.setMaxHeight(100);
            this.getChildren().add(startserverbutton);
            this.getChildren().add(getconnectionsbutton);
    		this.getChildren().add(primefield);
    		this.getChildren().add(textarea);
    		
        }   
    	public void addToTextArea(String s)
    	{
    		textarea.appendText("SERVER receive: " + s + "\n");
    	}

     }
}
