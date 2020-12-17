package model.MenuModels;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;;

public class GameSubScene extends SubScene{
	
	private final static String BACKGROUND_IMAGE="model/MenuResources/green_panel.png";
	
	private boolean isHidden;

	public GameSubScene() {
		super(new AnchorPane(), 340, 550);
		prefWidth(600);
		prefHeight(400);
		
		BackgroundImage image = new BackgroundImage(new Image(BACKGROUND_IMAGE,340,550,false,true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 =(AnchorPane) this.getRoot(); //getting root for this class
		
		root2.setBackground(new Background(image));
		
		isHidden=true;
		
		//start by hiding it from the screen
		setLayoutX(600);
		setLayoutY(200);
	}
	//method to decide when to move the subscene
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this); 
		
		if(isHidden) {
		transition.setToX(-345);
		isHidden=false;
		}else {
			transition.setToX(0);
			isHidden=true;
		}
		
		transition.play();
	}
	
	//return the Pane for the subscene 
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}
	

	
}
