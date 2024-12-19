package hutchison.grant;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUIDriver extends Application {
	// Hello

	Die d1 = new Die();
	Die d2 = new Die();

	@Override
	public void start(Stage stage) throws Exception {
		VBox vbox = new VBox(10);

		// Create and display the title
		Label title = new Label("Shut The Box");
		vbox.getChildren().add(title);

		HBox tileBox = new HBox(10);

		Button[] tileBtns = new Button[9];
		Tile[] tiles = new Tile[9];

		for (int i = 0; i < tileBtns.length; i++) {
			tileBtns[i] = new Button(String.valueOf(i + 1));
			tiles[i] = new Tile(i + 1);
			tileBtns[i].setStyle("-fx-background-color: lightgray;");

            
            tileBtns[i].setOnAction(e -> {
                Button clickedButton = (Button) e.getSource();
                String currentStyle = clickedButton.getStyle();
                if (currentStyle.contains("lightgray")) {
                    clickedButton.setStyle("-fx-background-color: red;");
                } else if (currentStyle.contains("red")) {
                    clickedButton.setStyle("-fx-background-color: lightgray;");
                }
            });
			tileBox.getChildren().add(tileBtns[i]);
		}


		
			tileBox.setAlignment(Pos.CENTER);
			vbox.getChildren().add(tileBox);
			
			
			Button btnRoll2 = new Button("ROLL 2 DICE");
			Button btnRoll1 = new Button("ROLL 1 DICE");
			Button lockin = new Button("Lock in");
			tileBox.getChildren().add(btnRoll1);
			Button btnEnd = new Button("End Round");
			Label result = new Label("Result");
			Label lblValue = new Label("0"); // output of results
			vbox.getChildren().addAll(btnRoll1, btnRoll2, lockin, result, lblValue);
			vbox.setAlignment(Pos.CENTER);
			vbox.getChildren().addAll(btnEnd);
			btnRoll1.setDisable(true);
			btnRoll1.setStyle("-fx-background-color: orange;");


			// When the ROLL DICE button is clicked
			btnRoll2.setOnAction(e -> {
				int roll1 = d1.roll(); // Roll for die 1
				int roll2 = d2.roll(); // Roll for die 2

				int total = roll1 + roll2;
				lblValue.setText(String.valueOf(total));

			});

			btnRoll1.setOnAction(e -> {
				int roll1 = d1.roll();
				lblValue.setText(String.valueOf(roll1));
			});
			
			
			btnEnd.setOnAction(e -> {
				int amount = 0;
				for (int i = 0; i < tileBtns.length; i++) {
					if (tileBtns[i].getStyle().contains("lightgray")) {
						amount += Integer.valueOf(tileBtns[i].getText());
					}
				}
	        	lblValue.setText(String.valueOf(amount)); 
	        	result.setText("Score"); 
	        	btnRoll2.setDisable(true);
	        	btnRoll1.setDisable(true);
	        	lockin.setDisable(true);
	        	btnRoll2.setStyle("-fx-background-color: orange;");
	        	btnRoll1.setStyle("-fx-background-color: orange;"); 
	        	lockin.setStyle("-fx-background-color: orange;"); 
				
			});
			
			lockin.setOnAction(e -> {
				int sum = 0;
				for (int i = 0; i < tileBtns.length; i++) {
					if (!tileBtns[i].getStyle().contains("orange")) {
						sum += Integer.valueOf(tileBtns[i].getText());
					}
				}
			if (sum == Integer.valueOf(lblValue.getText())) {
        	     for (int i = 0; i < tileBtns.length; i++) {
        	         if (tileBtns[i].getStyle().contains("red")) {
        	             tileBtns[i].setStyle("-fx-background-color: orange;");
			
        	         }
        	     }
        	     btnRoll2.setDisable(false);
        	     btnRoll1.setDisable(true);
			}
			
			if (tileBtns[6].getStyle().contains("orange") && tileBtns[7].getStyle().contains("orange") && tileBtns[8].getStyle().contains("orange")) {
             	btnRoll1.setDisable(true);
             	btnRoll2.setStyle("-fx-background-color: orange;"); 
             	btnRoll1.setDisable(false);
             	btnRoll1.setStyle("-fx-background-color: lightgray;");
				}
			});

			Scene scene = new Scene(vbox, 500, 200);
			stage.setScene(scene);

			stage.show();
		}
	

	public static void main(String[] args) {
		launch(args);
	}
}
