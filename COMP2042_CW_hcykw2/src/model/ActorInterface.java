package model;

import view.World;

public interface ActorInterface {
	
	/**
	 * This method is used to get the pane that the actor is in
	 * @return return pane actor is in as a World object 
	 */
	public abstract World getWorld();
	
	/**
	 * A Method to check whether the objects intersect or not. 
	 * @param <A> type of class to check for an interaction
	 * @param cls object of class to check for interaction
	 * @return returns an array that contains objects that intersected with
	 */
	public abstract <A extends Intersect> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls);

} 
