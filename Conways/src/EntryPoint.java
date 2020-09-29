import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import javafx.util.Duration;

public class EntryPoint extends Application {

	final int width = 800;
	final int height = 800;

	CellWorld cellWorld1 = new CellWorld(20, 10);
	Cell[][] world = cellWorld1.getWorld();
	Group group = new Group();

	public void start(Stage primaryStage) throws Exception {

		/** Main pane layout (BorderPane) */
		BorderPane root = new BorderPane();
	//	Image image = new Image("download.jpg");

		/** Make a button pane at the bottom */
		VBox buttonpanel = new VBox(8); // HBox to set buttons horizontally
		buttonpanel.setPrefWidth(200);
		buttonpanel.setId("sidepanel");
		buttonpanel.setSpacing(20);
		buttonpanel.setPadding(new Insets(20, 20, 20, 20));
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button clear = new Button("Clear");
		MenuButton add = new MenuButton("Presets");
	//	buttonpanel.getChildren().addAll(new ImageView(image));
		add.setId("addbutton");
		add.getItems().addAll(new MenuItem("SpaceShip"), new MenuItem("Butterfly"));
		buttonpanel.getChildren().addAll(start, stop, clear, add);
		root.setLeft(buttonpanel);
		buttonpanel.setAlignment(Pos.CENTER);
		HBox view = new HBox();
		buttonpanel.getChildren().add(view);

		/** title at the top */
		HBox toppanel = new HBox(8);
		toppanel.setId("toppanel");
		root.setTop(toppanel);
		toppanel.setAlignment(Pos.CENTER);
		
		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);
		Text text = new Text();
		//TextField textField = new TextField();
		toppanel.getChildren().add(text);
		text.setText("Conways Game of Life");
		text.setId("gametitle");
		text.setFont(Font.font("Impact", 100));
	//	text.textProperty().bind(text.textProperty());
		
		
		
		DropShadow ds = new DropShadow();
		ds.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		ds.setOffsetX(5);
		ds.setOffsetY(5);
		ds.setRadius(5);
		ds.setSpread(0.2);

		blend.setBottomInput(ds);

		DropShadow ds1 = new DropShadow();
		ds1.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		ds1.setRadius(20);
		ds1.setSpread(0.2);

		Blend blend2 = new Blend();
		blend2.setMode(BlendMode.MULTIPLY);

		InnerShadow is = new InnerShadow();
		is.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		is.setRadius(9);
		is.setChoke(0.8);
		blend2.setBottomInput(is);

		InnerShadow is1 = new InnerShadow();
		is1.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		is1.setRadius(5);
		is1.setChoke(0.4);
		blend2.setTopInput(is1);

		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(ds1);
		blend1.setTopInput(blend2);

		blend.setTopInput(blend1);

		text.setEffect(blend);

		/**
		 * Descriptive box at the bottom (could add game descriptions, cell counter (how
		 * many are alive)
		 */
		HBox bottompanel = new HBox();
		Text sampletext = new Text(
				"Conways Game of Life is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.");
		bottompanel.getChildren().add(sampletext);
		bottompanel.setId("bottompanel");
		root.setBottom(bottompanel);
	

		/** Styling everything with a css file */
		root.getStylesheets().add("styling.css");

		BorderPane gamepane = new BorderPane();
		gamepane.getChildren().add(group);
		drawSquares();
		root.setCenter(group);
		Scene scene = new Scene(root, width, height);
		root.prefHeightProperty().bind(scene.heightProperty());
	    root.prefWidthProperty().bind(scene.widthProperty());
		primaryStage.setScene(scene);
		primaryStage.show();

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				drawSquares();
			}

		});

		Timeline t = new Timeline(frame);
		t.setCycleCount(javafx.animation.Animation.INDEFINITE);

		/** Start button */
		start.setOnAction(e -> {
			t.play();

		});
		/** Stop button */
		stop.setOnAction(e -> {
			t.stop();

		});
		/** Clear button */
		clear.setOnAction(e -> {

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
					r.setFill(javafx.scene.paint.Color.DARKBLUE);
				} else {
					r.setFill(javafx.scene.paint.Color.LIGHTBLUE);
				}
				group.getChildren().add(r);

			}

		}

	}
}
