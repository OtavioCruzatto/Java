package graphicalUserInterface;

import graphicalUserInterface.ConfiguracaoPortaSerialController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application{

	private ConfiguracaoPortaSerialController configuracaoPortaSerialController;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("configuracaoPortaSerial.fxml"));
		Parent root = loader.load();
		configuracaoPortaSerialController = (ConfiguracaoPortaSerialController) loader.getController();
		Scene scene = new Scene(root);
		primaryStage.setTitle("Configuração Porta Serial");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() {
		configuracaoPortaSerialController.getPortaSerialUsuario().fecharPortaSerial();
	}

	public ConfiguracaoPortaSerialController getConfiguracaoPortaSerialController() {
		return configuracaoPortaSerialController;
	}

}

