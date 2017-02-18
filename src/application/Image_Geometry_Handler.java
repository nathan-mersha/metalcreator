package application;

import javafx.scene.image.Image;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Image_Geometry_Handler {
	
	private Image image;
	private WritableImage wImage;
	public Image_Geometry_Handler(Image img){
		image = img;
	}
	public Image_Geometry_Handler(String url){
		image = new Image(url);
	}
	
	public void changePixel(Color color,int x,int y){
		wImage = new WritableImage(image.getPixelReader(),(int)image.getWidth(),(int)image.getHeight());
		PixelWriter pxWriter = wImage.getPixelWriter();
		pxWriter.setColor(x, y,color);
	}
	public Image getImage(){
		return wImage;
	}
	 
	

}
