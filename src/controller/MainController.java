package controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Stack;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class MainController implements Initializable{
	
	final String BUTTON = "class javafx.scene.control.Button";
	final String LABEL = "class javafx.scene.control.Label";
	final String TEXTFIELD = "class javafx.scene.control.TextField";
	
	final String BUTTONSTRING = "BUTTON";
	final String LABELSTRING = "LABEL";
	final String TEXTFIELDSTRING = "TEXTFIELD";
@FXML
Button xButton;
@FXML AnchorPane anchorPane;
@FXML
Text statusText;

@FXML RadioButton createButtonRadioButton;
@FXML RadioButton createTextFieldRadioButton;
@FXML RadioButton createLabelRadioButton;
@FXML TextField textfield;
Stack st;
ArrayList<String> JSONArrayList;
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	  ToggleGroup group = new ToggleGroup();
	  createButtonRadioButton.setToggleGroup(group);
	  createTextFieldRadioButton.setToggleGroup(group);
	  createLabelRadioButton.setToggleGroup(group);
	  st = new Stack();
	  JSONArrayList = new ArrayList<>();
	
}
public ArrayList<String> getJSONLIST(){
	return this.JSONArrayList;
}
public void handleXButton(){
	System.out.println("xbutton has been clicked.");
//	Node n = (Node) st.pop();
//	System.out.println(n.getClass());
//	System.out.println(n.getAccessibleText());
//	Button b = (Button)n;
//	System.out.println(b.getText().toString());
//	System.out.println("x: "+b.getLayoutX() +"y: "+b.getLayoutY());
//	createButton("hello");
	System.out.println("anchor pane size:" +anchorPane.getChildren().size() );
	for(int i=0;i<anchorPane.getChildren().size();i++){
		Node n = anchorPane.getChildren().get(i);
		Group g = (Group) n;
		String comparison = "" + g.getChildren().get(0).getClass();
		
		if(comparison.equals("class javafx.scene.control.Button")){
			Button b= (Button) g.getChildren().get(0);
			
			JSONArrayList.add(createJSON(BUTTONSTRING,b.getTranslateX(),b.getTranslateY(),b.getText().toString()));
		}else if(comparison.equals(LABEL)){
			Label l = (Label) g.getChildren().get(0);
			JSONArrayList.add(createJSON(LABELSTRING,l.getTranslateX(),l.getTranslateY(),l.getText().toString()));
		}else if(comparison.equals(TEXTFIELD)){
			TextField tf = (TextField) g.getChildren().get(0);
			JSONArrayList.add(createJSON(TEXTFIELDSTRING,tf.getTranslateX(),tf.getTranslateY(),tf.getText().toString()));
		}
	}
	
	

//	Label b = (Label) g.getChildren().get(0);
	System.out.println("finished function");
//	System.out.println(b.getText().toString());
//	System.out.println("x: "+b.getTranslateX() +"y: "+b.getTranslateY());
}
private String createJSON(String type, double translateX, double translateY, String text) {
	String jsonString = new JSONObject()
            .put("TYPE", type)
            .put("TEXT", text)
            .put("COORDINATES", new JSONObject()
                 .put("X", translateX)
                 .put("Y", translateY)).toString();

	System.out.println("generated JSON: " +jsonString);
	
	return jsonString;
	
}
private static MainController instance = null;

public static MainController getInstance() {
   if(instance == null) {
      instance = new MainController();
   }
   return instance;
}

public void createButton(String text){
	 final Node loginPanel =
             makeDraggable(new Button(text));
	anchorPane.getChildren().add(loginPanel);
}
public void displayPosition(MouseEvent event){
	statusText.setText("X = " +event.getX()+" Y= " +event.getY());
	
}
public void handleCreateButton(){
	if (createButtonRadioButton.isSelected()){
		Button b1 = new Button(textfield.getText().toString());
		anchorPane.getChildren().add(makeDraggable(b1));
		System.out.println("button initialized: "+b1.getText().toString());
		st.push(b1);
	}else if(createLabelRadioButton.isSelected()){
		Label l1 = new Label(textfield.getText().toString());
		anchorPane.getChildren().add(makeDraggable(l1));
		st.push(l1);
	}else if(createTextFieldRadioButton.isSelected()){
		anchorPane.getChildren().add(makeDraggable(new TextField(textfield.getText().toString())));
	}
	System.out.println("printing: .. ");
	System.out.println("x: " + createButtonRadioButton.getLayoutX());

}
static void showpop(Stack st) {
    System.out.print("pop -> ");
    Integer a = (Integer) st.pop();
    System.out.println(a);
    System.out.println("stack: " + st);
 }
private Node makeDraggable(final Node node) {
    DragContext dragContext = new DragContext();
    Group wrapGroup = new Group(node);
    
   wrapGroup.addEventFilter(
           MouseEvent.ANY,
           new EventHandler<MouseEvent>() {
               public void handle(final MouseEvent mouseEvent) {
                       // disable mouse events for all children
                       mouseEvent.consume();
               }
           });

   wrapGroup.addEventFilter(
           MouseEvent.MOUSE_PRESSED,
           new EventHandler<MouseEvent>() {
               public void handle(final MouseEvent mouseEvent) {
                       // remember initial mouse cursor coordinates
                       // and node position
                       dragContext.mouseAnchorX = mouseEvent.getX();
                       dragContext.mouseAnchorY = mouseEvent.getY();
                       dragContext.initialTranslateX =
                               node.getTranslateX();
                       dragContext.initialTranslateY =
                               node.getTranslateY();
                                   }
           });

   wrapGroup.addEventFilter(
           MouseEvent.MOUSE_DRAGGED,
           new EventHandler<MouseEvent>() {
               public void handle(final MouseEvent mouseEvent) {
                       // shift node from its initial position by delta
                       // calculated from mouse cursor movement
                       node.setTranslateX(
                               dragContext.initialTranslateX
                                   + mouseEvent.getX()
                                   - dragContext.mouseAnchorX);
                       node.setTranslateY(
                               dragContext.initialTranslateY
                                   + mouseEvent.getY()
                                   - dragContext.mouseAnchorY);
                                   }
           });
           
   return wrapGroup;
}
private static final class DragContext {
    public double mouseAnchorX;
    public double mouseAnchorY;
    public double initialTranslateX;
    public double initialTranslateY;
}

}
