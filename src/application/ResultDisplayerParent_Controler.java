package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ResultDisplayerParent_Controler extends AnchorPane {
	
	@FXML VBox childrenHolder;
	
	@FXML Label fileName_label;
	@FXML Label dataDate_label;
	@FXML Label leftUnit_label;
	@FXML Label rightUnit_label;
	@FXML TextArea userNote;
	@FXML Label phaseDiagramName;
	@FXML TextArea phaseAreaDiscriptionNote;
	// File Menu Menuitems
	@FXML public MenuItem exportToPdf;
	@FXML public MenuItem close;
	//Help Menu Menuitems
	@FXML public MenuItem formulaDescription;
	@FXML public MenuItem about;
	@FXML public MenuItem credit;
	
	private Stage stage = new Stage();
	private Scene scene = new Scene(this,1300,700);
	
	public ResultDisplayerParent_Controler(){
		
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultDisplayerParent_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initialUserDataSetter();
		
	}
	public ResultDisplayerParent_Controler(ResultDisplayerChild_Controler child){
		childrenHolder.getChildren().add(child);
		
	}
	public ResultDisplayerParent_Controler(ArrayList<ResultDisplayerChild_Controler> children){
		for (int i = 0; i < children.size(); i++) {
			childrenHolder.getChildren().add(children.get(i));
		}
		
	}
	public void addChild(ResultDisplayerChild_Controler child){
		childrenHolder.getChildren().add(child);
		
	}
	public void addChildren(ArrayList<ResultDisplayerChild_Controler> children){
		for (int i = 0; i < children.size(); i++) {
			childrenHolder.getChildren().add(children.get(i));
		}
	}
	public void showParent(){
		stage.setScene(scene);
		stage.initOwner(Main.secondaryStage.getOwner());
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Computed Point Properties /" + fileName_label.getText());
		stage.setResizable(false);
		stage.show();
		
	}
	
	
	public void closeSpace(){
		ExitConfirmation_Controler exitController = new ExitConfirmation_Controler(stage);
		exitController.show();
	}
	private void initialUserDataSetter(){
	
		fileName_label.setText(Main.root.getDataName());
		dataDate_label.setText(Main.root.getDataDate());
		leftUnit_label.setText(Main.root.getLeftElement());
		rightUnit_label.setText(Main.root.getRightElement());
		userNote.setText(Main.root.getUserNote());
		phaseDiagramName.setText(Main.root.getPhaseDiagramName());
		phaseAreaDiscriptionNote.setText(Main.root.getPhaseDescription());
	}
	public void menuItemController(ActionEvent e){
		if (e.getSource() == exportToPdf) {
			System.out.println("ExportToPdf selcted");
		}
		else if (e.getSource() == close) {
			
			closeSpace();
		}
		else if (e.getSource() == formulaDescription) {
			System.out.println("formulaDescription selcted");
		}
		else if (e.getSource() == about) {
			System.out.println("about selcted");
		}
		else if (e.getSource() == credit) {
			System.out.println("credits selcted");
		}
		
	}

}
