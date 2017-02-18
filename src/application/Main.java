package application;
	
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage secondaryStage = new Stage();

	public static PhaseDiagram_Controler root = new PhaseDiagram_Controler();
	@Override
	public void start(Stage primaryStage) {
		
		try {
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			secondaryStage.setScene(scene);
			secondaryStage.setResizable(false);
			secondaryStage.setTitle("Phase-Diagram V 1.2");
			secondaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {

		launch(args);
	}
}
