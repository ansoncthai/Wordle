package application;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class StatsController {
	@FXML
    private BarChart<Number, String> barChart;
	
	@FXML
    private GridPane Grid;
	
	private String Guess1;
	private String Guess2;
	private String Guess3;
	private String Guess4;
	private String Guess5;
	private String Guess6;
	private String totalPlays;
	private String totalFails;
	private String currentStreak;
	private String maxStreak;
	private String mostRecent;
	private String won;
	
	
	public void initialize() {
		//barChart
		
		String fileName = "C:\\Users\\anson\\eclipse-workspace\\Wordle\\src\\application\\Results.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        	String line;
            line = br.readLine();
            Guess1 = br.readLine();
            line = br.readLine();
            Guess2 = br.readLine();
            line = br.readLine();
            Guess3 = br.readLine();
            line = br.readLine();
            Guess4 = br.readLine();
            line = br.readLine();
            Guess5 = br.readLine();
            line = br.readLine();
            Guess6 = br.readLine();
            line = br.readLine();
            totalPlays = br.readLine();
            line = br.readLine();
            totalFails = br.readLine();
            line = br.readLine();
            currentStreak = br.readLine();
            line = br.readLine();
            maxStreak = br.readLine();
            line = br.readLine();
            mostRecent = br.readLine();
            line = br.readLine();
            won = br.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
		
        //4 grid stats
        BorderPane borderPane1 = (BorderPane) Grid.getChildren().get(0 * Grid.getColumnCount() + 0);
        BorderPane borderPaneInner1 = (BorderPane)borderPane1.getCenter();
    	Text centerNode1 = (Text) borderPaneInner1.getCenter();
    	centerNode1.setText(totalPlays);
        
    	BorderPane borderPane2 = (BorderPane) Grid.getChildren().get(0 * Grid.getColumnCount() + 1);
        BorderPane borderPaneInner2 = (BorderPane)borderPane2.getCenter();
    	Text centerNode2 = (Text) borderPaneInner2.getCenter();
    	int winPercentage = ((Integer.valueOf(totalPlays) - Integer.valueOf(totalFails))*100) /Integer.valueOf(totalPlays);
    	centerNode2.setText(String.valueOf(winPercentage));
        
    	BorderPane borderPane3 = (BorderPane) Grid.getChildren().get(0 * Grid.getColumnCount() + 2);
        BorderPane borderPaneInner3 = (BorderPane)borderPane3.getCenter();
    	Text centerNode3 = (Text) borderPaneInner3.getCenter();
    	centerNode3.setText(currentStreak);
        
    	BorderPane borderPane4 = (BorderPane) Grid.getChildren().get(0 * Grid.getColumnCount() + 3);
        BorderPane borderPaneInner4 = (BorderPane)borderPane4.getCenter();
    	Text centerNode4 = (Text) borderPaneInner4.getCenter();
    	centerNode4.setText(maxStreak);
        
        
        
		XYChart.Series<Number, String> series = new XYChart.Series<>();
		
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess6), "6"));
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess5), "5"));
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess4), "4"));
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess3), "3"));
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess2), "2"));
		series.getData().add(new XYChart.Data<>(Integer.valueOf(Guess1), "1"));
		
		barChart.getData().add(series); // Add series to the chart
		
		// Set color for the series
		for (int i = series.getData().size() - 1; i >= 0; i--) {
		    XYChart.Data<Number, String> data = series.getData().get(i);
		    Node node = data.getNode();
		    if (node != null) {
		    	if(Integer.valueOf(mostRecent) == 6-i && Integer.valueOf(won) == 1) {
		    		node.setStyle("-fx-bar-fill: #6ca965;");
		    	}
		    	else {
		    		node.setStyle("-fx-bar-fill: #787c7f;");
		    	}

		    }
		}

	}
	
	

	
	
}
    