
package application;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.CheckBox;

public class TableValues {
	
	private SimpleStringProperty points_value = new SimpleStringProperty();
	private SimpleStringProperty onPhaseName_value = new SimpleStringProperty();
	private SimpleDoubleProperty composition_value = new SimpleDoubleProperty();
	private SimpleDoubleProperty temp_value = new SimpleDoubleProperty();
	private SimpleStringProperty onPurePhase_value = new SimpleStringProperty();
	
	private SimpleDoubleProperty rightFraction_value = new SimpleDoubleProperty();
	private SimpleDoubleProperty leftFraction_value = new SimpleDoubleProperty();
	private SimpleDoubleProperty coolingTime_value = new SimpleDoubleProperty();
	private SimpleStringProperty note_value = new SimpleStringProperty();
	//////////
	private CheckBox check_value = new CheckBox();
	private TempratureListHolder listHolder_value = new TempratureListHolder();
	//////////
	private Double variantTempPoint_value = 0.0;
	private Double enthalpy_value = 0.0;
	private Double density_value = 0.0;
	private Double thermalConductivity_value = 0.0;
	private Double liquidViscosity_value = 0.0;
	private Double surfaceTension_value = 0.0;
	private Double slInterfaceEnergy_value = 0.0;
	private Double secondDendrite_value = 0.0;
	private Double austeniteGrain_value = 0.0;
	
	
	
	
	public TableValues(double variantTempPoint,double enthalpy,double density,double thermalConductivity,double liquidViscosity,double surfaceTension,double slInterfaceEnergy,double secondDendrite,double austeniteGrain){
		variantTempPoint_value = variantTempPoint;
		enthalpy_value = enthalpy;
		density_value = density;
		thermalConductivity_value = thermalConductivity;
		liquidViscosity_value = liquidViscosity;
		surfaceTension_value = surfaceTension;
		slInterfaceEnergy_value =slInterfaceEnergy;
		secondDendrite_value = secondDendrite;
		austeniteGrain_value = austeniteGrain;
		
	}
	
	public TableValues(String points,String onPhaseName,String composition,String temprature,String onPurePhase,String rightFraction,String leftFraction,String coolingTime,String note){
		points_value = new SimpleStringProperty(points);
		onPhaseName_value = new SimpleStringProperty(onPhaseName);
		composition_value = new SimpleDoubleProperty(Double.parseDouble(composition));
		temp_value = new SimpleDoubleProperty(Double.parseDouble(temprature));
		onPurePhase_value = new SimpleStringProperty(onPurePhase);
		rightFraction_value = new SimpleDoubleProperty(Double.parseDouble(rightFraction));
		leftFraction_value = new SimpleDoubleProperty(Double.parseDouble(leftFraction));
		coolingTime_value = new SimpleDoubleProperty(Double.parseDouble(coolingTime));
		note_value = new SimpleStringProperty(note);
		
	}
	public TableValues(String composition,String temprature,String coolingTime,String onPhaseName,String leftFraction,String rightFraction){
		composition_value = new SimpleDoubleProperty(Double.parseDouble(composition));
		temp_value = new SimpleDoubleProperty(Double.parseDouble(temprature));
		coolingTime_value = new SimpleDoubleProperty(Double.parseDouble(coolingTime));
		onPhaseName_value = new SimpleStringProperty(onPhaseName);
		leftFraction_value = new SimpleDoubleProperty(Double.parseDouble(leftFraction));
		rightFraction_value = new SimpleDoubleProperty(Double.parseDouble(rightFraction));
		
	}
	public TableValues(CheckBox chBox, String points,String onPhaseName,String composition,String temprature,String onPurePhase,String rightFraction,String leftFraction,String coolingTime,String note){
		check_value = chBox;
		points_value = new SimpleStringProperty(points);
		onPhaseName_value = new SimpleStringProperty(onPhaseName);
		composition_value = new SimpleDoubleProperty(Double.parseDouble(composition));
		temp_value = new SimpleDoubleProperty(Double.parseDouble(temprature));
		onPurePhase_value = new SimpleStringProperty(onPurePhase);
		rightFraction_value = new SimpleDoubleProperty(Double.parseDouble(rightFraction));
		leftFraction_value = new SimpleDoubleProperty(Double.parseDouble(leftFraction));
		coolingTime_value = new SimpleDoubleProperty(Double.parseDouble(coolingTime));
		note_value = new SimpleStringProperty(note);
		
	}
	public double getVariantTempPoint_value() {
		return variantTempPoint_value;
	}
	public void setVariantTempPoint_value(double variantTempPoint_value) {
		this.variantTempPoint_value = variantTempPoint_value;
	}
	public double getEnthalpy_value() {
		return enthalpy_value;
	}
	public void setEnthalpy_value(double enthalpy_value) {
		this.enthalpy_value = enthalpy_value;
	}
	public double getDensity_value() {
		return density_value;
	}
	public void setDensity_value(double density_value) {
		this.density_value = density_value;
	}
	public double getThermalConductivity_value() {
		return thermalConductivity_value;
	}
	public void setThermalConductivity_value(double thermalConductivity_value) {
		this.thermalConductivity_value = thermalConductivity_value;
	}
	public double getLiquidViscosity_value() {
		return liquidViscosity_value;
	}
	public void setLiquidViscosity_value(double liquidViscosity_value) {
		this.liquidViscosity_value = liquidViscosity_value;
	}
	public double getSurfaceTension_value() {
		return surfaceTension_value;
	}
	public void setSurfaceTension_value(double surfaceTension_value) {
		this.surfaceTension_value = surfaceTension_value;
	}
	public double getSlInterfaceEnergy_value() {
		return slInterfaceEnergy_value;
	}
	public void setSlInterfaceEnergy_value(double slInterfaceEnergy_value) {
		this.slInterfaceEnergy_value = slInterfaceEnergy_value;
	}
	public double getSecondDendrite_value() {
		return secondDendrite_value;
	}
	public void setSecondDendrite_value(double secondDendrite_value) {
		this.secondDendrite_value = secondDendrite_value;
	}
	public double getAusteniteGrain_value() {
		return austeniteGrain_value;
	}
	public void setAusteniteGrain_value(double austeniteGrain_value) {
		this.austeniteGrain_value = austeniteGrain_value;
	}
	
