package model;

import java.util.ArrayList;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;
import model.ActorAssets.ActorResource;


public class Turtle extends MovingActor{
	
	ActorResource actorObject =ActorResource.TURTLE;
	ArrayList<MovingActor> objects;
	private int spacing= 100;
	private double speed=0;
	boolean bool = true;
	
	Image turtle1 = new Image(actorObject.getUrlAnimation1(), actorObject.getWidth(), actorObject.getHeight(), true, true);
	Image turtle2 = new Image(actorObject.getUrlAnimation2(), actorObject.getWidth(), actorObject.getHeight(), true, true);
	Image turtle3 = new Image(actorObject.getUrlAnimation3(), actorObject.getWidth(), actorObject.getHeight(), true, true);
	
	KeyFrame firstAnim  = new KeyFrame(Duration.seconds(0.5), new KeyValue(this.imageProperty(),turtle1));
	KeyFrame secondAnim = new KeyFrame(Duration.seconds(1), new KeyValue(this.imageProperty(),turtle2));
	KeyFrame thirdAnim  = new KeyFrame(Duration.seconds(1.5), new KeyValue(this.imageProperty(),turtle3));
	
	Timeline timeline = new Timeline();
	
	public void act(long now, ArrayList<MovingActor> objects,double newSpeed) {
		
		double firstXPos=0;
		this.objects=objects;
		speed=newSpeed;
				
		showTurtleAnimation();

		if(speed>0) {
			firstXPos= getFirstXPositionRight();
		}
		else {
			firstXPos= getFirstXPositionLeft();
		}
		move(speed , 0);
		if (getX()>600 && speed>0)
			if(firstXPos<0) {
				setX(firstXPos-actorObject.getSize()-spacing);
			}else {
				setX(-actorObject.getSize()-spacing);
			}
				
			
		if (getX()<-300 && speed<0)
			if(firstXPos<0) {
				setX(600+firstXPos+actorObject.getSize()+spacing);
			}else {
				setX(600+actorObject.getSize()+spacing);
			}
	}
	
	/**
	 * This method is used to display the Turtle animation.
	 */
	private void showTurtleAnimation() {		
		timeline.getKeyFrames().addAll(firstAnim,secondAnim,thirdAnim); 
		timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
	}
	/**
	 * This constructor creates Turtle objects 
	 * @param xpos initial position of object on the x axis.
	 * @param ypos position of the object on y axis (the row in which the object will fit into). 
	 * @param s initial speed of the object.
	 */
	public Turtle(int xpos, int ypos, int s) {
		
		setX(xpos);
		setY(ypos);
		this.speed = s;
		setImage(turtle3);
	}
	
	/**
	 * This method is to get where the first turtle is created in the x axis.
	 * @return position of the first turtle
	 */
	
	public double getFirstXPositionRight() {
		double firstXPos=0;
		for(MovingActor actor: objects) {
			if(getY()==actor.getY() && (firstXPos>actor.getX())) {
				firstXPos=actor.getX();
			}
		}
		return firstXPos;
	}
	
	/**
	 * This method is to get where the first turtle is created in the x axis.
	 * @return position of the first turtle
	 */
	public double getFirstXPositionLeft() {
		double firstXPos=0;
		for(MovingActor actor: objects) {
			if(getY()==actor.getY() && (firstXPos<actor.getX())) {
				firstXPos=actor.getX();
			}
		}
		return firstXPos;
	}
	
	
	public double getSpeed() {
		
		return this.speed;
	}
	
	
	
	
	
}
