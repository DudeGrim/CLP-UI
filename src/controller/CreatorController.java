package controller;

import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CreatorController {
	@FXML RadioButton createButtonRadioButton;
	@FXML RadioButton createTextFieldRadioButton;
	
	@FXML TextField textfield;
	
	
	public void handleButton(){
		MainController.getInstance().createButton("tex111t");;
		
	}
}
