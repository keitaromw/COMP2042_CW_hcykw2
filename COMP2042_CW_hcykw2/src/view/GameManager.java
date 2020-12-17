package view;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Digit;
import model.Frogger;
import model.Intersect;
import model.MovingActor;
import model.MenuModels.Texts;
import model.MenuModels.Levels;

/**
 * This class is responsible for rendering the view of the program.
 * It makes a World Object, a Game Setter Object and then makes these two work together in a timer. 
 * @author keitaro
 * 
 *
 */
public class GameManager {
	
	private World background;
	private Scene gameScene;
	private Stage gameStage;
	AnimationTimer timer;
	private MediaPlayer mediaPlayer;
	private Frogger frogger;
	private ArrayList<Intersect> objects;
	private String newHighScore; //used if the user sets a new High Score 
	private int numOfLevel;

	
	private boolean speedChange;
	ImageView bonusImageView;
	Label bonusLabel;
	TextInputDialog textDialog;
	Score scoreHandler= new Score();
	
	private static final int GAME_WIDTH= 600;
	private static final int GAME_HEIGHT=800;
	private static final String BACKGROUND_IMAGE="file:src/view/ViewAssets/FrogBackground.png";
	private static final String MUSIC_URL= "src/view/ViewAssets/Frogger Main Song Theme (loop).mp3";
	private static final String Bonus_Image= "file:src/view/ViewAssets/BonusRound.png";
	
	private Stage menuStage;
	private String playerName;
	
	public GameManager() {
		initializeStage();
		createBackground();
	}
	
	/**
	 * The initializeStage() method is used to set up the Scene 
	 */

	private void initializeStage() {
		background= new World();
		gameScene = new Scene(background, GAME_WIDTH, GAME_HEIGHT);
		gameStage = new Stage();
		gameStage.setScene(gameScene);
		gameStage.setResizable(false);
	}
	
	/**
	 * The createBackground method sets the background Image in the world object.
	 */
	
	private void createBackground() {
		ImageView backgroundImage= new ImageView();
		backgroundImage.setImage( new Image(BACKGROUND_IMAGE,600,800,true,false));
		background.add(backgroundImage);
		}
	
	/**
	 * This method is used to show and hide scenes/stage.
	 * @param menuStage is used to hide the Stage specified here
	 * @param choosenLevel is to create game according to the level
	 */
	
	public void createNewGame(Stage menuStage, Levels choosenLevel) {
		this.menuStage=menuStage;
		this.menuStage.hide();
		scoreHandler.readHighScores();
		createGameAccordingToLevel(choosenLevel);
		gameStage.show();
	}
	
	/**
	 * Calls startGame method according to level chosen by the user.
	 * @param choosenLevel specifies which level has been chosen by the user
	 */
	
	private void createGameAccordingToLevel(Levels choosenLevel) {
		switch (choosenLevel.getLevel()) {
		case "LEVEL1":
			numOfLevel=0;
			startGame(3,3,1,2,3,3,3,0,false);
			break;
		case "LEVEL2":
			numOfLevel=1;
			startGame(3,4,2,3,3,3,3,1,false);
			break;
		case "LEVEL3":
			numOfLevel=2;
			startGame(4,4,3,3,4,4,4,2,true);
			break;
		default: System.out.println("ERROR in choosing levels");
			break;
		}
		
	}
/**
 * Method is used to create Game according to the chosen level. 
 * @param numOfLogs number of logs 
 * @param numOfSlowCars number of slow cars 
 * @param numOfFastCars number of fast cars 
 * @param numOfLargeTrucks number of Large trucks 
 * @param numOfSmallTrucks number of small trucks 
 * @param numOfTurtles number of Turtles 
 * @param numOfWetTurtles number of WetTurtles 
 * @param numOfCrocodiles number of Crocodiles 
 * @param CrocHead will there be a CrocHead in the game or not
 */
	

	private void startGame(int numOfLogs, int numOfSlowCars,int numOfFastCars,int numOfLargeTrucks,
			int numOfSmallTrucks, int numOfTurtles,int numOfWetTurtles, int numOfCrocodiles, boolean CrocHead) {
			
			GameSet game = new GameSet(numOfLogs,numOfSlowCars,numOfFastCars,numOfLargeTrucks,
					numOfSmallTrucks,numOfTurtles,numOfWetTurtles,numOfCrocodiles,CrocHead);
			frogger=(Frogger) game.getFrogger();
			objects= game.getArrayList();
			setObjectsToBackground();
			createLabels();
			DisplayHighScore(scoreHandler.getHighScoreForLevel(numOfLevel));
			makeBonusRound();
			createTimer();
			
			playMusic();
			timer.start();
			createNameDialog();
	}
	/**
	 * For the user to enter their name.
	 */
	private void createNameDialog() {
		textDialog= new TextInputDialog();
		
		textDialog.setTitle("Enter Name:");
		textDialog.setGraphic(null);
		textDialog.setHeaderText(null);
		textDialog.getDialogPane().setContentText("Name");
		
		@SuppressWarnings("unused")
		Optional<String> result =textDialog.showAndWait();
		
		TextField input = textDialog.getEditor();
		
		if(input.getText() !=null && input.getText().toString().length() != 0) {
			playerName=input.getText().toString();
		}
		else {
			playerName="@@@@";
			System.out.println("No Input");
		}
		
	}

