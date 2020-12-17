package model;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;
import model.ActorAssets.ActorResource;


public class Frogger extends Player {

	private static ActorResource frogger=ActorResource.FROGGER;
	private static ActorResource froggerMove= ActorResource.FROGGERMOVE;
	private static final double movementY = 13.5*2; 
	private static final double movementX = 10.5*2; 
	private static final int 	imgSize =frogger.getSize();
	private static final double initialPosX=300;
	private static final double initialPosY=679.8+movementY;
	
	private int points = 0; 
	private boolean noMove = false;
	private boolean changeScore = false;
	private boolean hasStageChanged=false;
	private int Death=0;
	private double w = 800; 
	
	Image imgW1 = new Image(frogger.getUrlUp(), imgSize, imgSize, true, true);
	Image imgA1 = new Image(frogger.getUrlLeft(), imgSize, imgSize, true, true);
	Image imgS1 = new Image(frogger.getUrlDown(), imgSize, imgSize, true, true);
	Image imgD1 = new Image(frogger.getUrlRight(), imgSize, imgSize, true, true);
	Image imgW2 = new Image(froggerMove.getUrlUp(), imgSize, imgSize, true, true);
	Image imgA2 = new Image(froggerMove.getUrlLeft(), imgSize, imgSize, true, true);
	Image imgS2 = new Image(froggerMove.getUrlDown(), imgSize, imgSize, true, true);
	Image imgD2 = new Image(froggerMove.getUrlRight(), imgSize, imgSize, true, true);
	
	ArrayList<End> inter = new ArrayList<>();
	ArrayList<End> activatedEnds =new ArrayList<End>();
	
	
	public Frogger() {
		setFroggerToStart();
		createKeyListner();
	}
	
