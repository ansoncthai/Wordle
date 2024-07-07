# Wordle
Recreated the viral New York Times game “Wordle” in JavaFX (full stack)
Includes implementing loading, random word selection, file reading/writing, correct game logic, error messages, statistics screen while saving score across multiple games, and GUI with the help of Scene Builder

https://youtu.be/zSfZRidVZqc

load/save/logs are not implemented
the program reads and writes directly to the grid. It is logically equivalent to a 2d matrix.

# Sample.fxml
Contains GUI for main wordle page

# Stats.fxml
Contains GUI for stats page

# application.css
Contains all used styles

# Main.java
main - no alterations

# SampleController.java
public void initialize() - initializes the board and helps clear previous session. All board icons and keyboard buttons are
set to their default styles. Pushes all button members into ArrayList<Button> Buttons for ease of access.
Also picks a random number using java.util.Random. Reads from Word.txt and picks the word at the line of the random number.

public void press(ActionEvent e) - handles all button press events. calls appropriate functions for back, enter, and reset. 
Sets the letters on the board if a letter button is pressed.

public void enter() - creates a local variable userWord to represent the user's word. Checks if it is valid by calling isValidWord(userWord).
If not, call popUp(). If yes, call checkWord(userWord).

public boolean isValidWord(String userWord) - checks if the word is in the word list Word.txt. Ex: QQQQQ is not a word Returns True/False.

public void checkWord(String userWord) - checks userWord against the random word "targetWord". Checks for green letters which match letter and index.
Checks for yellow which match letter but not index, but also needs to consider handling yellows in the case of double letters.
Gray for everything else. Calls setColors(). Also calls popUp() if you have reached the 6th word without guessing correctly. 
If user won, call stats(won).

public void setColors(ArrayList<Character> green, ArrayList<Character> yellow, ArrayList<Character> gray)
sets colors of the correct green, yellow, gray tiles on the board

private void popUp(String message) - creates a popup of containing message.

private void stats(boolean won) - opens Results.txt. Reads it to a string while modifying accordingly. Writes string back into Results.txt.
Also loads in Stats.fxml.

private void reset() - sets colIndex = 0, rowIndex = 0, and initialize(). initialize() wipes the board so it already resets.

# StatsController.java
public void initialize() - reads in Results.txt. Updates statistics in the UI. Adds nodes to barChart to display.
Assigns appropriate styles.
