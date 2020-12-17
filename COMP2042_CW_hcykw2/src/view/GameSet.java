package view;


import java.util.ArrayList;

import model.ActorEnum;
import model.CrocHead;
import model.End;
import model.Intersect;
import model.Log;
import model.Player;
import model.Obstacle;
import model.PlayerFactory;
import model.Turtle;
import model.WetTurtle;
import model.ActorAssets.ActorResource;

/**
 * This class is used to make each of the object that the GameManager Class will be rendered
 * @author keitaro
 *
 */
public class GameSet {
	
	private ArrayList<Intersect> objects= new ArrayList<Intersect>();

	private Player frogger;
	
   
	
	public GameSet(int numOfLogs, int numOfSlowCars,int numOfFastCars,int numOfLargeTrucks,
			int numOfSmallTrucks,int numOfTurtles,int numOfWetTurtles,int numOfCrocodiles,boolean CrocHead) {
		createLog(numOfLogs, numOfLogs, numOfLogs);
		createSlowCars(numOfSlowCars);
		createFastCars(numOfFastCars);
		createLargeTrucks(numOfLargeTrucks);
		createSmallTrucks(numOfSmallTrucks);
		createTurtle(numOfTurtles);
		createWetTurle(numOfWetTurtles);
		createCrocodilesRight(numOfCrocodiles);
		createEnd();
		if(CrocHead) {
			createCrocHeads();
		}
		makeFrogger();
		
	}
	
	/**
	 * This method makes a crocHead object
	 */
	private void createCrocHeads() {
		CrocHead crocHead=new CrocHead(ActorResource.CROCHEAD,65,0,65,6);
		objects.add(crocHead);
	}
	
	/**
	 * Method makes Crocodile Obstacles
	 * @param numOfCrocodiles specifies the number of crocodiles 
	 */
	private void createCrocodilesRight(int numOfCrocodiles) {
		int xpos=0;
		for(int i=0; i<numOfCrocodiles;i++) {
			Obstacle croc = new Obstacle(ActorResource.CROC, xpos, 440,2);
			objects.add(croc);
			xpos+=400;
		}
		
	}

	
	/**
	 * Create End objects and adds it to the objects ArrayList
	 */
	private void createEnd() {
		int xpos= 12; //128++
		for(int i=0;i<5;i++) {
			End end =new End(xpos, 96);
			objects.add(end);
			xpos+=128;
		}	
	}
	
	/**
	 * Makes main player frogger
	 */

	private void makeFrogger() {
		frogger = PlayerFactory.createPlayer(ActorEnum.FROGGER);
		objects.add(frogger);
	}
	/**
	 * Makes Turtle Objects t.
	 * @param turtleNum specifies number of turtle objects 
	 */

	private void createTurtle(int turtleNum) {
		int xpos=0;
		for(int i=0;i<turtleNum;i++) {
			Turtle turtle =new Turtle(xpos, 376, -1);
			objects.add(turtle);
			xpos+=200;
		}	
	}
	
	/**
	 * Create WetTurtle objects 
	 * @param turtleNum specifies number of WetTurles 
	 */
	private void createWetTurle(int turtleNum) {
		int xpos=0;
		for(int i=0;i<turtleNum;i++) {
			WetTurtle turtle =new WetTurtle(xpos, 217, -1);
			objects.add(turtle);
			xpos+=200;
		}
	}
	
	/**
	 * Is a container to create log objects
	 * @param firstQuadNum specifies number of Log objects in first row
	 * @param secondQuad specifies number of Log Objects second row
	 * @param thirdQuadNum specifies number of Log objects third row
	 */
	private void createLog(int firstQuadNum, int secondQuad, int thirdQuadNum) {
		
		createLogForFirstQuad(firstQuadNum);
		
		createLogForSecondQuad(secondQuad);
		
		createLogForThirdQuad(thirdQuadNum);		
		
	}
	/**
	 * Creates Log objects for the first row
	 * @param firstQuadNum specifies number of Log objects to be created for the first row
	 */
	private void createLogForFirstQuad(int firstQuadNum){
		int xpos=0;
		for(int i=0; i < firstQuadNum; i++) {
			Log firstQuadLog = new Log( ActorResource.LOG2, xpos, 166, 0.75);
			objects.add(firstQuadLog);
			xpos+=276;
		}
	}
	
	/**
	 * Creates Log objects for the second row 
	 * @param secondQuadNum specifies number of Log objects to be created for the second row
	 */
	private void createLogForSecondQuad(int secondQuadNum){
		int xpos=0;
		for(int i=0; i < secondQuadNum; i++) {
			Log secondQuadLog = new Log(ActorResource.LOG, xpos, 276, -2);
			objects.add(secondQuadLog);
			xpos+=400;	 
		}
	}
	
	/**
	 * Creates Log objects for the third row 
	 * @param thirdQuadNum specifies number of Log objects to be created for the third row
	 */
	private void createLogForThirdQuad(int thirdQuadNum) {
		int xpos=50;
		for(int i=0; i < thirdQuadNum; i++) {
			Log thirdQuadLog = new Log(ActorResource.LOG2, xpos, 329, 0.75);
			objects.add(thirdQuadLog);
			xpos+=220;
		}
	}
	
	/**
	 * Creates Obstacle objects of slow cars
	 * @param carNum specifies how many Obstacle objects will be created as slow cars
	 */
	private void createSlowCars(int carNum) {
		int xpos=100;
		for (int i=0; i< carNum; i++) {
			Obstacle slowCars = new Obstacle(ActorResource.CAR, xpos, 597, 1);
			objects.add(slowCars);
			xpos +=150;
			}
	
	}
	
	/**
	 * Creates Obstacle objects of fast cars
	 * @param carNum specifies number of Obstacle objects that will be created as fast cars
	 */
	private void createFastCars(int carNum) {
		int xpos=500;
		for (int i=0; i< carNum; i++) {
			Obstacle fastCars = new Obstacle(ActorResource.CAR, xpos, 490, 5);
			objects.add(fastCars);
			xpos +=150;
			}
	}
	
	/**
	 * Creates Obstacle objects small trucks
	 * @param truckNum specifies number of Obstacle objects that will be created as small trucks 
	 */
	private void createSmallTrucks(int truckNum) {
	int xpos=0;
	for (int i=0; i< truckNum; i++) {
		Obstacle smallTruck = new Obstacle(ActorResource.TRUCK1, xpos, 649, 1);
		objects.add(smallTruck);
		xpos +=300;
		}
	}
	
	/**
	 * Creates Obstacle objects of large trucks
	 * @param truckNum specifies number of Obstacle objects that will be created as large trucks 
	 */
	
	private void createLargeTrucks(int truckNum) {
		int xDistance= 10;
		int xpos=ActorResource.TRUCK2.getWidth() + xDistance;
		for (int i=0; i<=5; i++) {
			Obstacle largeTruck = new Obstacle(ActorResource.TRUCK2, xpos, 540, 1);
			objects.add(largeTruck);
			xpos = xpos + ActorResource.TRUCK2.getWidth() + xDistance;
			}
	}
	
	/**
	 * Gets ArrayList that has Actor objects inside it.
	 * @return returns the objects ArrayList 
	 */
	
	public ArrayList<Intersect> getArrayList(){
		return objects;
	}
	
	/**
	 * Returns an Actor
	 * @return return main player frogger
	 */
	public Intersect getFrogger() {
		return frogger;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
