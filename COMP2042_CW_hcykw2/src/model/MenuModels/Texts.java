package model.MenuModels;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class Texts extends Label{

	public final static String FONT_PATH= "src/model/MenuResources/LBRITE.TTF";
	public final static String FONT_PATH_2= "src/view/ViewAssets/HighscoreHero.ttf";
	
	
	
	
	public Texts() {
		
	}
	public Texts(String text) {
		
		setPrefWidth(380);
		setPrefHeight(49);

		setText(text);
		setWrapText(true);
		setLabelFont(FONT_PATH);
		setAlignment(Pos.CENTER);
			
	}
	
	public Texts(String text, boolean isMainLabel) {
		
		
			setText(text);
			setWrapText(true);
			setLabelFont(FONT_PATH);
		
		
	}
	
	
	
	
	//method to set the font of the label
	private void setLabelFont(String fontPath) {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(fontPath)), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana",23));
			
		}
	}
	
	
	
	
}
