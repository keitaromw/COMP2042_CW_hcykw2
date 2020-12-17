package model;

import java.util.ArrayList;

import javafx.scene.image.ImageView;
import view.World;

/**
 * The Intersect class checks whether some object intersects or not.
 * @author KMW
 *
 */
public class Intersect extends ImageView implements ActorInterface{
	/**
	 * The move method sets x and y coordinates depending on parameter.
	 * @param dx distance to be moved on the x axis
	 * @param dy distance to be moved on the y axis
	 */
	
	public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }
	
	public <A extends Intersect> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
	        ArrayList<A> someArray = new ArrayList<A>(); //new array list of type A which is an actor
	        for (A actor: getWorld().getObjects(cls)) {
	            if (actor != this && actor.intersects(this.getBoundsInLocal())) {
	                someArray.add(actor);
	            }
	        }
	        return someArray;
	    }
	
	
	public World getWorld() {
		return (World) getParent();
	}
	
	
}
