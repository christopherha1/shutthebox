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
	//Hello

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
			tileBox.getChildren().add(tileBtns[i]);
		}
		tileBox.setAlignment(Pos.CENTER);
		vbox.getChildren().add(tileBox);
		
		
		Button btnRoll = new Button("ROLL 2 DICE");
		Button btnRoll2 = new Button("ROLL 1 DICE");
		Button lockin = new Button("Lock in");
		tileBox.getChildren().add(btnRoll);
		Button btnEnd = new Button("End Round");
		Label result = new Label("Result");
		Label lblValue = new Label(); // output of results
		vbox.getChildren().addAll(btnRoll2,btnRoll, lockin, result, lblValue);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(btnEnd);
		
		
		// When the ROLL DICE button is clicked
		btnRoll.setOnAction(e -> {
			int roll1 = d1.roll();  // Roll for die 1
			int roll2 = d2.roll();  // Roll for die 2
			
			
			
			int total = roll1 + roll2;
			lblValue.setText(String.valueOf(total));
			
			
		});
		
		btnRoll2.setOnAction(e -> {
			int roll1 = d1.roll();
			lblValue.setText(String.valueOf(roll1));
		});
		
		Scene scene = new Scene(vbox, 500, 200);
		stage.setScene(scene);
		
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
