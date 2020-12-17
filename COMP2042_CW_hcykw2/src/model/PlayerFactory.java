package model;

/**
 * This PlayerFactory class can be used to extend the game by adding in new Player with other function.
 * @author keitaro
 *
 */
public class PlayerFactory {
	
	/**
	 * Creates a main player.
	 * @param mainActor Is an ActorEnum object
	 * @return mainPlayer
	 */
	public static Player createPlayer(ActorEnum mainActor) {
		
		Player mainPlayer=null;
		switch(mainActor) {
		case FROGGER: 
			mainPlayer= new Frogger();
			break;
		default:
			mainPlayer= null;
		}
		return mainPlayer;
	}
}
