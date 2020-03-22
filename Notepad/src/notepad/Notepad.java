/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Mayar-Elabbasy
 */

public class Notepad extends Application {
    
    @Override
    public void start(Stage primaryStage) {
            FXMLNotepadBase root = new FXMLNotepadBase();
            Scene scene = new Scene(root, 500, 500);
            primaryStage.setTitle("Notepad  made by Mayar Elabbasy");
            primaryStage.setScene(scene);
            primaryStage.setOnCloseRequest(e -> {
                    System.exit(0);
         });
              primaryStage.show();
          }
                
    
    public static void main(String[] args) {
        launch(args);
    }
    
}