	/**
	 * This method is used to set Objects 
	 */
	private void setObjectsToBackground() {
		for(int i=0; i<objects.size();i++) {
			background.add(objects.get(i));
		}
	}
	/**
	 * Is used to create the Animation Timer. 
	 * In the timer's handle method it is checked whether the game is over i.e. the user has died three times
	 * Method also checks if stage is ended, if it is it calls showSpeedChange method, it also increases the speed for the next stage of the level.
	 */
		
	public void createTimer() {
        timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
            	int speedMultiplier=1;
            	double speed=1;
            	if(frogger.isGameOver()) {
            		
            		System.out.print("GAME OVER!");
            		stop();
            		if(scoreHandler.isNewHighScore(frogger.getPoints(), numOfLevel)) {
            			newHighScore=playerName+ ":"+ frogger.getPoints();
            			scoreHandler.replaceLine(newHighScore,numOfLevel);
            		}
            		else {
            		createAlertBox();
            		}
            	}
            	ArrayList<MovingActor> actors = (ArrayList<MovingActor>) background.getObjects(MovingActor.class);
                for (MovingActor anActor: actors) {
                	speed=anActor.getSpeed();
                	anActor.act(now,actors,speed);
                }
                frogger.act(now);
            	if (frogger.changeScore()) {
            		setNumber(frogger.getPoints());
            	}           	
            	if(frogger.hasStageEnded()) {
            		System.out.println("Stage Ended");
            		speedMultiplier+=1;
            		System.out.printf("The speed has increased by %d", speedMultiplier);
            		speedChange=true;
            		if(hasSpeedChanged()) {
            			System.out.println("Speed change");
            			showBonusRound();
            		}
            		for (MovingActor anActor: actors) {
                     	speed=anActor.getSpeed()*speedMultiplier;
                     	anActor.act(now, actors,speed);
                     }
            		if(speedMultiplier== 3) {
            			stop();
            		}
            	}
            }

			
        };
    }
	
	/**
	 * This method creates an AlertBox which is displayed after the Game ends. It calls for the endGame method.
	 */
	private void createAlertBox() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Nice Job");
		alert.getDialogPane().setMaxWidth(400);
		alert.setGraphic(null);
		alert.setHeaderText(null);
		alert.setOnHidden(evt -> endGame());
		alert.setContentText("Nice Try");
		alert.show();
		
	}
	/**
	 * This method closes the gameStage and shows the menuStage again.
	 */
	private void endGame() {
		mediaPlayer.stop();
		menuStage.show();
		gameStage.close();
		
	}

	/**
	 * Method checks boolean variable speed change.
	 * @return if speedChange is true return true else return false
	 */
	 public boolean hasSpeedChanged() {
	    	if(speedChange) {
	    		speedChange=false;
	    		return true;
	    	}
	    	else 
	    		return false;
	    }
	 /**
	  * Makes bonus round, adds it to the World Object and also sets its visibility to false initially.
	  */
	
	private void makeBonusRound() {
		bonusImageView =new ImageView(new Image(Bonus_Image, 585,140,true,true));
		bonusLabel =new Label("",bonusImageView);
		bonusLabel.setLayoutX(100);
		bonusLabel.setLayoutY(350);
		background.add(bonusLabel);
		bonusLabel.setVisible(false);
	}
	/**
	 * Stops the timer, has a transition that flashes the speedChange label on screen.
	 * After transition finishes it starts the timer again from the point it left off and sets visibility of the speedLabel to false.
	 */
	
	private void showBonusRound() {
		timer.stop();
		bonusLabel.setVisible(true);
		FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), bonusLabel);
		
		fadeTransition.setFromValue(1); 
		fadeTransition.setToValue(0.0);
		 
		fadeTransition.play();
		fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				bonusLabel.setVisible(false);
				timer.start();
			}
		});		
	}
	
	/**
	 * Plays background Music by using MediaPlayer and Media Objects.
	 */

	public void playMusic() {
			String musicFile = MUSIC_URL;   
			Media sound = new Media(new File(musicFile).toURI().toString());
			mediaPlayer = new MediaPlayer(sound);
			mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
		    mediaPlayer.play();
		}


	/**
	 * Method shows points 
	 * @param n is the current score of the player
	 */
    public void setNumber(int n) {
    	int shift = 0;
    	while (n > 0) {
    		  int d = n / 10;
    		  int k = n - d * 10;
    		  n = d;
    		  background.add(new Digit(k, 30, 570 - shift, 760));
    		  shift+=30;
    		}
    }
	/**
	 * Displays the digits of the HighScore 
	 * @param highScore is the HighScore received for the particular level chosen by the user
	 */
    private void DisplayHighScore(int highScore) {
    	System.out.println("This is the value of n"+highScore);
    	int shift = 0;	
    	if(highScore==0) {
    		for(int i=0;i<4;i++) {
    			background.add(new Digit(0,30,300-shift,760));
    			shift+=30;
    		}
    		shift=0;
    	}
    	else {
    	while (highScore > 0) {
    		  int d = highScore / 10;
    		  int k = highScore- d * 10;
    		  highScore = d;
    		  background.add(new Digit(k, 30, 300 - shift, 760));
    		  shift+=30;
    		}
    	}
    }
	
    /**
     * Method creates HighScore and Points Labels 
     */
	private void createLabels() {
	Texts highScore = new Texts("HIGH-SCORE:", false);
	highScore.setLayoutX(40);
	highScore.setLayoutY(760);
	highScore.setTextFill(Color.web("#f55353",0.8));
	background.add(highScore);
	
	Texts points = new Texts("SCORE:", false);
	points.setLayoutX(370);
	points.setLayoutY(760);
	points.setTextFill(Color.web("#f55353",0.8));
	background.add(points);
	}
	
	
	
	
    
}

	