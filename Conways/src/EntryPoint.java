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
		VBox buttonpanel = new VBox(8); //HBox to set buttons horizontally
		Button run = new Button("Start"); 
		Button stop = new Button("Stop");
		buttonpanel.getChildren().addAll(run, stop);
		rootpane.setLeft(buttonpanel);
		buttonpanel.setAlignment(Pos.TOP_CENTER);
		
		/** title at the top */
		HBox toppanel = new HBox(8);
		Text gametitle = new Text("Conways Game of Life");
		gametitle.setId("gametitle");
		toppanel.getChildren().add(gametitle);
		rootpane.setTop(toppanel);
		toppanel.setAlignment(Pos.CENTER);
		
		/** Pane for the game to go into */
		Pane gamepane = new Pane();
		gamepane.setStyle("-fx-background-color: black"); //Just to see where the grid is before we implement grid
		rootpane.setCenter(gamepane);
		
		/** Descriptive box at the bottom (could add game descriptions, cell counter (how many are alive) */
		HBox bottompanel = new HBox();
		Text sampletext = new Text("We will use this for descriptions");
		bottompanel.getChildren().add(sampletext);
		bottompanel.setStyle("-fx-background-color: red");
		rootpane.setBottom(bottompanel);
			
		/** Styling everything with a css file */
		rootpane.getStylesheets().add("styling.css");
		
		Scene scene = new Scene(rootpane, width, height);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);

	}

}
