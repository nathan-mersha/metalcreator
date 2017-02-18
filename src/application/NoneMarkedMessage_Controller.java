package application;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class NoneMarkedMessage_Controller extends AnchorPane {
	@FXML public Button ok;
	private Stage stage = new Stage();
	private Scene scene = new Scene(this);
	
	public NoneMarkedMessage_Controller(){
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("NoneMarkedMessage_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
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
	public void onButtonAction(){
		stage.close();
	}
	

}
