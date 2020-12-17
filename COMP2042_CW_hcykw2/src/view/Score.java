package view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 *this class manages score in particular high scores and save it in a file
 * @author keitaro
 *
 */
public class Score {
	
	private static final String HIGH_SCORE_FILE="src/view/HIGHSCORES.txt";
	
	private ArrayList<String> highScoresList= new ArrayList<String>();
	
	public void readHighScores() {
		
		    try {
		        File scoreFile = new File(HIGH_SCORE_FILE);
		        if (!scoreFile.exists()) {
		          scoreFile.createNewFile();
		          System.out.println("File created: " + scoreFile.getName());
		          initializeFile();
		        } else {
		          System.out.println("File already exists.");
		          
		        }
		      } catch (IOException e) {
		        System.out.println("An error occurred.");
		        e.printStackTrace();
		      }
		    highScoresList = (ArrayList<String>) readFileIntoList();
		    
		    if(highScoresList.isEmpty()) {
		    	initializeFile();
		    }
		    
		}
	
	public void initializeFile() {
		 try {
		      FileWriter myWriter = new FileWriter(HIGH_SCORE_FILE);
		      myWriter.write("Nobody:0\nNobody:0\nNobody:0");
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
	}

	public boolean isNewHighScore(int score, int numOfLevel) {
		if(score > Integer.parseInt((highScoresList.get(numOfLevel).split(":")[1]))){
			return true;
		}	
		else return false;
	}
	
	
	public void replaceLine(String replaceWithHighScore, int levelofHighScore) {
		  List<String> listOfLines =Collections.emptyList();
		  listOfLines=readFileIntoList();
		  listOfLines.set(levelofHighScore,replaceWithHighScore);

		  
		  try {
		      FileWriter myWriter = new FileWriter(HIGH_SCORE_FILE);
		      myWriter.write(listOfLines.get(0)+"\n"+listOfLines.get(1)+"\n"+listOfLines.get(2));
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
	}
	
	public static List<String> readFileIntoList(){
		List<String> lines= Collections.emptyList();
		try {
			lines= Files.readAllLines(Paths.get(HIGH_SCORE_FILE),StandardCharsets.UTF_8 );
		} catch (IOException e) {
			System.out.println("Can't read file into list");
		}
		return lines;
	}   
	
	public ArrayList<String> getHighScoresList(){
		return highScoresList;
	}
	
	public int getHighScoreForLevel(int numOfLevel) {
		return Integer.parseInt((highScoresList.get(numOfLevel).split(":")[1])) ;
	}	
}
