package application;


import java.io.IOException;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import math.PropertyMath;

public class ResultDisplayerChild_Controler extends AnchorPane {

	@FXML Label pointName;
	@FXML TextArea note;
	@FXML TableView<TableValues> input_table;
	@FXML TableView<TableValues> output_table;
	// Input Values
	public CheckBox input_CheckBox;
	public String input_PointName;
	public String input_OnPhaseName;
	public double input_CemmentiteComp;
	public double input_TempratureVal;
	public String input_OnPurePhase;
	public double input_RightFrac;
	public double input_LeftFrac;
	public TempratureListHolder input_ComputedTemps;
	public double input_CoolingRate;
	public String input_Note;
	
	public boolean isChecked(){
		return input_CheckBox.isSelected();
	}
	
	public ResultDisplayerChild_Controler(TreeTableView<TableValues> inputValue,int rowNum){
		if (rowNum < 2) {
			System.err.println("Row Number input Error");
		}
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("ResultDisplayerChild_GUI.fxml"));
			loader.setRoot(this);
			loader.setController(this);
			loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//index 2 is the starting of TreeTableData
		try {
			System.out.println(rowNum);
			this.input_CheckBox = (CheckBox)inputValue.getColumns().get(0).getCellData(rowNum);
			this.input_PointName = (String) inputValue.getColumns().get(1).getCellData(rowNum);
			this.input_OnPhaseName = (String) inputValue.getColumns().get(2).getCellData(rowNum);
			this.input_CemmentiteComp = Double.parseDouble((String) inputValue.getColumns().get(3).getCellData(rowNum));
			this.input_TempratureVal = Double.parseDouble((String)inputValue.getColumns().get(4).getCellData(rowNum));
			this.input_OnPurePhase = (String) inputValue.getColumns().get(5).getCellData(rowNum);
			
			try {
				this.input_RightFrac = Double.parseDouble((String) inputValue.getColumns().get(6).getCellData(rowNum));
				this.input_LeftFrac = Double.parseDouble((String) inputValue.getColumns().get(7).getCellData(rowNum));
			} catch (Exception e) {
				e.printStackTrace();
				this.input_RightFrac = 0.0;
				this.input_LeftFrac = 0.0;
			}
			
			this.input_ComputedTemps = (TempratureListHolder) inputValue.getColumns().get(8).getCellData(rowNum);
			this.input_CoolingRate = Double.parseDouble((String) inputValue.getColumns().get(9).getCellData(rowNum));
			this.input_Note = (String) inputValue.getColumns().get(10).getCellData(rowNum);
		} catch (Exception e) {
			System.err.println("Cast error exception");
			System.err.println("ResultDisplayerChild_Controler.java");
			e.printStackTrace();
		}
		inputDataSetter();
		outputDataSetter();
		
	}
	public void setOutPutDataToTable(Double enthalpy,Double density,Double thermalConductivity,Double liquidViscosity,Double surfaceTension_AL,Double SL_interfaceEnergy,Double secondDendArmSpacing,Double austeniteGrainSize){
		
	}
	public void inputDataSetter(){
		pointName.setText(input_PointName);
		note.setText(input_Note);
		
		TableValues inputValue = new TableValues(Double.toString(input_CemmentiteComp), Double.toString(input_TempratureVal), Double.toString(input_CoolingRate), input_OnPhaseName, Double.toString(input_LeftFrac), Double.toString(input_RightFrac));
		input_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("composition_value"));
		input_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("temp_value"));
		input_table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("coolingTim                                                                                                                                                                                                                                                                                                                                                                                                                           e_value"));
		input_table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("onPhaseName_value"));
		input_table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("leftFraction_value"));
		input_table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("rightFraction_value"));

		input_table.setItems(FXCollections.observableArrayList(inputValue));
		
	}
	public void outputDataSetter(){
		output_table.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("variantTempPoint_value"));
		output_table.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("enthalpy_value"));
		output_table.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("density_value"));
		output_table.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("thermalConductivity_value"));
		output_table.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("liquidViscosity_value"));
		output_table.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("surfaceTension_value"));
		output_table.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("slInterfaceEnergy_value"));
		output_table.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("secondDendrite_value"));
		output_table.getColumns().get(8).setCellValueFactory(new PropertyValueFactory<>("austeniteGrain_value"));
		
		ArrayList<Double> listOfTemp = input_ComputedTemps.getTempPointList();
		double tempratureHolder = 0;
		ObservableList<TableValues> outPutResults = FXCollections.observableArrayList();
		for (int i = -1; i < listOfTemp.size(); i++) {
			if (i==-1) {
				tempratureHolder = input_TempratureVal;
			}
			else {
				tempratureHolder = listOfTemp.get(i);
			}
			double currentTemp = tempratureHolder;
			double enthalpy_result = PropertyMath.enthalpy(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double density_result = PropertyMath.density(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double thermalConductivity_result = PropertyMath.thermalConductivity(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double liquidViscosity_result = PropertyMath.liquidViscosity(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double surfaceTension_result = PropertyMath.surfaceTension_LA(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double slInterfaceEnergy_result = PropertyMath.sl_interfaceEnergy(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double secondDendrite_result = PropertyMath.secDend_ArmSpacing(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			double austeniteGrain_result = PropertyMath.austeniteGrainSize(input_CemmentiteComp, tempratureHolder, input_RightFrac, input_LeftFrac, input_CoolingRate, input_OnPhaseName);
			
			TableValues values = new TableValues(currentTemp, enthalpy_result, density_result, thermalConductivity_result, liquidViscosity_result, surfaceTension_result, slInterfaceEnergy_result, secondDendrite_result, austeniteGrain_result);
			outPutResults.add(values);
		}
		output_table.setItems(outPutResults);
		
	}
	
	
}



                                                                                                                       