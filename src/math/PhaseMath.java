package math;

import application.PhaseDiagram_Controler;
import javafx.scene.input.MouseEvent;

public class PhaseMath {

/**
 * <h1>Description</h1>
 * Method returns the value of left Weight Fraction.
 * @param cR
 * @param cO
 * @param cL
 * @return {@link Double} LeftFraction
 */
	public static double returnLeftWeight(double cR,double cO,double cL){
		return (cR-cO)/(cR-cL);
	}
/**
 * <h1>Description</h1>
 * Method returns the value of right Weight Fraction.
 * @param cR
 * @param cO
 * @param cL
 * @return {@link Double} RightFraction
 */
	public static double returnRightWeight(double cR,double cO,double cL){
		return (cO-cL)/(cR-cL);
	}

/**<h1>Description</h1>
 * Method returns weight Value from a given atomic value.
 * @param atomicValue
 * @return {@link Double} weight value.
 */
	public static double toWeight(double atomicValue){
		return atomicValue/100;
	}
/**<h1>Description</h1>
 * Method returns atomic Value from a given weight value.
 * @param weightValue
 * @return {@link Double} atomic value.
 */	
	public static double toAtomic(double weightValue){
		return weightValue*100;
	}
	public double pxlReaderX(double mouseX,double viewPortX,boolean isZoom){
		return isZoom ? mouseX+viewPortX : mouseX;
	}
	public double pxlReaderY(double mouseY,double viewPortY,boolean isZoom){
		return isZoom ? mouseY+viewPortY: mouseY;
	}
/**
 * <h1>Description</h1>
 * Conversion for the Composition values. The composition values have been given 1000 pixels to represent them, and since the
 * composition is represented in 100% each pixel represent a 0.1% value. If the user wishes to enter a value of high precision
 * then the text field will be of help.
 * @param i {@link MouseEvent} X
 * @return {@link Double} Corresponding true Value.
 */
	public static double mouseX_To_comp(boolean isZoom,double i,int ViewPortX){
		return isZoom ? (i + ViewPortX)/20 : i/10;
	}
	
	
	
/**<h1>Description</h1>
 * Conversion for temperature values, The temperature in the graph ranges from 400 to 1600 degreeCENTIGRADE and with the limited
 * pixels available, i have extracted 600 pixels to represent 1200 different temperature categories. As indicated in the formula
 * i have chosen the even numbers. however if the user wishes to input different values, the text field provided will be of help.
 * special attention have been given however for odd special values.
 * @param i {@link MouseEvent} Y
 * @return {@link Integer} Corresponding true value.
 */
	public static double mouseY_To_temp(boolean isZoom,int i,int ViewPortY){
			int result = 0;
			if (isZoom) {
				result = 1600 - (i + ViewPortY);
				return result;
			}
			else if (!isZoom) {
				result = 1600-(2*i);
				if ((result == 1146)|(result==1148)) {
					result = 1147;
				}
				else if ((result==728)) {
					result = 727;
				}
				else if ((result == 1492)|(result==1494)) {
					result = 1493;
				}

				return result;
			}
			return result;
	}
	/**
	 * <h1>Description</h1>
	 * Method returns Mouse y coordinate value from a temprature Value.
	 * @param d temprature Value {@link Double}
	 * @return Y-coordinate value {@link Integer}
	 */
		public int temp_To_mouseY(boolean isZoom,double temprature,int viewPortY){
			return isZoom ? (int)(1600-viewPortY-temprature) : (int)(1600-temprature)/2;
		}
	/**
	 * <h1>Descripton</h1>
	 * Method returns Mouse X coordinate value from a temprature Value.
	 * @param d Composition Value {@link Double}
	 * @return mouse X-coordinate value {@link Integer}
	 */
		public int comp_To_mouseX(boolean isZoom,double composition,int viewPortX){
			return (int) (isZoom ? ((20*composition)-viewPortX) : (composition*10));
		
		}
/**
 * <h1>Description</h1>
 * Method returns Farhanite value from a Centigrade Parameter.
 * @param centigrade {@link Double}
 * @return FarhaniteValue {@link Double} 
 */
	public double toFarhanite(double centigrade){
		return (centigrade * (9/5)) + 32;
	}
/**
 * <h1>Description</h1>
 * Method returns Celcius Value from a Farhanite Parameter.
 * @param fahranite {@link Double}
 * @return celciusValue {@link Double}
 */
	public double toCelcius(double fahranite){
		return (fahranite - 32)*(5/9);
	}
/**
 * <h1>Description</h1>
 * double(1-0) which corresponds to (1600*c - 400*c) value
 * Method for determining the current temprature value for the Progress indicator node.
 * @param valueinCentigrade {@link Double}
 * @return temprature Progress Value {@link Double}
 */
	public double tempProg_Conv(double valueinCentigrade){
		return (valueinCentigrade-400)/1200;
	}
/**
 * <h1>Description</h1>
 * Method for determining the current composition value for the Progress indicator node.
 * @param valueOfIron {@link Double}
 * @return double (1-0) which corresponds to (100% - 0%) compositon {@link Double}
 */
	public double comp_Conv(double valueOfIron){
				return (valueOfIron/100);
	}
			// Conversion to Mouse values;

