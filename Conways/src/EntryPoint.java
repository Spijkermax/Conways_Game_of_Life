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
import javafx.scene.Node;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
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

	final int width = 600;
	final int height = 600;
	int rW;
	int rH;

	CellWorld cellWorld1 = new CellWorld(20, 10);
	Cell[][] world = cellWorld1.getWorld();
	Group group = new Group();

	public void start(Stage primaryStage) throws Exception {

		/** Main pane layout (BorderPane) */
		BorderPane root = new BorderPane();
		/** Styling everything with a css file */
		root.getStylesheets().add("styling.css");

// ------------------------------------------------------------------------- 		

		/** loading in images */
		Image pulsari = new Image("pulsar.png");
		ImageView pulsariv = new ImageView(pulsari);
		pulsariv.setFitHeight(30);
		pulsariv.setFitWidth(30);

		Image rpentominoi = new Image("rpentomino.png");
		ImageView rpentominov = new ImageView(rpentominoi);
		rpentominov.setFitHeight(30);
		rpentominov.setFitWidth(30);
		
		Image pufferfishrakei = new Image("Pufferfish.png");
		ImageView pufferfishrakev = new ImageView(pufferfishrakei);
		pufferfishrakev.setFitHeight(30);
		pufferfishrakev.setFitWidth(30);
		
		Image windmilli = new Image("Windmill.png");
		ImageView windmillv = new ImageView(windmilli);
		windmillv.setFitHeight(30);
		windmillv.setFitWidth(30);

// ----------------------------------------------------------------------------- 	

		/** Button pane on the left side */
		VBox buttonpanel = new VBox(8);
		buttonpanel.setId("sidepanel");
		buttonpanel.setSpacing(20);
		buttonpanel.setPadding(new Insets(20, 20, 20, 20));
		buttonpanel.setAlignment(Pos.CENTER);
		//buttons
		Button start = new Button("Start");
		Button stop = new Button("Stop");
		Button reset = new Button("Reset");
		Button clear = new Button("Clear");
		Text lefttitle = new Text("Controls");
		lefttitle.setId("lefttitle");
		root.setLeft(buttonpanel);
		buttonpanel.getChildren().addAll(lefttitle, start, stop, reset, clear);
		

		/** Button pane on the right side */
		VBox presetpanel = new VBox(8);
		presetpanel.setId("rightpanel");
		presetpanel.setSpacing(20);
		presetpanel.setPadding(new Insets(20, 20, 20, 20));
		presetpanel.setAlignment(Pos.CENTER);
		root.setRight(presetpanel);
		//buttons
		Button pulsar = new Button("Pulsar");
		pulsar.setGraphic(pulsariv);
		Button rpentomino = new Button("Pentomino");
		rpentomino.setGraphic(rpentominov);
		Button pufferfish = new Button("Pufferfish");
		pufferfish.setGraphic(pufferfishrakev);
		Button windmill = new Button("Windmill");
		windmill.setGraphic(windmillv);
		Text righttitle = new Text("Preset Games");
		righttitle.setId("righttitle");
		presetpanel.getChildren().addAll(righttitle, pulsar, rpentomino, pufferfish, windmill);

// ----------------------------------------------------------------------------------- 
		/** title at the top */
		HBox toppanel = new HBox(8);
		toppanel.setId("toppanel");
		root.setTop(toppanel);
		toppanel.setAlignment(Pos.CENTER);

		Blend blend = new Blend();
		blend.setMode(BlendMode.MULTIPLY);
		Text text = new Text();
		toppanel.getChildren().add(text);
		text.setText("Conways Game of Life");
		text.setId("gametitle");
		DropShadow d1 = new DropShadow();
		d1.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		d1.setOffsetX(5);
		d1.setOffsetY(5);
		d1.setRadius(5);
		d1.setSpread(0.2);
		DropShadow d2 = new DropShadow();
		d2.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		d2.setRadius(20);
		d2.setSpread(0.2);
		InnerShadow is1 = new InnerShadow();
		is1.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		is1.setRadius(9);
		is1.setChoke(0.8);
		InnerShadow is2 = new InnerShadow();
		is2.setColor(javafx.scene.paint.Color.LIGHTBLUE);
		is2.setRadius(5);
		is2.setChoke(0.4);
		Blend blend1 = new Blend();
		blend1.setMode(BlendMode.MULTIPLY);
		blend1.setBottomInput(d1);
		blend1.setTopInput(d2);
		blend.setTopInput(blend1);
		text.setEffect(blend);

// ------------------------------------------------------------------------------ 

		/** Bottom pane */
		VBox bottompanel = new VBox();
		Text sampletext = new Text(
				"Conways Game of Life is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input.");
		sampletext.setId("sampletext");
		bottompanel.setAlignment(Pos.CENTER);
		bottompanel.getChildren().add(sampletext);
		bottompanel.setId("bottompanel");
		root.setBottom(bottompanel);

//------------------------------------------------------------------------------------------

		/** Making the gamepane and loading a game when application opens */
		Pane gamepane = new Pane();
		gamepane.getChildren().add(group);
		gamepane.setId("centerpane");
		root.setCenter(group);
		cellWorld1.tick();
		drawSquares();

		/** preparing the scene */
		Scene scene = new Scene(root, width, height);
		primaryStage.setScene(scene);
		primaryStage.setFullScreen(true);
		primaryStage.show();

//----------------------------------------------------------------------------------------

		/** setting up Timeline and frames to animate each tick of game */

		KeyFrame frame = new KeyFrame(Duration.millis(200), new EventHandler<ActionEvent>() {

			public void handle(ActionEvent arg0) {
				cellWorld1.tick();
				drawSquares();
			}

		});

		Timeline t = new Timeline(frame);
		t.setCycleCount(javafx.animation.Animation.INDEFINITE);

//--------------------------------------------------------------------------------------

		/** Binding buttons with actions */

		/** Start button */
		start.setOnAction(e -> {
			cellWorld1.setWorld(world);

			cellWorld1.tick();

			t.play();

		});
		/** Stop button */
		stop.setOnAction(e -> {
			t.stop();

		});
		/** reset button */
		reset.setOnAction(e -> {
			group.getChildren().clear(); // Clear the pane
			CellWorld cellWorld2 = new CellWorld(20, 10);
			Cell[][] newworld = cellWorld2.getWorld();
			cellWorld1.setWorld(newworld);
			cellWorld1.tick();
			drawSquares();
			t.stop();
			// Clear the array of the world
		});
		/** clear button */
		clear.setOnAction(e -> {

		});
		/** pulsar button */
		pulsar.setOnAction(e -> {
			
		});
		/** pufferfish button */
		pufferfish.setOnAction(e -> {
			
		});
		/**r-pentomino button */
		rpentomino.setOnAction(e -> {
			
		});
		/** windmill button */
		windmill.setOnAction(e -> {
			
			
		});

		group.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> fillSquare(event.getX(), event.getY()));
	}

	public static void main(String[] args) {
		launch(args);

	}
// --------------------------------------------------------------------------------------------------------
	/** Methods */
	
	public void drawSquares() {
		group.getChildren().clear();
		world = cellWorld1.getWorld();
		rH = height / world.length;
		for (int j = 0; j < world.length; j++) {
			rW = width / world[j].length;
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
				group.setId("gamecontents");

			}

		}

	}

	public void fillSquare(double x, double y) {

//      world = cellWorld1.getWorld();
//      rH = height/world.length;
		for (Node n : group.getChildren()) {
			if (x >= n.getTranslateX() && x < n.getTranslateX() + ((Rectangle) n).getWidth() && y >= n.getTranslateY()
					&& y < n.getTranslateY() + ((Rectangle) n).getHeight()) {
				double rx = n.getTranslateX();
				double ry = n.getTranslateY();
				double cx = (rx - 5) / rW;
				double cy = (ry - 5) / rH;
				world[(int) cy][(int) cx].swapState();
				break;
			}
		}
		drawSquares();
	}
}
