package pacote;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Starter extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			String path = "FXML_LoginScreen1.fxml";
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
			Parent root = loader.load();
			Scene scene = new Scene(root);
			primaryStage.initStyle(StageStyle.UTILITY);
			
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void stop() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
