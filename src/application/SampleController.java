package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.lang.reflect.Field;


import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;

//file reading
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class SampleController {
	
	@FXML
    private Button A;
    @FXML
    private Button B;
    @FXML
    private Button C;
    @FXML
    private Button D;
    @FXML
    private Button E;
    @FXML
    private Button F;
    @FXML
    private Button G;
    @FXML
    private Button H;
    @FXML
    private Button I;
    @FXML
    private Button J;
    @FXML
    private Button K;
    @FXML
    private Button L;
    @FXML
    private Button M;
    @FXML
    private Button N;
    @FXML
    private Button O;
    @FXML
    private Button P;
    @FXML
    private Button Q;
    @FXML
    private Button R;
    @FXML
    private Button S;
    @FXML
    private Button T;
    @FXML
    private Button U;
    @FXML
    private Button V;
    @FXML
    private Button W;
    @FXML
    private Button X;
    @FXML
    private Button Y;
    @FXML
    private Button Z;
    @FXML
    private GridPane Grid;
    @FXML
    private Button Back;
    @FXML
    private Button Enter;
    @FXML
    private Button Reset;
    

    private int rowIndex = 0;
    private int colIndex = 0;
    private String targetWord;
    ArrayList<Character> targetArray = new ArrayList<>();
    ArrayList<Button> Buttons = new ArrayList<>();
    
    public void initialize() {
    	//clears grid and colors
    	for(int i = 0; i < 6; i++) {
    		for(int j = 0; j < 5 ; j++) {
    			BorderPane borderPane = (BorderPane) Grid.getChildren().get(i * Grid.getColumnCount() + j);
    	    	Text centerNode = (Text) borderPane.getCenter();
    	    	if(centerNode instanceof Text) {
    			    centerNode.setText(null);
    			}
    	    	borderPane.getStyleClass().remove("green-text-style");
    	    	borderPane.getStyleClass().remove("yellow-text-style");
    	    	borderPane.getStyleClass().remove("gray-text-style");
    	    	borderPane.getStyleClass().remove("gray-text-style");
    	    	centerNode.getStyleClass().remove("white");
    		}
    	}
    	// pushed all buttons into Buttons
    	Class<?> clazz = this.getClass(); 
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            try {
                // Get the field corresponding to the button
                Field field = clazz.getDeclaredField(String.valueOf(letter));
                field.setAccessible(true);
                Button button = (Button) field.get(this);
                Buttons.add(button);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        
        // clears button colors
        for(Button b : Buttons) {
        	b.getStyleClass().remove("green-letterButton-style");
        	b.getStyleClass().remove("yellow-letterButton-style");
        	b.getStyleClass().remove("gray-letterButton-style");
        	b.getStyleClass().add("letterButton-style");
        }
        
    	//extracts random string
    	String fileName = "C:\\Users\\anson\\eclipse-workspace\\Wordle\\src\\application\\Words.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	String line;
            //random number from 0 to 2310 inclusive
        	Random random = new Random();
        	int randomNumber = random.nextInt(2311);
        	
        	//randomNumber = 1626; //test word entry
        	//randomNumber = 2112; //test word putty
        	
            for(int i = 0; i < randomNumber-1; i++) {
            	line = br.readLine();
            }
            
            targetWord = br.readLine();
            char[] tmp = targetWord.toCharArray();
            for (char c : tmp) {
                targetArray.add(c);
            }
            
            System.out.println("Random Number: " + randomNumber + " Random Word: " + targetWord); 
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        
        

    }
    
    public void press(ActionEvent e) {
        if (e.getSource() instanceof Button) {
            Button clickedButton = (Button) e.getSource(); 
            //Back Button
            if(clickedButton.getText().equals("Back")){
            	if(colIndex > 0) {
            		colIndex--;
        			BorderPane borderPane = (BorderPane) Grid.getChildren().get(rowIndex * Grid.getColumnCount() + colIndex);
        	    	Text centerNode = (Text) borderPane.getCenter();
    				centerNode.setText(null);
    				System.out.println("Back Clicked");
            	}
            }
            //Enter Button
            else if(clickedButton.getText().equals("Enter")){
            	if(colIndex < 5) {
            		System.out.println("Not Enough Letters");
            		popUp("Not enough letters");
            	}
            	else if(colIndex == 5) {
            		System.out.println("Enter Clicked");
            		enter();
            	}
            }
            //Reset Button
            else if(clickedButton.getText().equals("Reset")){
            	reset();
            }
            
            
            
            //Letter Buttons
            else if(colIndex >=0 && colIndex < 5) {
            	//sets text in grid after a button is pressed
            	BorderPane borderPane = (BorderPane) Grid.getChildren().get(rowIndex * Grid.getColumnCount() + colIndex);
    	    	Text centerNode = (Text) borderPane.getCenter();
    	    	centerNode.setText(clickedButton.getText());
			    colIndex++;
            }
            
        }
    }

    public void enter() {
    	String userWord = "";
    	for(int i = 0; i < 5; i++) {
    		BorderPane borderPane = (BorderPane) Grid.getChildren().get(rowIndex * Grid.getColumnCount() + i);
	    	Text centerNode = (Text) borderPane.getCenter();
	    	userWord = userWord + centerNode.getText();
	    	
    	}
    	userWord = userWord.toLowerCase();
    	System.out.println(userWord);
    	
    	if(!isValidWord(userWord)) {
    		System.out.println("Not in word list");
    		popUp("Not in word list");
    	}
    	else {
    		checkWord(userWord);
    		rowIndex++;
    		colIndex = 0;
    	}
    	
    }
    
    public boolean isValidWord(String userWord) {
    	String fileName = "C:\\Users\\anson\\eclipse-workspace\\Wordle\\src\\application\\Words.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            for(int i = 0; i < 2310; i++) {
            	line = br.readLine();
            	if(line.equals(userWord)) {
            		return true;
            	}
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    	return false;
    }
	
    public void checkWord(String userWord) {

		char[] tmpWord = userWord.toCharArray();
		ArrayList<Character> userArray = new ArrayList<>();
		
		for(int i = 0; i < 5; i++) {
			userArray.add(tmpWord[i]);
		}
		
		ArrayList<Character> green = new ArrayList<>();
		ArrayList<Character> yellow = new ArrayList<>();
		ArrayList<Character> gray = new ArrayList<>();
		ArrayList<Character> tmp = new ArrayList<>(); //visited marker
		
		//match
		if(userWord.equals(targetWord)){
			System.out.println("Winner!");
    		setColors(targetArray, yellow, gray);
    		popUp("Winner!");
    		stats(true);
    	}
		else {
			for(int i = 0; i < 5; i++) {
				tmp.add(targetArray.get(i));
			}

			//green
			for(int i = 0; i < 5; i++) {
				//green correct letter and position
				if(userArray.get(i) == tmp.get(i)) {
					green.add(userArray.get(i));
					tmp.set(i, '!');
				}
				else {
					green.add(null);
				}
			}
			System.out.println(tmp);
			//yellow
			for(int i = 0; i < 5; i++) {
				if(targetArray.contains(userArray.get(i)) && !targetArray.get(i).equals(userArray.get(i))) {
					boolean v = true;
					for(int j = 0; j < 5; j++) {
						if(targetArray.get(j).equals(userArray.get(i)) && tmp.get(j).equals('!')) {
							if(tmp.contains(userArray.get(i))) {
								break;
							}
							else {
								v = false;
								break;
							}
							
						}
					}
					if(v) {
						yellow.add(userArray.get(i));
						tmp.set(i, '!');
					}
				}
				if(yellow.size() <= i) {
					yellow.add(null);
				}
			}
			//grey not in word
			for(int i = 0; i < 5; i++) {
				if (green.get(i) == null && yellow.get(i) == null) {
					gray.add(userArray.get(i));
					tmp.set(i, '!');
				}
				else {
					gray.add(null);
				}
			}
			System.out.println(green);
			System.out.println(yellow);
			System.out.println(gray);
			System.out.println(tmp);
			setColors(green, yellow, gray);
			if (rowIndex > 4){
				popUp("Failed");
				stats(false);
			}
		}
    }
    
    public void setColors(ArrayList<Character> green, ArrayList<Character> yellow, ArrayList<Character> gray ) {
    	for(int i = 0; i < 5; i++) {
    		BorderPane borderPane = (BorderPane) Grid.getChildren().get((rowIndex) * Grid.getColumnCount() + i);
   	    	Text centerNode = (Text) borderPane.getCenter();
	    	String text = centerNode.getText().toLowerCase();
	    	if(green.get(i) != null) {
	    		borderPane.getStyleClass().add("green-text-style");
	    		centerNode.getStyleClass().add("white");
	    		for(Button b : Buttons) {
	    			if(b.getText().toLowerCase().equals(String.valueOf(green.get(i)))) {
	    				System.out.println("Button: " + b.getText());
	    				b.getStyleClass().clear();
	    				b.getStyleClass().add("green-letterButton-style");
	    			}
	    		}

	    	}
	    	else if(yellow.get(i) != null) {
	    		borderPane.getStyleClass().add("yellow-text-style");
	    		centerNode.getStyleClass().add("white");
	    		for(Button b : Buttons) {
	    			//checks so it doesnt override color
	    			if(b.getText().toLowerCase().equals(String.valueOf(yellow.get(i))) && !b.getStyleClass().contains("green-letterButton-style")) {
	    				System.out.println("Button: " + b.getText());
	    				b.getStyleClass().clear();
	    				b.getStyleClass().add("yellow-letterButton-style");
	    			}
	    		}
	    	}
	    	else if(gray.get(i) != null) {
	    		borderPane.getStyleClass().add("gray-text-style");
	    		centerNode.getStyleClass().add("white");
	    		for(Button b : Buttons) {
	    			//checks so it doesnt override color
	    			if(b.getText().toLowerCase().equals(String.valueOf(gray.get(i))) && !b.getStyleClass().contains("green-letterButton-style") && !b.getStyleClass().contains("yellow-letterButton-style")) {
	    				System.out.println("Button: " + b.getText());
	    				b.getStyleClass().clear();
	    				b.getStyleClass().add("gray-letterButton-style");
	    			}
	    		}
	    	}
    	}
    }
    
    @FXML
    private StackPane rootPane;
    private void popUp(String message) {
        // Create the text
        Text popupText = new Text(message);
        popupText.getStyleClass().add("popup-text");

        // Create a rectangle to serve as the background
        Rectangle background = new Rectangle();
        background.setFill(Color.BLACK); 
        background.setArcWidth(10);
        background.setArcHeight(10);
        
        double longer = 0;
        if(message.equals("Winner!")) {
        	background.setWidth(100);
        	longer += 5;
        }
        else if (message.equals("Failed")) {
        	background.setWidth(100);
        	longer += 5;
        }
        else if (message.equals("Not in word list")) {
        	background.setWidth(175);
        }
        else {
        	background.setWidth(200);
        }
        
        background.setHeight(50); // Adjust height

        StackPane popupPane = new StackPane();
        popupPane.getChildren().addAll(background, popupText);
        rootPane.getChildren().add(popupPane);

        StackPane.setMargin(popupText, new Insets(-500, 0, 0, 0));
        StackPane.setMargin(background, new Insets(-500, 0, 0, 0));

        Timeline timeline = new Timeline();
        //(duration, opacity action, opacity value)
        KeyFrame keyFrame1 = new KeyFrame(Duration.ZERO, new KeyValue(popupPane.opacityProperty(), 0));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.1), new KeyValue(popupPane.opacityProperty(), 1));
        KeyFrame keyFrame3 = new KeyFrame(Duration.seconds(.7), new KeyValue(popupPane.opacityProperty(), 1));
        KeyFrame keyFrame4 = new KeyFrame(Duration.seconds(.7 + longer), new KeyValue(popupPane.opacityProperty(), 1));
        KeyFrame keyFrame5 = new KeyFrame(Duration.seconds(1 + longer), new KeyValue(popupPane.opacityProperty(), 0));
        timeline.getKeyFrames().addAll(keyFrame1, keyFrame2, keyFrame3, keyFrame4, keyFrame5);
        timeline.setOnFinished(event -> rootPane.getChildren().remove(popupPane)); // Remove container after animation

        timeline.play();
    }
    
    
    private void stats(boolean won) {
    	//read and update results
    	String writer = "";
    	int lineIndex = 1;
    	int modify = 0;
    	int currStreak = 0;
    	//read
    	String fileName = "C:\\Users\\anson\\eclipse-workspace\\Wordle\\src\\application\\Results.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	String line;
        	
        	while ((line = br.readLine()) != null) {
                if (lineIndex == (rowIndex + 1) * 2 && won){
                	modify = Integer.valueOf(line) + 1;
                	writer = writer + String.valueOf(modify) + "\n";
                }
                //plays
                else if(lineIndex == 14){
                	modify = Integer.valueOf(line) + 1;
                	writer = writer + String.valueOf(modify) + "\n";
                }
                //fails
                else if(lineIndex == 16 && !won){
                	modify = Integer.valueOf(line) + 1;
                	writer = writer + String.valueOf(modify) + "\n";
                }
                //currentStreak
                else if(lineIndex == 18){
                	if(won) {
                		modify = Integer.valueOf(line) + 1;
                    	writer = writer + String.valueOf(modify) + "\n";
                    	currStreak = modify;
                	}
                	else { //loss
                		writer = writer + "0" + "\n";
                		currStreak = 0;
                	}
                }
                //maxStreak
                else if(lineIndex == 20){
                	if(Integer.valueOf(line) < currStreak) {
                		modify = Integer.valueOf(currStreak);
                    	writer = writer + String.valueOf(modify) + "\n";
                	}
                	else {
                		writer = writer + line + "\n";
                	}
                }
                //most recent
                else if(lineIndex == 22 && won) {
                	writer = writer + String.valueOf(rowIndex + 1) + "\n";
                	
                }
              //most recent
                else if(lineIndex == 24) {
                	if(won) {
                		writer = writer + "1" + "\n";
                	}
                	else {
                		writer = writer + "0" + "\n";
                	}
                }
                else {
                	writer = writer + line + "\n";
                }
                lineIndex++;
            }
        	
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    	
        //write
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
        	bw.write(writer);
        	
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    	
    	
    	//loads window
        try {
            // Load the FXML file for the new window
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Stats.fxml"));
            Parent root = loader.load();
            
            // Create a new stage
            Stage statsStage = new Stage();
            statsStage.initModality(Modality.APPLICATION_MODAL);
            statsStage.setTitle("Stats"); // Set the title of the window
            statsStage.setScene(new Scene(root));
            
            // Show the new window
            statsStage.showAndWait(); // Use showAndWait() to make it modal
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void reset(){
 
    	colIndex = 0;
    	rowIndex = 0;
    	initialize();
    }


}

