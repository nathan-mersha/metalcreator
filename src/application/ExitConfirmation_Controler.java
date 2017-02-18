package application;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExitConfirmation_Controler extends AnchorPane {
	@FXML Button exit ;
	@FXML Button cancel;
	
	private Stage stage = new Stage();
	private Scene scene = new Scene(this);
	
	public Stage secondaryStage;
	
	public ExitConfirmation_Controler(){
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ExitConfirmationTab_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public ExitConfirmation_Controler(Stage secondaryStage){
		this.secondaryStage = secondaryStage;
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ExitConfirmationTab_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void buttonAction(ActionEvent event){
		if (event.getSource() == exit) {
			if (secondaryStage == null) {
				System.exit(0);
			}
			else {
				secondaryStage.close();
				stage.close();
			}
		}
		else if (event.getSource() == cancel) {
			stage.close();
		}
	}
	public void show(){
		stage.setScene(scene);
		stage.initOwner(Main.secondaryStage.getOwner());
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Message");
		stage.setResizable(false);
		stage.show();
		
	}

}
