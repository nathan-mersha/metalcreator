package application;

import javafx.geometry.Rectangle2D;
import javafx.scene.shape.Rectangle;

public class ViewAdjuster {
	final int viewWidth = 1000;
	final int viewHeight = 600;
	
	public double viewPortX = 0;
	public double viewPortY = 0;
	public boolean viewPortChanged = false;
	
	
	public ViewAdjuster(int smallImg_X,int smallImg_Y,int currentViewPortX,int currentViewPortY,boolean isZoom) {
		int largeImg_X = smallImg_X*2;
		int largeImg_Y = smallImg_Y*2;
		
		if (isZoom) {
			if (new Rectangle(currentViewPortX, currentViewPortY, viewWidth, viewHeight).contains(largeImg_X,largeImg_Y)) {
				viewPortChanged = false;
			}
			else if (!new Rectangle(currentViewPortX, currentViewPortY, viewWidth, viewHeight).contains(largeImg_X,largeImg_Y)) {
				Rectangle2D r = defineBestViewPort(largeImg_X, largeImg_Y);
				
				Rectangle2D possibleRectangle = new Rectangle2D(setTo_Max_Min(r.getMinX(), 0, 1000), setTo_Max_Min(r.getMinY(), 0, 600), viewWidth, viewHeight);
				
				viewPortX = possibleRectangle.getMinX();
				viewPortY = possibleRectangle.getMinY();
				viewPortChanged = true;
			}
		}
	}
	private Rectangle2D defineBestViewPort(int pointOnImageX,int pointOnImageY){
		return new Rectangle2D(pointOnImageX-500, pointOnImageY-300, viewWidth, viewHeight);
	}
	private double setTo_Max_Min(double currentValue,double possibleMinimum,double possibleMaximum){
		if (currentValue < possibleMinimum) {
			return possibleMinimum;
		}
		else if (currentValue> possibleMaximum) {
			return possibleMaximum;
		}
		else {
			return currentValue;
		}
		
	}
	public double getViewPortX(){
		return viewPortX;
	}
	public double getViewPortY(){
		return viewPortY;
	}

}
