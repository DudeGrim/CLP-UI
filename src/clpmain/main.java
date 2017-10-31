package clpmain;

import java.io.IOException;

import controller.MainController;
import controller.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class main extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    RootController rootController;
    	MainController mainController;
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("UI Parser Demo");
        primaryStage.getIcons().add(new Image("Lannister.png"));//cause Lannister.
        initRootLayout();
        
        showMain();
        
        rootController.setMainController(mainController);
//        showCreator()
    }

    /**
     * Initializes the root layout.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/view/RootLayout.fxml"));
    		
//    		controller.ini
//            loader.setController(main.class.getResource("/controller/RootController.java"));
            rootLayout = (BorderPane) loader.load();
            rootLayout.setPadding(new Insets(5));
            // Show the scene containing the root layout.
            rootController =
    				loader.<RootController>getController();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
           
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Shows the person overview inside the root layout.
     */
    public void showMain() {
        try {
            // Load person overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(main.class.getResource("/view/Main.fxml"));
          
//            loader.setController(main.class.getResource("/controller/MainController.java"));
            AnchorPane main = (AnchorPane) loader.load();
          
//            rootController.setMainController(mainController);
            // Set person overview into the center of root layout.
             mainController =
    				loader.getController();  
             
             
//             System.out.println(mainController.toString());
            rootLayout.setCenter(main);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showCreator(){
    	
    	try{
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(main.class.getResource("/view/Creator.fxml"));
            loader.setController(main.class.getResource("/controller/CreatorController.java"));

            AnchorPane creator = (AnchorPane) loader.load();

            // Set person overview into the center of root layout.
            rootLayout.setBottom(creator);
    		
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    /**
     * Returns the main stage.
     * @return
     */
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}