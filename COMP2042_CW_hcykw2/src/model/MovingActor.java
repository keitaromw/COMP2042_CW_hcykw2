package model;

import java.util.ArrayList;


public abstract class MovingActor extends Intersect {
	
	/**
	 * The act method is  implemented in order for the object to move each a certain distance each frame. 
	 * @param now current frame of the animation
	 * @param objects ArrayList containing all the MovingActor objects
	 * @param newSpeed current speed 
	 */
	 public abstract void act(long now, ArrayList<MovingActor> objects, double newSpeed);
	 
	 /**
	  * Returns the speed of the objects at that frame in time. 
	  * @return speed of the MovableActor object.
	  */
	 public abstract double getSpeed() ;
	 
}