	public TempratureListHolder getListHolder_value() {
		return listHolder_value;
	}

	public void setListHolder_value(TempratureListHolder listHolder_value) {
		this.listHolder_value = listHolder_value;
	}
	
	public TableValues(CheckBox checkBox){
		check_value = checkBox;
	}
	
	public CheckBox getCheck_value() {
		return check_value;
	}
	
	public void setCheck_value(CheckBox checkBox) {
		check_value = checkBox;
	}
	
	public String getOnPhaseName_value() {
		return onPhaseName_value.get();
	}
	
	public String getRightFraction_value() {
		return rightFraction_value.get()==0 ? " " : Double.toString(rightFraction_value.get());
	}
	
	public String getOnPurePhase_value() {
		return onPurePhase_value.get();
	}
	
	public String getLeftFraction_value() {
		return leftFraction_value.get()==0 ? " " : Double.toString(leftFraction_value.get());
	}
	public String getCoolingTime_value() {
		return coolingTime_value.get()==0 ? " " : Double.toString(coolingTime_value.get());
	}
	public void setOnPurePhase_value(String onPurePhase) {
		onPurePhase_value = new SimpleStringProperty(onPurePhase);
	}
	public void setOnPhaseName_value(String onPhaseName) {
		onPhaseName_value = new SimpleStringProperty(onPhaseName);
	}
	public void setRightFraction_value(Double rightFraction) {
		rightFraction_value = new SimpleDoubleProperty(rightFraction);
	}
	public void setLeftFraction_value(Double leftFraction) {
		leftFraction_value = new SimpleDoubleProperty(leftFraction);
	}
	public void setCoolingTime_value(Double coolingTime) {
		coolingTime_value = new SimpleDoubleProperty(coolingTime);
	}
	public TableValues(String points){
		points_value = new SimpleStringProperty(points);
	}
///
	public String getPoints_value() {
		return points_value.get();
	}

	public String getComposition_value() {
		return composition_value.get()==0 ? " " : Double.toString(composition_value.get());
	}

	public String getTemp_value() {
		return temp_value.get()==0 ? " " : Double.toString(temp_value.get());
	}

	public String getNote_value() {
		return note_value.get();
	}
	
	public void setPoints_value(String points_v) {
		points_value = new SimpleStringProperty(points_v);
	}

	public void setComposition_value(Double composition_v) {
		composition_value = new SimpleDoubleProperty(composition_v);
	}

	public void setTemp_value(Double temp_v) {
		temp_value = new SimpleDoubleProperty(temp_v);
	}

	public void setNote_value(String note_v) {
		note_value = new SimpleStringProperty(note_v);
	}
	
}
