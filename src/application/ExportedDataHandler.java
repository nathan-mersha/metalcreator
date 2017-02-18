package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ExportedDataHandler  {
	TreeTableView<TableValues> exportedData = new TreeTableView<>();
	
	private Button calculatePropBtn = new Button("Calculate Properties");
	private Button cancelBtn = new Button("Cancel");
	private Button selectAll = new Button("Select All");
	private Button selectNone = new Button("Select None");
	
	private Scene scene;
	private AnchorPane root = new AnchorPane();
	private Stage stage = new Stage();
	
	public ExportedDataHandler(TreeTableView<TableValues> exportedTable){
		exportedData = exportedTable;
		double buttonWidth = 160;
		double buttonHeight = 17;
		calculatePropBtn.setPrefSize(buttonWidth, buttonHeight);
		cancelBtn.setPrefSize(buttonWidth, buttonHeight);
		selectAll.setPrefSize(buttonWidth, buttonHeight);
		selectNone.setPrefSize(buttonWidth, buttonHeight);
		
		exportedData = exportedTable;
		
		GridPane gPaneA	= new GridPane();
		GridPane gPaneB	= new GridPane();
		
		gPaneA.setHgap(25);
		gPaneB.setHgap(25);
		gPaneA.setPadding(new Insets(0, 95, 0, 10));
		gPaneA.add(calculatePropBtn, 0, 0);
		gPaneA.add(cancelBtn, 1, 0);
		gPaneB.add(selectAll, 0, 0);
		gPaneB.add(selectNone, 1, 0);
		
		Label message = new Label("Mark Points to be Computed");
		message.setTextFill(Color.RED);
		
		VBox verticalCont = new VBox();
		
		HBox horizontalCont = new HBox();
		horizontalCont.getChildren().addAll(gPaneA,gPaneB);
		
		verticalCont.getChildren().addAll(exportedData,horizontalCont,message);
		
		horizontalCont.setPadding(new Insets(10, 10, 0, 0));
		root.getChildren().add(verticalCont);
		
		exportedData.setPrefWidth(805);
		exportedData.setMaxWidth(805);
		exportedData.setMinWidth(805);
		scene = new Scene(root,795,440);
		
		//Assigning Events
		calculatePropBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onCalculateProp();
				
			}
		});
		cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onCancel();
			}
		});
		selectAll.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onSelectAll();
		
			}
		});
		selectNone.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				onSelectNone();
		
			}
		});
		
	}
	
	public void onCalculateProp(){
		ResultDisplayerParent_Controler parentDisplayer = new ResultDisplayerParent_Controler();
		double totalRow = exportedData.getRoot().getChildren().get(0).getChildren().size() + exportedData.getRoot().getChildren().get(1).getChildren().size()+3;
		boolean isOneMarked = false;
		for (int i = 2; i < totalRow; i++) {
			if (i == 10) {}
			else {
				ResultDisplayerChild_Controler childController = new ResultDisplayerChild_Controler(exportedData, i);
				if (childController.isChecked()) {
					isOneMarked = true;
					parentDisplayer.addChild(childController);
				}
			}
			
		}
		if (isOneMarked == false) {
			NoneMarkedMessage_Controller noneMarked = new NoneMarkedMessage_Controller();
			noneMarked.show();
		}
		else if (isOneMarked == true) {
			parentDisplayer.showParent();
			stage.close();
		}
		
		
		
	}
	public void onCancel(){
		stage.close();
	}
	public void onSelectAll(){
		int totalRow = exportedData.getRoot().getChildren().get(1).getChildren().size()+11;
		
		for (int i = 1; i < totalRow; i++) {
			CheckBox ch = (CheckBox)exportedData.getColumns().get(0).getCellData(i);
			ch.setSelected(true);
		}
	}
	public void onSelectNone(){
		int totalRow = exportedData.getRoot().getChildren().get(1).getChildren().size()+11;
		
		for (int i = 1; i < totalRow; i++) {
			CheckBox ch = (CheckBox)exportedData.getColumns().get(0).getCellData(i);
			ch.setSelected(false);
		}
		
	}
	public void showTable(){
		stage.setScene(scene);
		stage.initOwner(Main.secondaryStage.getOwner());
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Exported Values");
		stage.setResizable(false);
		stage.show();
	}
	

}