			// Conversion Formulas are accurate
/**
 * <h1>Description</h1>
 * Method Returns Cemmentite Value from a Iron value.
 * @param Iron Fe {@link Double}
 * @return cemmentite value {@link Double} 
 * @see #evaluatedValue
 */
	public double iron_mw_in(double fe){
		double evInput = evaluatedValue(fe, 0, 100);
		double cemmentite = Math.abs((evInput-100));
		return cemmentite;
	}
/**
 * <h1>Description</h1>
 * Method returns Cemmentite Value from a Carbon weight value.
 * @param Carbon Mass weight {@link Double}
 * @return cemmentite  {@link Double}
 * @see #evaluatedValues
 */
	public double carbon_mw_in(double carbon){
		double evInput = evaluatedValue(carbon, 0, 6.7);
		double cemmentite = (evInput*100)/6.7;
		return cemmentite;
	}
/**
 * <h1>Description</h1>
 * Method returns Cemmentite value after being tested from evaluatedValueMethod returns cemmentite value after being tested from evaluatedValue.
 * @param cemmentite {@link Double}
 * @return evaluated cemmentite value {@link Double}
 * @see #evaluatedValue
 */
	public double cemmentite_mw_in(double cemmentite){
		double evInput = evaluatedValue(cemmentite, 0, 100);
		return evInput;
	}
			// Place holder formulas
/**
 * <h1>Description</h1>
 * Method returns Cemmentite value from an atomic weight iron value.
 * @param Iron Fe {@link Double}
 * @return cemmentite {@link Double}
 * @see #evaluatedValue
 */
	public double iron_aw_in(double fe){
		double evInput = evaluatedValue(fe, 0, 100);
		double cemmentite = Math.abs((evInput-100))+1;
		return cemmentite;
	}
/**
 * <h1>Description</h1>
 * Method returns Cemmentite value from an atomic weight carbon value.
 * @param carbon {@link Double}
 * @return cemmentite Atomic weight {@link Double}
 * @see #evaluatedValue
 */
	public double carbon_aw_in(double carbon){
		double evInput = evaluatedValue(carbon, 0, 6);
		double cemmentite = (evInput*100)/6;
		return cemmentite;
	}
/**
 * <h1>Description</h1>
 * Method returns Cemmentite Atomic Weight value after passing the evaluation of evaluatedValue.
 * @param cemmentite {@link Double}
 * @return cemmentite Atomic Weight {@link Double}
 * @see #evaluatedValue
 */
	public double cemmentite_aw_in(double cemmentite){
		double evInput = evaluatedValue(cemmentite, 0, 100);
		return evInput;
	}
// ends place holder formulas		
/**
 * <h1>Description</h1>
 * Method returns Celcius value if it passes the evaluatedValue function.
 * @param celc
 * @return celcius value if it passes the evaluatedValue function
 * @see #evaluatedValue
 */
	public double celcius_in(double celc){
		double evInput = evaluatedValue(celc, 400, 1600);
		return evInput;
	}
/**
 * <h1>Description</h1>
 * Method Recieves farhanite and returns Celcius Value.
 * @param farhanite {@link Double}
 * @return celcius {@link Double}
 * @see #evaluatedValue
 */
	public double farhanite_in(double farhanite){
		double evInput = evaluatedValue(farhanite, 752, 2912);
		double celciusHolder = (evInput - 32)*0.55555;
		return celciusHolder;
			}
/**
 * <h1>Description</h1>
 * Method returns the highest or the lowest possible if the user inputs a value that exceeds the highest bound or the lowest bound respectively.
 * @param input {@link Double}
 * @param min {@link Double}
 * @param max {@link Double}
 * @return {@link Double}
 */
	public  double evaluatedValue(double input,double min, double max){
		if(input<min){
			PhaseDiagram_Controler.errorMessage = "Value Exceed Bound Error : Value exceeds MINIMUM value";
			return min;
		}
		if(input>max){
			PhaseDiagram_Controler.errorMessage = "Value Exceed Bound Error : Value exceeds MAXIMUM value";
			return max;
		}
		else {
			return input;
		}
		}

}
