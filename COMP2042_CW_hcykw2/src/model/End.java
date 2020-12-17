package model;


import javafx.scene.image.Image;
/**
 * Class is used in order to displays ends or the goal of player on the screen. This class extends the Intersect class because it interacts with frogger
 * object.  
 * @author KMW
 *
 */

public class End extends Intersect{
	private boolean activated = false;
	
	private static final String END_URL="file:src/model/ActorAssets/End.png";
	private static final String SET_END_URL="file:src/model/ActorAssets/FrogEnd.png";
	
	public End(int x, int y) {
		setX(x);
		setY(y);
		setImage(new Image(END_URL, 60, 60, true, true));
	}
	
	/**
	 * Method used to set image of the End ImageView to show that it is filled.
	 */
	public void setEnd() {
		setImage(new Image(SET_END_URL, 70, 70, true, true));
		activated = true;
	}
	/**
	 * Method used in order to show that the End is not filled yet. 
	 */
	public void unsetEnd() {
		setImage(new Image(END_URL, 60, 60, true, true));
		activated=false;
	}
	/**
	 * Method checks whether end object is activated or not.
	 * @return returns the value of the activated field.
	 */
	public boolean isActivated() {
		return activated;
	}

	

}
