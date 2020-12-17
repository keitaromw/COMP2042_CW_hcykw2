package model;

import java.util.ArrayList;

import javafx.scene.image.Image;
import model.ActorAssets.ActorResource;


public class Log extends MovingActor{
	
	private double speed=0;
	ArrayList<MovingActor> objects;
	ActorResource obj;
	private int spacing=100;


	
	public void act(long now,ArrayList<MovingActor> objects,double newSpeed) {
		this.objects= objects;
		this.speed=newSpeed;
		double firstXPos=0;
		
		if(speed>0) {
			firstXPos= getFirstXPositionRight();
		}
		else {
			firstXPos= getFirstXPositionLeft();
		}
		move(speed , 0);
		if (getX()>600 && speed>0)
			if(firstXPos<0) {
				setX(firstXPos-obj.getSize()-spacing);
			}else {
				setX(-obj.getSize()-spacing);
			}
				
			
		if (getX()<-300+spacing && speed<0)
			if(firstXPos<0) {
				setX(600+firstXPos+obj.getSize()+spacing);
			}else {
				setX(600+obj.getSize()+spacing);
			}
			//setX(700);
	}
	
	/**
	 * The constructor receives an ActorResources Object to set the image.
	 * @param object ActorComponents object, which specifies exactly which image should be set.
	 * @param xpos position of the object on the x axis.
	 * @param ypos position of the object on y axis  
	 * @param s speed of the object
	 */

	public Log(ActorResource object, int xpos, int ypos, double s) {
		this.obj = object;
		setImage(new Image(object.getObject(), obj.getSize(),obj.getSize(), true, true));
		setX(xpos);
		setY(ypos);
		this.speed = s;	
	}
	
	/**
	 * This method is to get where the first log object is created in the x axis.
	 * @return position of the first log
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
	 * This method is to get where the first log object is created in the x axis.
	 * @return position of the first log 
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
	public boolean getLeft() {
		return speed < 0;
	}
	
	public double getSpeed() {
		return this.speed;
	}


}
