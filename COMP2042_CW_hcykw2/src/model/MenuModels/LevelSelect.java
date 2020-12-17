package model.MenuModels;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
/**
 * This class creates hbox that contain images for selecting the levels.
 * @author keitaro
 *
 */


public class LevelSelect extends HBox{
	
	private ImageView frogLevelImage;
	private ImageView boxImage;
	
	//store the path to images that will be presented on the subscene 
	private String boxnotticked= "view/ViewResources/levelchooser/Unchecked.png";
	private String boxticked ="view/ViewResources/levelchooser/Checked.png";
	
	private Levels level; 
	
	private boolean isBoxTicked; //boolean that says whether or not a level is choosen
	
	public LevelSelect(Levels level) {
		boxImage = new ImageView(boxnotticked);
		frogLevelImage= new ImageView(level.getUrl());
		this.level= level;
		isBoxTicked= false; //at the start the button is not choosen 
		
		this.setAlignment(Pos.CENTER);
		this.setSpacing(5);
		
		this.getChildren().add(frogLevelImage); //adding images to the vbox
		this.getChildren().add(boxImage);
		
		this.getChildren().add(setNormalInfoLabel(level.toString(), false));
	}
	
	
	private Texts setNormalInfoLabel(String labelName, boolean isMainLabel) {
		Texts label = new Texts(labelName, isMainLabel);
		return label;
	}

	public Levels getLevel() {
		return level;
	}
	
	public boolean getisBoxticked() {
		return isBoxTicked;
	}
	
	public void setisBoxticked(boolean isboxticked) {
		this.isBoxTicked =isboxticked;
		String imageToSet= this.isBoxTicked ? boxticked :boxnotticked;
		boxImage.setImage(new Image(imageToSet));
	}
	
	
	
	
	
	
	
}
