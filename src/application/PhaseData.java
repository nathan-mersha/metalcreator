package application;

import java.util.ArrayList;

public enum PhaseData{
	
	LIQUID("Liquid",-1236956,0,"smth Liquid",true,"None","None"),
	DELTA_LIQUID("Delta + Liquid",-9384740,0,"smth DeltaLiquid",false,"Delta","Liquid"),
	DELTA("Delta",-959956,0,"smth Delta",true,"None","None"),
	DELTA_AUSTENITE("Delta + Austenite",-7485886,0,"smth DeltaLiquid",false,"Delta","Liquid"),
	AUSTENITE_LIQUID("Austenite + Liquid",-1,0,"smth AusteniteLiquid",false,"Austenite","Liquid"),
	LIQUID_CEMMENTITE("Liquid + Cemmentite",-857322,0,"smth Liquid_Cemmentite",false,"Liquid","Cemmentite"),
	AUSTENITE("Austenite",-13085788,0,"smth Austenite",true,"None","None"),
	AUSTENITE_CEMMENTITE("Austenite + Cemmentite",-4697441,0,"smth AusteniteCemmentite",false,"Austenite","Cemmentite"),
	FERRITE_AUSTENITE("Ferrite + Austenite",-9089751,0,"smth FerriteAustenite",false,"Ferrite","Austenite"),
	FERRITE("Ferrite",-15767239,0,"smth Ferrite",true,"None","None"),
	FERRITE_CEMMENTITE("Ferrite + Cemmentite",-11447983,0,"smth FerriteCemmentite",false,"Ferrite","Cemmentite"),
	CEMMENTITE("Cemmentite",-12337492,0,"smth Cemmentite",true,"None","None"),
	IRON("Iron",-8496730,0,"smth Iron",true,"None","None"),
	
	PUREPHASES,
	MIXEDPHASES;
	
	private ArrayList<Integer> purePhases = new ArrayList<>();
	private ArrayList<Integer> mixedPhases =   new ArrayList<>();
	
	private String phaseName;
	private int colorCode;
	private double phaseEntropy;
	private String phaseNote;
	private boolean isPure;
	private String rightPhaseName;
	private String leftPhaseName;
	
	private PhaseData(){
		 
	}
	
	private PhaseData(String phaseName,int colorCode,double phaseEntropy,String phaseNote,boolean isPure,String leftPhaseName,String rightPhaseName){
		this.phaseName = phaseName;
		this.colorCode = colorCode;
		this.phaseEntropy = phaseEntropy;
		this.phaseNote = phaseNote;
		this.isPure = isPure;
		this.leftPhaseName = leftPhaseName;
		this.rightPhaseName = rightPhaseName;
		if (isPure) {
			purePhases.add(colorCode);
		}
		else if (!isPure){
			mixedPhases.add(colorCode);
		}
	}
		
	public String getPhaseName() {
		return phaseName;
	}

	public int getColorCode() {
		return colorCode;
	}

	public double getPhaseEntropy() {
		return phaseEntropy;
	}

	public String getPhaseNote() {
		return phaseNote;
	}

	public boolean isPure() {
		return isPure;
	}

	public String getRightPhaseName() {
		return rightPhaseName;
	}

	public String getLeftPhaseName() {
		return leftPhaseName;
	}
	public ArrayList<Integer> getPurePhases(){
		return purePhases;
	}
	public ArrayList<Integer> getMixedPhases(){
		return mixedPhases;
	}
	public ArrayList<String> getNeighbors(){
		ArrayList<String> neighborNames = new ArrayList<>();
		neighborNames.add(rightPhaseName);
		neighborNames.add(leftPhaseName);
		return neighborNames;
	}

}
