package model.MenuModels;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
/**
 * create, initialize and style the button for the game
 * @author keitaro
 *
 */
public class Buttons extends Button {
	
	private final String FONT_PATH= "src/model/MenuResources/LBRITE.TTF";
	private final String BUTTON_PRESSED_STYLE="-fx-background-color: transparent; -fx-background-image: url(/model/MenuResources/buttongreen.png)";
	private final String BUTTON_RELEASED_STYLE="-fx-background-color: transparent; -fx-background-image: url(/model/MenuResources/buttonbrown.png)";

	
	public Buttons(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_RELEASED_STYLE);
		intitializeButtonListners();
	}
	public void setButtonFont() {
		
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 23));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 23));
		}
		
	}
	//method to style button when button is pressed
	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}
	//method to style button in normal conditions
	private void setButtonReleasedStyle() {
		setStyle(BUTTON_RELEASED_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
		
	}
	
	private void intitializeButtonListners() {
		
		
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}	
			}
		});
		
		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if(event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}	
			}
		});
		
		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow(4.0, Color.WHITE));
			}
		});
		
		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(null);
			}
		});
	}
	
	
	
	
	
	
	
	
}
	
	