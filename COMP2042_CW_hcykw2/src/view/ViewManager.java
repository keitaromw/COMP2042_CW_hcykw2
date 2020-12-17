package view;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.MenuModels.Buttons;
import model.MenuModels.GameSubScene;
import model.MenuModels.Texts;
import model.MenuModels.Levels;
import model.MenuModels.LevelSelect;

public class ViewManager {
	
	private static final int WIDTH = 600;
	private static final int HEIGHT = 800;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	public final static String FONT_PATH= "src/model/MenuResources/LBRITE.TTF";
	private final static int MENU_BUTTON_START_X=40;
	private final static int MENU_BUTTON_START_Y=220;
	
	private GameSubScene subSceneScore;
	private GameSubScene subSceneHelp;
	private GameSubScene subSceneLevelChooser;
	
	private GameSubScene sceneToHide; //field to store scene that is hidden
	
	List<Buttons> menuButtons; //a list to contain all the menu buttons at the start
	
	List<LevelSelect> levelList; //list that stores levels
	private Levels choosenLevel; 
	
	public ViewManager() {
		menuButtons= new ArrayList<>();
		mainPane= new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage=new Stage();
		mainStage.setScene(mainScene);
		mainStage.setResizable(false);
		createButton();
		createBackground();
		createSubScene();
	}
	
