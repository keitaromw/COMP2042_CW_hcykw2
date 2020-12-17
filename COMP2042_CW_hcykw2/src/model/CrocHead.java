package model;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;
import model.ActorAssets.ActorResource;

public class CrocHead extends MovingActor{
	
	ArrayList<Integer> xPosition =new ArrayList<>();
	ActorResource oBj;
	
	private double speed;
	@Override
	
	public void act(long now,ArrayList<MovingActor> objects,double newSpeed) {
		this.speed= newSpeed;
		this.setVisible(false);
		move(0,speed);
		this.setVisible(true);
		if(getY()>65*2) {
			setY(65);
			setX(getRandomElement());
			this.setVisible(false);
			
		 }
		
	}
	/**
	 * Constructor used to initialize the object
	 * @param object gets the image from ActorResources
	 * @param size size of the object
	 * @param xpos position of the ImageView on the x axis
	 * @param ypos position of the ImageView on the y axis
	 * @param s get the speed
	 */
	public CrocHead(ActorResource object,int size, int xpos, int ypos, double s) {
		this.oBj= object;
		setImage(new Image(oBj.getObject(), size,size, true, true));
		setX(xpos);
		setY(ypos);
		this.speed = s;	
		addElementsToList();
	}
	
	private int getRandomElement() 
    { 
        Random rand = new Random(); 
        return xPosition.get(rand.nextInt(xPosition.size())); 
    } 
	
	private void addElementsToList() {
		 int xpos=12;
		for(int i=0; i<5;i++) {
			xPosition.add(xpos);
			xpos+=128;
		}
	}

	@Override
	public double getSpeed() {
		return this.speed;
	}


	
	

}
