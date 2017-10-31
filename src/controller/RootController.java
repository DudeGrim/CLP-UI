package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class RootController {
@FXML Menu exportMenu;
MainController mc;
private Stage savedStage;

private static final String defaultFileName = "MyFile.txt";
public void setMainController(MainController mc ){
	this.mc = mc;
}

public void handleExport(){
	if(!mc.compile()){
		SoftwareNotification.notifyError("you haven't selected anything yet.");
	}
	ArrayList<String> JSONArrayList; 
	JSONArrayList = mc.getJSONLIST();
	for(int i=0;i<JSONArrayList.size();i++){
		System.out.println(JSONArrayList.get(i));
	}
	System.out.println("exporting");

	showSaveFileChooser();
	
	
	
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
			SoftwareNotification.notifyError("An ERROR occurred while saving the file!" +
					savedFile.toString());
			return;
		}
		
		SoftwareNotification.notifySuccess("File saved: " + savedFile.toString());
	}
	else {
		SoftwareNotification.notifyError("File save cancelled.");
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
