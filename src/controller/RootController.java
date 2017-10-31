package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RootController {
@FXML Menu exportMenu;
MainController mc;
private Text actionStatus;
private Stage savedStage;

private BorderPane rootLayout;
private TextArea txtArea;
private static final String titleTxt = "JavaFX File Chooser Example 2";
private static final String defaultFileName = "MyFile.txt";
public void setMainController(MainController mc ){
	this.mc = mc;
}

public void setRootLayout( BorderPane rootLayout){
	this.rootLayout = rootLayout;
}
public void handleExport(){
	ArrayList<String> JSONArrayList; 
	JSONArrayList = mc.getJSONLIST();
	for(int i=0;i<JSONArrayList.size();i++){
		System.out.println(JSONArrayList.get(i));
	}
	System.out.println("exporting");
	
	
	
	// Window label
	Label label = new Label("Save File Chooser");
	label.setTextFill(Color.DARKBLUE);
	label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
	HBox labelHb = new HBox();
	labelHb.setAlignment(Pos.CENTER);
	labelHb.getChildren().add(label);
	
		// Text area in a scrollpane and label
	Label txtAreaLabel = new Label("Enter text and save as a file:");
	txtAreaLabel.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
	txtArea = new TextArea();
	txtArea.setWrapText(true);
	ScrollPane scroll = new ScrollPane();
	scroll.setContent(txtArea);
	scroll.setFitToWidth(true);
	scroll.setFitToHeight(true);
	scroll.setPrefHeight(150);
	scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
	scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
	
	VBox txtAreaVbox = new VBox(5);
	txtAreaVbox.setPadding(new Insets(5, 5, 5, 5));
	txtAreaVbox.getChildren().addAll(txtAreaLabel, scroll);

	// Button
	Button btn1 = new Button("Save as file...");
	btn1.setOnAction(new SaveButtonListener());
	HBox buttonHb1 = new HBox(10);
	buttonHb1.setAlignment(Pos.CENTER);
	buttonHb1.getChildren().addAll(btn1);

	// Status message text
	actionStatus = new Text();
	actionStatus.setFont(Font.font("Calibri", FontWeight.NORMAL, 20));
	actionStatus.setFill(Color.FIREBRICK);

	// Vbox
	VBox vbox = new VBox(30);
	vbox.setPadding(new Insets(25, 25, 25, 25));
	vbox.getChildren().addAll(labelHb, txtAreaVbox, buttonHb1, actionStatus);
	
//	rootLayout.setRight(vbox);
	showSaveFileChooser();
	
	
	
}


private class SaveButtonListener implements EventHandler<ActionEvent> {

	@Override
	public void handle(ActionEvent e) {

		showSaveFileChooser();
	}
}

private void showSaveFileChooser() {

	FileChooser fileChooser = new FileChooser();
	fileChooser.setTitle("Save file");
	fileChooser.setInitialFileName(defaultFileName);
	File savedFile = fileChooser.showSaveDialog(savedStage);

	if (savedFile != null) {

		try {
			saveFileRoutine(savedFile);
		}
		catch(IOException e) {
		
			e.printStackTrace();
			actionStatus.setText("An ERROR occurred while saving the file!" +
					savedFile.toString());
			return;
		}
		
		actionStatus.setText("File saved: " + savedFile.toString());
	}
	else {
		actionStatus.setText("File save cancelled.");
	}
}

private void saveFileRoutine(File file)
		throws IOException{
	// Creates a new file and writes the txtArea contents into it

	String txt = "";
	ArrayList<String> JSONArrayList; 
	JSONArrayList = mc.getJSONLIST();
	for(int i=0;i<JSONArrayList.size();i++){
		System.out.println(JSONArrayList.get(i));
		txt = txt +JSONArrayList.get(i)+"";
		
	}
	
	
	
	file.createNewFile();
	FileWriter writer = new FileWriter(file);
	writer.write(txt);
	writer.close();
}

}