	/**
	 * This method makes the KeyListeners to catch the hardware key that we use.
	 */
	private void createKeyListner() {
		setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if(noMove) {
				}
				else {
					if (event.getCode() == KeyCode.W) {	            	
		                move(0, -movementY);
		                setImage(imgW2);
		            }
		            else if (event.getCode() == KeyCode.A) {	            	
		            	 move(-movementX, 0);
		            	 setImage(imgA2);
		            }
		            else if (event.getCode() == KeyCode.S) {	            	
		            	 move(0, movementY);
		            	 setImage(imgS2);
		            }
		            else if (event.getCode() == KeyCode.D) {	            	
		            	 move(movementX, 0);
		            	 setImage(imgD2);
		            	
		            }
				}
				
			}
		});
		
		setOnKeyReleased(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (noMove) {}
				else {
				if (event.getCode() == KeyCode.W) {	  
					//agar Y value of the frog is less than screen size
					if (getY() < w) {
						changeScore = true; //than changescore should be true 
						w = getY();
						points+=10; //points should be added by 10
					}
					//always do according to the movement no matter where the frog is at in the screen
	                move(0, -movementY);
	                setImage(imgW1);
	            }
				//otherwise do the normal shit (as soon as key released position frog according to which key was pressed 
	            else if (event.getCode() == KeyCode.A) {	            	
	            	 move(-movementX, 0);
	            	 setImage(imgA1);
	            }
	            else if (event.getCode() == KeyCode.S) {	            	
	            	 move(0, movementY);
	            	 setImage(imgS1);
	            }
	            else if (event.getCode() == KeyCode.D) {	            	
	            	 move(movementX, 0);
	            	 setImage(imgD1);
	            }
	        }
				
			}
		});
		
	}

	
	public void act(long now) {		
		
		froggerInBound();
		
		System.out.println(Death);
		if (getX() == 240 && getY() == 82) {
			
		}
		
		if (getIntersectingObjects(Obstacle.class).size() >= 1) {
			if(getIntersectingObjects(Obstacle.class).get(0).getObstacle().name()=="CROCODILE"){
				deathAnimator(ActorResource.WATERDEATH);
			}else {
			deathAnimator(ActorResource.CARDEATH);
			}
			
		}
		else if (getIntersectingObjects(Log.class).size() >= 1 && !noMove) {
			if(getIntersectingObjects(Log.class).get(0).getLeft())
				move(-2,0);
			else
				move (.75,0);
		}
		else if (getIntersectingObjects(Turtle.class).size() >= 1 && !noMove) {
			move(-1,0);
		}
		else if (getIntersectingObjects(WetTurtle.class).size() >= 1) {
			if (getIntersectingObjects(WetTurtle.class).get(0).isSunk()) {
				deathAnimator(ActorResource.WATERDEATH);
				
			} else {
				move(-1,0);
			}
		}
		else if (getIntersectingObjects(End.class).size() >= 1) {
			inter = (ArrayList<End>) getIntersectingObjects(End.class);
			if (inter.get(0).isActivated()) {
				points-=70;
				deathAnimator(ActorResource.WATERDEATH);
				
				
			}else {
				activatedEnds.add(inter.get(0));
			}
			points+=50;
			changeScore = true;
			w=800;
			inter.get(0).setEnd();
			setFroggerToStart();
		}
		else if (getY()<413){
			deathAnimator(ActorResource.WATERDEATH);	
		}
	}
	/**
	 * Calls the stageEnded method to check if the stage has ended.
	 * @return returns true if stage ended.
	 */
	public boolean hasStageEnded() {
		stageEnded();
		if(hasStageChanged) {
			hasStageChanged=false;
			return true;
		}else {
			return false;
		}
	}
	/**
	 * Method checks whether all Ends object in the game have been filled.
	 */
	private void stageEnded() {
		if(activatedEnds.size()==5) {
			noMove = false;
			changeScore = false;
			Death=0;
			w = 800; 
			for(End ends: activatedEnds ) {
				ends.unsetEnd(); 
			}
			activatedEnds.removeAll(activatedEnds);
			setFroggerToStart();
			createKeyListner();
			hasStageChanged=true;
		}else {
			hasStageChanged=false;
		}		
	}
	
	/**
	 * This method checks how many times frogger has died for the GameSetter class and, returns true if the number of deaths exceeds a certain value.
	 * @return returns whether the integer Death has increased more than a specified value
	 */
	public boolean isGameOver() {
		return Death>33;
	}
	
	/**
	 * Shows death animation
	 * @param object is an ActorComponents Enum object
	 */
	private void deathAnimator(ActorResource object) {
		noMove=true;
		Image anim1= new Image(object.getUrlAnimation1(), imgSize, imgSize, true, true);
		Image anim2= new Image(object.getUrlAnimation2(), imgSize, imgSize, true, true);
		Image anim3= new Image(object.getUrlAnimation3(), imgSize, imgSize, true, true);
		Image anim4= (object==ActorResource.WATERDEATH)?new Image(object.getUrlAnimation4(), imgSize, imgSize, true, true):null;
		Timeline timeLine = new Timeline(
                new KeyFrame(Duration.seconds(0.1), new KeyValue(this.imageProperty(),anim1)),
                new KeyFrame(Duration.seconds(0.4), new KeyValue(this.imageProperty(),anim2)),
                new KeyFrame(Duration.seconds(0.6), new KeyValue(this.imageProperty(),anim3)),
                new KeyFrame(Duration.seconds(0.7), new KeyValue(this.imageProperty(),anim4)),
                new KeyFrame(Duration.seconds(0.2), e -> setFroggerToStart()), 
                new KeyFrame(Duration.seconds(0.9), new KeyValue(this.imageProperty(), new Image(ActorResource.FROGGER.getUrlUp(), imgSize, imgSize, true, true))),
                new KeyFrame(Duration.ZERO,e->Death++)
                );
        timeLine.play();
        timeLine.setOnFinished(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				noMove=false;
				
			}
		});		
       
	}
	
	/**
	 * This method keeps frogger in game screen.
	 */
	private void froggerInBound() {
		if (getY()<0 || getY()>734) {
			setFroggerToStart();
		}
		
		if (getX()<0) {
			move(movementY*2, 0);
		}
		
		 if (getX()>600) { move(-movementY*2, 0); }
		
	}
	/**
	 * Method to allow classes to receive score from the frogger class.
	 * @return returns point according to the point in time 
	 */

	public int getPoints() {
		return points;
	}
	/**
	 * Method to check whether score has changed.
	 * @return returns true if changeScore variable is true else returns false
	 */
	
	public boolean changeScore() {
		if (changeScore) {
			changeScore = false;
			return true;
		}
		return false;
		
	}
	/**
	 * Sets frogger to its initial position with it facing upwards.
	 */
	private void setFroggerToStart() {
		setX(initialPosX);
		setY(initialPosY);
		setImage(imgW1);
	}
	
}