	//move a hidden subscene
	private void showSubScene(GameSubScene subScene) {
		if(sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		//show the subscene
		subScene.moveSubScene();
		sceneToHide=subScene;
	}
	
	private void createSubScene() {
		
		createLevelChooserSubScene();
		createHelpSubScene();
		createScoreSubScene();
		
	}
	
	private void createScoreSubScene() {
		subSceneScore = new GameSubScene();
		mainPane.getChildren().add(subSceneScore);
		
		Texts heading =new Texts("HIGH-SCORE", true);
		heading.setLayoutX(50);
		heading.setLayoutY(27);
		subSceneScore.getPane().getChildren().add(heading);
		
		VBox column = new VBox();
		column.setLayoutX(40);
		column.setLayoutY(100);
		
		ArrayList<HBox> hboxList= new ArrayList<HBox>();

		column.setSpacing(30);
		
		ArrayList<Texts> levelLabelList = new ArrayList<Texts>();
		for(Levels level: Levels.values()) {	
			Texts levelLabel;
			if(level.getLevel().equals("LEVEL1")) {
				levelLabel = new Texts(level.getLevel()+":");
			} else {
			levelLabel = new Texts(level.getLevel()+":");}
			levelLabel.setPrefSize(100, 20);
			levelLabel.setTextFill(Color.web("#6e0412",0.8));
			levelLabelList.add(levelLabel);
			hboxList.add(new HBox());
		}	
		for(HBox hbox: hboxList) {
			hbox.setSpacing(10);
			hbox.setLayoutX(50);
			hbox.setLayoutY(100);
			hbox.setAlignment(Pos.CENTER_LEFT);
		}
		
		
		Score scoreHandler =new Score();
		
		scoreHandler.readHighScores();
		ArrayList<String> highScoreList =scoreHandler.getHighScoresList();
		for(int i=0;i<highScoreList.size();i++) {
			Texts name= new Texts((highScoreList.get(i)).split(":")[0]);
			name.setPrefSize(100, 20);
			Texts score = new Texts((highScoreList.get(i)).split(":")[1]);
			score.setPrefSize(70, 20);
			hboxList.get(i).getChildren().add(levelLabelList.get(i));
			hboxList.get(i).getChildren().add(name);
			hboxList.get(i).getChildren().add(score);
			
		}
		
	
		for(int i=0;i<highScoreList.size();i++) {
			column.getChildren().add(hboxList.get(i));
		}
		
		subSceneScore.getPane().getChildren().add(column);
	
	}

	private void createHelpSubScene() {
		subSceneHelp = new GameSubScene();
		mainPane.getChildren().add(subSceneHelp);
		
		Texts heading =new Texts("             HELP"+"\n\n>Use 'W','A','S','D' "+"\nto move"+"\n"
		+"\n>Avoid Crocs,"+"\nCars,Trucks,"+"\nand falling "+"\ninto water"
				+"\n\n>You have 3 lives"
		+"\n\n>Theres is a"+"\nbonus round if"+"\nyou pass the level", true);
		heading.setLayoutX(30);
		heading.setLayoutY(20);
		subSceneHelp.getPane().getChildren().add(heading);
		


	}

	//method used to create levelchoosersubscene 
	private void createLevelChooserSubScene() {
		subSceneLevelChooser= new GameSubScene();
		mainPane.getChildren().add(subSceneLevelChooser);
		
		Texts chooseLevelLabel = new Texts("CHOOSE LEVEL");
		chooseLevelLabel.setLayoutX(20);
		chooseLevelLabel.setLayoutY(15);
		
		
		
		subSceneLevelChooser.getPane().getChildren().add(chooseLevelLabel);
		
		subSceneLevelChooser.getPane().getChildren().add(createLevelsToChoose()); //adds the hbox to the subscene 
		
		subSceneLevelChooser.getPane().getChildren().add(createStartButton()); // adds start button to the subscene 
		
		
	}
	
	//method below makes the images for the levels
	private VBox createLevelsToChoose() {
		VBox box = new VBox();
		box.setSpacing(40);
		levelList = new ArrayList<LevelSelect>();

		for(Levels level: Levels.values()) {
			LevelSelect levelToPick= new LevelSelect(level);			
			levelList.add(levelToPick);
			box.getChildren().add(levelToPick);
			levelToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					for(LevelSelect level: levelList) {
						level.setisBoxticked(false); //if we click on one of the levels the rest would be set to false
					}
					levelToPick.setisBoxticked(true);//the chosen level will be set to chosen 
					choosenLevel=levelToPick.getLevel();
				}
			});
		}
		box.setLayoutX(20);
		box.setLayoutY(50);
		return box;
	}
	
	private Buttons createStartButton() {
		Buttons startButton =new Buttons("START");
		startButton.setLayoutX(100);
		startButton.setLayoutY(400);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				if(choosenLevel != null) {
					
					GameManager gameManager= new GameManager();
					gameManager.createNewGame(mainStage, choosenLevel);
				}
			}
		});
		
		return startButton;
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(Buttons button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y+ menuButtons.size() *100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	private void createButton() {
		createScoreButton();
		createPlayButton();
		createHelpButton();
		createExitButton();
		createLogo();
		createIcon();
	}
	
	private void createPlayButton() {
		Buttons playButton = new Buttons("LEVEL");
		addMenuButton(playButton);
		
		playButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(subSceneLevelChooser);
				
			}
		});
		
	}
	
	private void createScoreButton() {
		Buttons scoreButton = new Buttons("HIGH-SCORE");
		addMenuButton(scoreButton);
		
		scoreButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				showSubScene(subSceneScore);
				
			}
		});
	}
	
	private void createHelpButton() {
		Buttons helpButton = new Buttons("HELP");
		addMenuButton(helpButton);
		
		helpButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(subSceneHelp);
				
			}
		});
	}
	
	private void createExitButton() {
		Buttons exitButton = new Buttons("EXIT");
		addMenuButton(exitButton);
		
		//implementing exit logic
		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();
				
			}
		});
	}


	private void createBackground() {
		Image backgroundImage= new Image("view/ViewAssets/dark-blue-watercolor.jpg",600,800,true,false);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,null);
		mainPane.setBackground(new Background(background));
		
	}
	
	private void createLogo() {
		ImageView logo= new ImageView("view/ViewAssets/title.png");
		logo.setFitHeight(200);
		logo.setFitWidth(350);
		logo.setPreserveRatio(true);
		logo.setLayoutX(125);
		logo.setLayoutY(15);
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow(4.0, Color.WHITE));
										
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
		});
		
		mainPane.getChildren().add(logo);
	}
	
	private void createIcon() {
		ImageView icon = new ImageView("view/ViewAssets/green-frogger.png");
		
		icon.setFitHeight(90);
		icon.setFitWidth(90);
		icon.setPreserveRatio(true);
		icon.setLayoutX(250);
		icon.setLayoutY(110);
		
		icon.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				icon.setEffect(new DropShadow(4.0, Color.WHITE));
										
			}
		});
		
		icon.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				icon.setEffect(null);
				
			}
		});
		mainPane.getChildren().add(icon);
	}
	
	
	
	
	
	
	
	
	
}
