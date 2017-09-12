package graphicalUserInterface;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import portaSerial.PortaSerial;

public class ConfiguracaoPortaSerialController implements Initializable {

	@FXML private Label portaSerialLabel;
	@FXML private Label baudrateLabel;
	@FXML private Label dataBitsLabel;
	@FXML private Label stopBitsLabel;
	@FXML private Label controleDeFluxoLabel;
	@FXML private Label statusPortaSerialLabel;
	@FXML private Label paridadeLabel;
	@FXML private ComboBox<String> portaSerialComboBox;
	@FXML private ComboBox<String> baudrateComboBox;
	@FXML private ComboBox<String> dataBitsComboBox;
	@FXML private ComboBox<String> stopBitsComboBox;
	@FXML private ComboBox<String> controleDeFluxoComboBox;
	@FXML private ComboBox<String> paridadeComboBox;
	@FXML private Tab comunicacaoSerialTab;
	@FXML private Tab testeTab;
	@FXML private GridPane comunicacaoSerialGridPane;
	@FXML private Button conectarPortaSerialButton;
	@FXML private Button enviarDadosButton;
	@FXML private TextField enviarDadosTextField;
	@FXML private TextArea recepcaoSerialTextArea;

	private PortaSerial portaSerialUsuario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		portaSerialUsuario = new PortaSerial();
		portaSerialUsuario.setConfiguracaoPortaSerialController(this);
		

		ObservableList<String> portas = FXCollections.observableArrayList(portaSerialUsuario.getListaDePortasComDisponiveis());
		getPortaSerialComboBox().setItems(portas);

		ObservableList<String> baudRate = FXCollections.observableArrayList("1200", "2400", "4800", "9600", "19200", "38400", "57600", "115200");
		getBaudrateComboBox().setItems(baudRate);
		getBaudrateComboBox().getSelectionModel().select("9600");

		ObservableList<String> paridade = FXCollections.observableArrayList("None", "Odd", "Even");
		getParidadeComboBox().setItems(paridade);
		getParidadeComboBox().getSelectionModel().select("None");

		ObservableList<String> numDeBits = FXCollections.observableArrayList("7 bits", "8 bits");
		getDataBitsComboBox().setItems(numDeBits);
		getDataBitsComboBox().getSelectionModel().select("8 bits");

		ObservableList<String> stopBits = FXCollections.observableArrayList("1 bit", "1.5 bits", "2 bits");
		getStopBitsComboBox().setItems(stopBits);
		getStopBitsComboBox().getSelectionModel().select("1 bit");

		ObservableList<String> flowControl = FXCollections.observableArrayList("None", "Xon / Xoff", "Hardware");
		getControleDeFluxoComboBox().setItems(flowControl);
		getControleDeFluxoComboBox().getSelectionModel().select("None");

