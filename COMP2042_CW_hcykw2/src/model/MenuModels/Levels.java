package model.MenuModels;

public enum Levels {

	LEVEL1("view/ViewResources/levelchooser/Level1.png"),
	LEVEL2("view/ViewResources/levelchooser/Level2.png"),
	LEVEL3("view/ViewResources/levelchooser/Level3.png");
	
	
	private String urlLevel;
	
	private Levels(String urlLevel) {
		this.urlLevel= urlLevel;
	}
	
	public String getUrl() {
		return this.urlLevel;
	}
	
	public String getLevel() {
		return this.name();
	}
}
