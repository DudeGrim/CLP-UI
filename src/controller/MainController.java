package controller;

import java.net.URL;
import java.util.ResourceBundle;

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
	
	
@FXML
Button xButton;
@FXML AnchorPane anchorPane;
@FXML
Text statusText;

@FXML RadioButton createButtonRadioButton;
@FXML RadioButton createTextFieldRadioButton;
@FXML RadioButton createLabelRadioButton;
@FXML TextField textfield;
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	  ToggleGroup group = new ToggleGroup();
	  createButtonRadioButton.setToggleGroup(group);
	  createTextFieldRadioButton.setToggleGroup(group);
	  createLabelRadioButton.setToggleGroup(group);
	
}
public void handleXButton(){
	System.out.println("xbutton has been clicked.");

	
	createButton("hello");
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
		anchorPane.getChildren().add(makeDraggable(new Button(textfield.getText().toString())));
	}else if(createLabelRadioButton.isSelected()){
		anchorPane.getChildren().add(makeDraggable(new Label(textfield.getText().toString())));
	}else if(createTextFieldRadioButton.isSelected()){
		anchorPane.getChildren().add(makeDraggable(new TextField(textfield.getText().toString())));
	}
	System.out.println("printing: .. ");
	System.out.println("x: " + createButtonRadioButton.getLayoutX());
}

private Node makeDraggable(final Node node) {
    final DragContext dragContext = new DragContext();
    final Group wrapGroup = new Group(node);

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
