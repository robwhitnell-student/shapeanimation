package edu.guilford;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class ShapeAnimation extends Application {

    private static Scene scene;

    // Each JavaFX application provides a Stage, a window for the application
    @Override
    public void start(Stage stage) throws IOException {

        final int WIDTH = (int)Screen.getPrimary().getBounds().getWidth() / 2;
        final int HEIGHT = (int)Screen.getPrimary().getBounds().getHeight() / 2;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("control.fxml"));
        Parent root = loader.load(); // load the FXML file
        scene = new Scene(root, 640, 200);
        stage.setScene(scene);
        stage.show();
        stage.setX(0);
        stage.setY(100);

        // Let's build a new Stage/Scene with ShapePane
        // and set it as the root of the scene
        Stage shapeStage = new Stage();
        shapeStage.setTitle("Shape Display");
        ShapePane shapePane = new ShapePane(WIDTH, HEIGHT);
        // Instantiate a ShapeList and generate shapes
        ShapeList shapeList = new ShapeList(WIDTH, HEIGHT);
        shapeList.generateShapes(50);
        shapePane.setShapeList(shapeList); 
        shapePane.updateShapes(); 
        Scene shapeScene = new Scene(shapePane, WIDTH, HEIGHT);
        shapeStage.setScene(shapeScene);
        shapeStage.show();

        // Get the ControlController object and tell it about the ShapeList and ShapePane
        ControlController controlController = (ControlController) loader.getController();
        controlController.setShapeList(shapeList);
        controlController.setShapePane(shapePane);
        shapePane.setControlController(controlController); // set the ControlController to the ShapePane
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ShapeAnimation.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}