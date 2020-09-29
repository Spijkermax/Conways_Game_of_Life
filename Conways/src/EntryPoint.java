import java.awt.Color;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EntryPoint extends Application {

	final int width = 600;
	final int height = 600;

	CellWorld cellWorld1 = new CellWorld(20, 10);
	Cell[][] world = cellWorld1.getWorld();
	Group group = new Group();

	public void start(Stage primaryStage) throws Exception {

		/** Main pane layout (BorderPane) */
		BorderPane rootpane = new BorderPane();

		/** Make a button pane at the bottom */
		VBox buttonpanel = new VBox(8); // HBox to set buttons horizontally
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		buttonpanel.getChildren().addAll(start, stop);
		rootpane.setLeft(buttonpanel);
		buttonpanel.setAlignment(Pos.TOP_CENTER);

		/** title at the top */
		HBox toppanel = new HBox(8);
		Text gametitle = new Text("Conways Game of Life is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.");
		gametitle.setId("gametitle");
		toppanel.getChildren().add(gametitle);
		rootpane.setTop(toppanel);
		toppanel.setAlignment(Pos.CENTER);

		/** Pane for the game to go into */
		GridPane gamepane = new GridPane();

		gamepane.setStyle("-fx-background-color: black"); // Just to see where the grid is before we implement grid
		rootpane.setCenter(gamepane);

		/**
		 * Descriptive box at the bottom (could add game descriptions, cell counter (how
		 * many are alive)
		 */
		HBox bottompanel = new HBox();
		Text sampletext = new Text("We will use this for descriptions");
		bottompanel.getChildren().add(sampletext);
		bottompanel.setStyle("-fx-background-color: red");
		rootpane.setBottom(bottompanel);

		/** Styling everything with a css file */
		rootpane.getStylesheets().add("styling.css");
		drawSquares();

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				drawSquares();
			}

		});

		Timeline t = new Timeline(frame);
		t.setCycleCount(javafx.animation.Animation.INDEFINITE);
		rootpane.setCenter(group);
		Scene scene = new Scene(rootpane, width, height);
		primaryStage.setScene(scene);
		primaryStage.show();

		/** Start button */
		start.setOnAction(e -> {
			t.play();

		});
		/** Stop button */
		stop.setOnAction(e -> {
			t.stop();

		});
	}

	public static void main(String[] args) {
		launch(args);

	}

	public void drawSquares() {
		group.getChildren().clear();
		cellWorld1.tick();
		world = cellWorld1.getWorld();
		int rH = height / world.length;
		for (int j = 0; j < world.length; j++) {
			int rW = width / world[j].length;
			for (int i = 0; i < world[j].length; i++) {
				Rectangle r = new Rectangle();
				r.setWidth(rW);
				r.setHeight(rH);
				r.setTranslateX(world[j][i].getX() * rW + 5);
				r.setTranslateY(world[j][i].getY() * rH + 5);
				if (world[j][i].isAlive()) {
					r.setFill(javafx.scene.paint.Color.BLACK);
				} else {
					r.setFill(javafx.scene.paint.Color.GREY);

				}
				group.getChildren().add(r);
			}
		}

	}
}