		getStatusPortaSerialLabel().setText("Desconectado!");
		this.desabilitarComboBoxSerial(false);
		this.desabilitarTabs(true);
	}

	@FXML
	private void conectarPortaSerialButtonPressed() {
		portaSerialUsuario.setPortaComSelecionada(getPortaSerialComboBox().getValue());
		portaSerialUsuario.setBaudrate(getBaudrateComboBox().getValue());
		portaSerialUsuario.setParidade(getParidadeComboBox().getValue());
		portaSerialUsuario.setDataBits(getDataBitsComboBox().getValue());
		portaSerialUsuario.setStopBits(getStopBitsComboBox().getValue());
		portaSerialUsuario.setControleDeFluxo(getControleDeFluxoComboBox().getValue());

		if(portaSerialUsuario.conectarNaPorta()) {
			getStatusPortaSerialLabel().setText("Conectado!");
			getConectarPortaSerialButton().setText("Desconectar!");
			this.desabilitarComboBoxSerial(true);
			this.desabilitarTabs(false);
		}
		else {
			getStatusPortaSerialLabel().setText("Desconectado!");
			getConectarPortaSerialButton().setText("Conectar!");
			portaSerialUsuario.fecharPortaSerial();
			this.desabilitarComboBoxSerial(false);
			this.desabilitarTabs(true);
		}

	}

	@FXML
	private void enviarDadosButtonPressed() {
		portaSerialUsuario.enviarDados(getEnviarDadosTextField().getText() + "\r\n");
	}

	private void desabilitarComboBoxSerial(boolean desabilitar) {
		getPortaSerialComboBox().setDisable(desabilitar);
		getBaudrateComboBox().setDisable(desabilitar);
		getParidadeComboBox().setDisable(desabilitar);
		getDataBitsComboBox().setDisable(desabilitar);
		getStopBitsComboBox().setDisable(desabilitar);
		getControleDeFluxoComboBox().setDisable(desabilitar);
	}


	private void desabilitarTabs(boolean desabilitar) {
		getTesteTab().setDisable(desabilitar);
	}

	public void interpretarDadosRecebidos(String dadosRecebidos) {
		getRecepcaoSerialTextArea().setText(dadosRecebidos);
	}

	public Label getPortaSerialLabel() {
		return portaSerialLabel;
	}

	public void setPortaSerialLabel(Label portaSerialLabel) {
		this.portaSerialLabel = portaSerialLabel;
	}

	public Label getBaudrateLabel() {
		return baudrateLabel;
	}

	public void setBaudrateLabel(Label baudrateLabel) {
		this.baudrateLabel = baudrateLabel;
	}

	public Label getDataBitsLabel() {
		return dataBitsLabel;
	}

	public void setDataBitsLabel(Label dataBitsLabel) {
		this.dataBitsLabel = dataBitsLabel;
	}

	public Label getStopBitsLabel() {
		return stopBitsLabel;
	}

	public void setStopBitsLabel(Label stopBitsLabel) {
		this.stopBitsLabel = stopBitsLabel;
	}

	public Label getControleDeFluxoLabel() {
		return controleDeFluxoLabel;
	}

	public void setControleDeFluxoLabel(Label controleDeFluxoLabel) {
		this.controleDeFluxoLabel = controleDeFluxoLabel;
	}

	public ComboBox<String> getPortaSerialComboBox() {
		return portaSerialComboBox;
	}

	public void setPortaSerialComboBox(ComboBox<String> portaSerialComboBox) {
		this.portaSerialComboBox = portaSerialComboBox;
	}

	public ComboBox<String> getBaudrateComboBox() {
		return baudrateComboBox;
	}

	public void setBaudrateComboBox(ComboBox<String> baudrateComboBox) {
		this.baudrateComboBox = baudrateComboBox;
	}

	public ComboBox<String> getDataBitsComboBox() {
		return dataBitsComboBox;
	}

	public void setDataBitsComboBox(ComboBox<String> dataBitsComboBox) {
		this.dataBitsComboBox = dataBitsComboBox;
	}

	public ComboBox<String> getStopBitsComboBox() {
		return stopBitsComboBox;
	}

	public void setStopBitsComboBox(ComboBox<String> stopBitsComboBox) {
		this.stopBitsComboBox = stopBitsComboBox;
	}

	public ComboBox<String> getControleDeFluxoComboBox() {
		return controleDeFluxoComboBox;
	}

	public void setControleDeFluxoComboBox(ComboBox<String> controleDeFluxoComboBox) {
		this.controleDeFluxoComboBox = controleDeFluxoComboBox;
	}

	public Tab getComunicacaoSerialTab() {
		return comunicacaoSerialTab;
	}

	public void setComunicacaoSerialTab(Tab comunicacaoSerialTab) {
		this.comunicacaoSerialTab = comunicacaoSerialTab;
	}

	public Tab getTesteTab() {
		return testeTab;
	}

	public void setTesteTab(Tab testeTab) {
		this.testeTab = testeTab;
	}

	public GridPane getComunicacaoSerialGridPane() {
		return comunicacaoSerialGridPane;
	}

	public void setComunicacaoSerialGridPane(GridPane comunicacaoSerialGridPane) {
		this.comunicacaoSerialGridPane = comunicacaoSerialGridPane;
	}

	public Label getStatusPortaSerialLabel() {
		return statusPortaSerialLabel;
	}

	public void setStatusPortaSerialLabel(Label statusPortaSerialLabel) {
		this.statusPortaSerialLabel = statusPortaSerialLabel;
	}

	public Label getParidadeLabel() {
		return paridadeLabel;
	}

	public void setParidadeLabel(Label paridadeLabel) {
		this.paridadeLabel = paridadeLabel;
	}

	public ComboBox<String> getParidadeComboBox() {
		return paridadeComboBox;
	}

	public void setParidadeComboBox(ComboBox<String> paridadeComboBox) {
		this.paridadeComboBox = paridadeComboBox;
	}

	public Button getConectarPortaSerialButton() {
		return conectarPortaSerialButton;
	}

	public void setConectarPortaSerialButton(Button conectarPortaSerialButton) {
		this.conectarPortaSerialButton = conectarPortaSerialButton;
	}

	public PortaSerial getPortaSerialUsuario() {
		return portaSerialUsuario;
	}

	public void setPortaSerialUsuario(PortaSerial portaSerialUsuario) {
		this.portaSerialUsuario = portaSerialUsuario;
	}

	public Button getEnviarDadosButton() {
		return enviarDadosButton;
	}

	public void setEnviarDadosButton(Button enviarDadosButton) {
		this.enviarDadosButton = enviarDadosButton;
	}

	public TextField getEnviarDadosTextField() {
		return enviarDadosTextField;
	}

	public void setEnviarDadosTextField(TextField enviarDadosTextField) {
		this.enviarDadosTextField = enviarDadosTextField;
	}

	public TextArea getRecepcaoSerialTextArea() {
		return recepcaoSerialTextArea;
	}

	public void setRecepcaoSerialTextArea(TextArea recepcaoSerialTextArea) {
		this.recepcaoSerialTextArea = recepcaoSerialTextArea;
	}

}
