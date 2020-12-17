package model.ActorAssets;

public enum ActorResource {
	
	FROGGER("file:src/model/ActorAssets/froggerUp.png",
			"file:src/model/ActorAssets/froggerDown.png",
			"file:src/model/ActorAssets/froggerRight.png",
			"file:src/model/ActorAssets/froggerLeft.png",
			40),
	
	FROGGERMOVE("file:src/model/ActorAssets/froggerUpJump.png",
			"file:src/model/ActorAssets/froggerDownJump.png",
			"file:src/model/ActorAssets/froggerRightJump.png",
			"file:src/model/ActorAssets/froggerLeftJump.png",
			40),
	
	LOG("file:src/model/ActorAssets/logs.png",300),
	LOG2("file:src/model/ActorAssets/log2.png",150),
	
	TURTLE("file:src/model/ActorAssets/TurtleAnimation1.png",
			"file:src/model/ActorAssets/TurtleAnimation2.png",
			"file:src/model/ActorAssets/TurtleAnimation3.png",130,130),
	
	WETTURTLE("file:src/model/ActorAssets/TurtleAnimation1.png",
			"file:src/model/ActorAssets/TurtleAnimation2Wet.png",
			"file:src/model/ActorAssets/TurtleAnimation3Wet.png",
			"file:src/model/ActorAssets/TurtleAnimation4Wet.png",130,130),
	
	CAR("file:src/model/ActorAssets/car1Left.png",
			"file:src/model/ActorAssets/car1Right.png",50,50),
	
	TRUCK1("file:src/model/ActorAssets/truck1Left.png",
			"file:src/model/ActorAssets/truck1Right.png",120,120),
	TRUCK2("file:src/model/ActorAssets/truck2Left.png",
			"file:src/model/ActorAssets/truck2Right.png",200,200),
	
	CROC("file:src/model/ActorAssets/croc.png",
			"file:src/model/ActorAssets/croc2.png",80,80),
	
	CROCHEAD("file:src/model/ActorAssets/crochead.png",65),
	
	CARDEATH("file:src/model/ActorAssets/cardeath1.png",
			"file:src/model/ActorAssets/cardeath2.png",
			"file:src/model/ActoResources/cardeath3.png"),
	
	WATERDEATH("file:src/model/ActorAssets/waterdeath1.png",
			"file:src/model/ActorAssets/death/waterdeath2.png",
			"file:src/model/ActorAssets/waterdeath3.png",
			"file:src/model/ActorAssets/waterdeath4.png");
	
	
	
	
	int size;
	int width;
	int height;
	String objectUrl;
	String urlUp;
	String urlDown;
	String urlLeft;
	String urlRight;
	String urlAnimation1;
	String urlAnimation2;
	String urlAnimation3;
	String urlAnimation4;
	
	
	private ActorResource(String urlUp, String urlDown,String urlRight, String urlLeft, int size) {
		this.urlUp= urlUp;
		this.urlDown= urlDown;
		this.urlRight= urlRight;
		this.urlLeft=urlLeft;
		this.size=size;
		
	}
	
	private ActorResource(String objectUrl, int size) {
		this.objectUrl=objectUrl;
		this.size=size;
	}
	
	private ActorResource(String animation1, String animation2, String animation3, int height,int width) {
		this.urlAnimation1= animation1;
		this.urlAnimation2= animation2;
		this.urlAnimation3= animation3;
		this.height= height;
		this.width= width;
		
	}
	
	private ActorResource(String animation1, String animation2, String animation3, String animation4, int height, int width) {
		this.urlAnimation1= animation1;
		this.urlAnimation2= animation2;
		this.urlAnimation3= animation3;
		this.urlAnimation4= animation4;
		this.height= height;
		this.width= width;
		
	}
	private ActorResource(String urlRight, String urlLeft, int height, int width) {
		this.urlRight =urlRight;
		this.urlLeft= urlLeft;
		this.height= height;
		this.width= width;
	}
	
	private ActorResource(String animation1, String animation2, String animation3) {
		this.urlAnimation1= animation1;
		this.urlAnimation2= animation2;
		this.urlAnimation3= animation3;
			
	}
	private ActorResource(String animation1, String animation2, String animation3, String animation4) {
		this.urlAnimation1= animation1;
		this.urlAnimation2= animation2;
		this.urlAnimation3= animation3;
		this.urlAnimation4= animation4;
	}
	
	public int getSize() {
		return this.size;
	}
	
	public int getWidth() {
		return this.width;
	}
	
	public int getHeight() {
		return this.height;
	}
	
	
	public String getUrlUp() {
		return this.urlUp;
	}
	
	public String getUrlDown() {
		return this.urlDown;
	}
	
	public String getUrlLeft() {
		return this.urlLeft;
	}
	
	public String getUrlRight() {
		return this.urlRight;
	}
	
	public String getUrlAnimation1() {
		return this.urlAnimation1;
	}
	
	public String getUrlAnimation2() {
		return this.urlAnimation2;
	}
	
	public String getUrlAnimation3() {
		return this.urlAnimation3;
	}
	
	public String getUrlAnimation4() {
		return this.urlAnimation4;
	}
	
	public String getObject() {
		return this.objectUrl;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
