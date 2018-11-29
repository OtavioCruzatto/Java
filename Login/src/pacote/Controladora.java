package pacote;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controladora implements Initializable {
	
	@FXML
	private Label forgotLbl;
	
	@FXML
	private void passarPorForgotPassword() {
		forgotLbl.setUnderline(true);
	}
	
	@FXML
	private void sairDeForgotPassword() {
		forgotLbl.setUnderline(false);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
