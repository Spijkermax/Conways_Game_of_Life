import java.awt.Color;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EntryPoint extends Application {

	final int width = 600;
	final int height = 600;

	public void start(Stage primaryStage) throws Exception {
		
		/** Main pane layout (BorderPane) */
		BorderPane rootpane = new BorderPane();

		/** Make a button pane at the bottom */
		HBox buttonpanel = new HBox(8); //HBox to set buttons horizontally
		Button run = new Button("Run"); 
		Button stop = new Button("Stop");
		buttonpanel.getChildren().addAll(run, stop);
		rootpane.setBottom(buttonpanel);
		buttonpanel.setAlignment(Pos.TOP_CENTER);
		
		/** title at the top */
		HBox toppanel = new HBox(8);
		Text gametitle = new Text("Conways Game of Life");
		toppanel.getChildren().add(gametitle);
		rootpane.setTop(toppanel);
		toppanel.setAlignment(Pos.CENTER);
		
		/** Pane for the game to go into */
		Pane gamepane = new Pane();
		gamepane.setStyle("-fx-background-color: black"); //Just to see where the grid is before we implement grid
		rootpane.setCenter(gamepane);
		
		Scene scene = new Scene(rootpane, width, height);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
