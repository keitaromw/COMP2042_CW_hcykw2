package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import model.ActorAssets.ActorResource;



public class Obstacle extends MovingActor{
	
	private double speed=0;
	ArrayList<MovingActor> objects;
	ActorResource actorObject;
	private int spacing=100;
	
	public void act(long now, ArrayList<MovingActor> objects, double newSpeed) {
		double firstXPos=0;
		
		this.objects= objects;
		
		this.speed=newSpeed;
		
		if(speed>0) {
			firstXPos= getFirstXPositionRight();
		}
		else {
			firstXPos= getFirstXPositionLeft();
		}
		move(speed , 0);
		if (getX()>600 && speed>0)
			if(firstXPos<0) {
				setX(firstXPos-actorObject.getWidth()-spacing);
			}else {
				setX(-actorObject.getWidth()-spacing);
			}
				
			
		if (getX()<-300 && speed<0)
			if(firstXPos<0) {
				setX(firstXPos+actorObject.getWidth()+spacing);
			}else {
				setX(600);
			}
	}
	
	/**
	 * The constructor receives an ActorResources Object to set the image.
	 * @param object ActorComponents object, which specifies exactly which image should be set.
	 * @param xpos position of the object on the x axis.
	 * @param ypos position of the object on y axis  
	 * @param s speed of the object
	 */
	public Obstacle(ActorResource object, int xpos, int ypos, int s) {	
		this.actorObject=object;
		setImage(new Image(actorObject.getUrlLeft(),actorObject.getWidth() ,actorObject.getHeight(), true, true));
		setX(xpos);
		setY(ypos);
		this.speed=s;
		//increaseSpeed=this.speed;
		
	}
	
	/**
	 * This method is to get where the first obstacle is created in the x axis.
	 * @return position of the first obstacle
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
	 * This method is to get where the first obstacle is created in the x axis.
	 * @return position of the first obstacle
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
	
	/**
	 * Method used to get what type of obstacle is being created.
	 * @return returns an ActorComponent object
	 */
	public ActorResource getObstacle() {
		return actorObject;
	}
}
