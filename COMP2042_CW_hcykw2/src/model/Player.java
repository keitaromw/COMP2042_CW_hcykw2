package model;

/**
 * This abstract class extends the Intersect class and has an abstract act method. this class is use to make a main player.
 * @author keitaro
 *
 */
public abstract class Player extends Intersect{
	
	/**
	 * Method used in order to influence Player according to the frame at the moment in time.
	 * @param now current frame of the animation. 
	 */
	public abstract void act(long now);
	
	
}
