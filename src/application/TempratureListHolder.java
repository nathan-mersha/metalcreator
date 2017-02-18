package application;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class TempratureListHolder extends HBox {
	
	TextField inputField = new TextField();
	ListView<Label> dataContainer = new ListView();
	
	MenuItem remove = new MenuItem("Remove");
	MenuItem removeAll = new MenuItem("Remove All");
	ContextMenu popUp;
	Label invoker;
	
	double max_TempVal = 0;
	ArrayList<Double> collection = new ArrayList();

	
	public TempratureListHolder(){
		dataContainer.setPrefHeight(5);
		dataContainer.setPrefWidth(70);
		inputField.setPrefWidth(90);
		inputField.setPromptText("MAX=" + max_TempVal + '\u00B0');
		popUp = new ContextMenu(remove,removeAll);
		
		remove.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dataContainer.getItems().remove(invoker);
			}
		});
		removeAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				dataContainer.getItems().removeAll(dataContainer.getItems());
				
			}
		});
		
		inputField.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				if (event.getCode() == KeyCode.ENTER) {
					double d = evaluateInput(inputField.getText());
					conditionalMembering(d);
				}
			}
		});
		
		getChildren().addAll(inputField,dataContainer);
	}
	
	private void conditionalMembering(double d){
		Label l = new Label(Double.toString(d));
		
		if (d != -1) {
			boolean flag = false;
			for (int i = 0; i < dataContainer.getItems().size(); i++) {
				double value = Double.parseDouble(dataContainer.getItems().get(i).getText());
				if (d == value) {
					flag = true;
				}
			}
			if (flag == false) {
				dataContainer.getItems().add(l);
				collection.add(d);
			}
		}
		inputField.setText("");
		l.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent e) {
				if (e.isPopupTrigger()) {
					popUp.show(dataContainer.getParent(),e.getScreenX(),e.getScreenY());
					invoker = (Label) e.getSource();	
				}
			}
		});
		
	}
	private double evaluateInput(String userInput){
		Double d;
		try {
			d = Double.parseDouble(userInput);
		} catch (NumberFormatException e) {
			return -1.0;
		}
		
		if (d > max_TempVal) {
			return max_TempVal;
		}
		else if (d < 0) {
			return -1;
		}
		else {
			return d;
		}
		
	}
	public ArrayList<Double> getTempPointList(){
		return collection;
	}
	public void setMaxTempValue(double max_TempVal){
		
		this.max_TempVal = max_TempVal;
		inputField.setPromptText("MAX=" + max_TempVal + '\u00B0');
	}
	public void removeElements(){
		getChildren().removeAll(getChildren());
	}

}
