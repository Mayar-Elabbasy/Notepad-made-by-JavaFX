/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.AnchorPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.stage.FileChooser;

/**
 *
 * @author Mayar-Elabbasy
 */

public  class FXMLNotepadBase extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final TextArea textArea;
    protected final MenuBar menuBar;
    protected final Menu fileMenu;
    protected final MenuItem new1;
    protected final MenuItem open;
    protected final MenuItem save;
    protected final MenuItem exit;
    protected final Menu editMenu;
    protected final MenuItem cut;
    protected final MenuItem copy;
    protected final MenuItem paste;
    protected final MenuItem delete;
    protected final MenuItem selectAll;
    protected final Menu helpMenu;
    protected final MenuItem about;
    protected String fileName;

    public FXMLNotepadBase() {

        anchorPane = new AnchorPane();
        textArea = new TextArea();
        menuBar = new MenuBar();
        fileMenu = new Menu();
        new1 = new MenuItem();
        open = new MenuItem();
        save = new MenuItem();
        exit = new MenuItem();
        editMenu = new Menu();
        cut = new MenuItem();
        copy = new MenuItem();
        paste = new MenuItem();
        delete = new MenuItem();
        selectAll = new MenuItem();
        helpMenu = new Menu();
        about = new MenuItem();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        AnchorPane.setBottomAnchor(anchorPane, 0.0);
        AnchorPane.setLeftAnchor(anchorPane, 0.0);
        AnchorPane.setRightAnchor(anchorPane, 0.0);
        AnchorPane.setTopAnchor(anchorPane, 0.0);
        anchorPane.setPrefHeight(200.0);
        anchorPane.setPrefWidth(200.0);
        anchorPane.setOpaqueInsets(new Insets(0.0));

        AnchorPane.setBottomAnchor(textArea, 0.0);
        AnchorPane.setLeftAnchor(textArea, 0.0);
        AnchorPane.setRightAnchor(textArea, 0.0);
        AnchorPane.setTopAnchor(textArea, 24.0);
        textArea.setLayoutX(-1.0);
        textArea.setLayoutY(24.0);
        textArea.setPrefHeight(376.0);
        textArea.setPrefWidth(600.0);
  
        // ====================save file Ctrl+S======================
        save.setOnAction(ActionEvent-> {
                FileChooser fc=new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fc.getExtensionFilters().add(extFilter);
                File saveFile = fc.showSaveDialog(null);
                try {
                    FileWriter fw=new FileWriter(saveFile);
                    fw.write(textArea.getText());
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(FXMLNotepadBase.class.getName()).log(Level.SEVERE, null, ex);
                }

                Image fileSavedIcon = new Image(getClass().getResourceAsStream("fileSaved.png"));
                ImageView imageView2 = new ImageView(fileSavedIcon);
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Saved");
                alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.rgb(160, 255, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                alert.getDialogPane().setGraphic(imageView2);
                alert.getDialogPane().setCursor(Cursor.HAND);
                alert.getDialogPane().setStyle("-fx-padding: 10px;   -fx-font-size: 25px;  -fx-font-weight:bold;  -fx-font-family: \"Berlin Sans FB Demi\";");
                alert.setHeaderText("You saved the file successfully ^_^");
                alert.showAndWait();
                System.out.println("You saved the file successfully ^_^");
    
    });
        
        
       // ====================new file Ctrl+N======================
        new1.setOnAction(ActionEvent-> {
                Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
               alert.setTitle("Confirmation");
               Image clearIcon = new Image(getClass().getResourceAsStream("clear.png"));
               ImageView imageView2 = new ImageView(clearIcon);
               alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.rgb(160, 255, 230), CornerRadii.EMPTY, Insets.EMPTY)));
               alert.getDialogPane().setGraphic(imageView2);
               alert.getDialogPane().setCursor(Cursor.HAND);
               alert.getDialogPane().setStyle("-fx-padding: 10px;   -fx-font-size: 25px;  -fx-font-weight:bold;  -fx-font-family: \"Berlin Sans FB Demi\";");
               alert.setHeaderText("Do you want to clear this file?");
               Optional<ButtonType> result = alert.showAndWait();
                  if (result.get() == ButtonType.OK){
                             textArea.setText("");
                             System.out.println("You cleared the file successfully ^_^");
                   } else if (result.get() == ButtonType.CANCEL) {
                               textArea.getText();
                                System.out.println("cancel pressed^_^");
                   }
    });
       
       
       // ====================open file Ctrl+O======================
        open.setOnAction(ActionEvent-> {
                int letters;
                FileChooser fc2=new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
                fc2.getExtensionFilters().add(extFilter);

                File openFile = fc2.showOpenDialog(null);
                    try {
                        FileReader fr=new FileReader(openFile);
                        textArea.setText("");

                        while((letters=fr.read())!=-1)
                                {
                                textArea.appendText(String.valueOf((char)letters));
                                }
                    } catch (FileNotFoundException ex) {
                        Logger.getLogger(FXMLNotepadBase.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(FXMLNotepadBase.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
    
        // ====================exit  Ctrl+E======================
        exit.setOnAction(ActionEvent-> {
                 Alert alert;
                alert = new Alert(AlertType.CONFIRMATION);
                Image exitIcon = new Image(getClass().getResourceAsStream("exit.png"));
                ImageView imageView2 = new ImageView(exitIcon);
                alert.getDialogPane().setBackground(new Background(new BackgroundFill(Color.rgb(160, 255, 230), CornerRadii.EMPTY, Insets.EMPTY)));
                alert.getDialogPane().setGraphic(imageView2);
                alert.getDialogPane().setCursor(Cursor.HAND);
                alert.getDialogPane().setStyle("-fx-padding: 10px;   -fx-font-size: 25px;  -fx-font-weight:bold;  -fx-font-family: \"Berlin Sans FB Demi\";");
                alert.setTitle("Confirmation");
                alert.setHeaderText("Are you sure you want to exit?");
                Optional<ButtonType> result = alert.showAndWait();
                   if (result.get() == ButtonType.OK){
                            System.exit(0);
                            System.out.println("You have  exited  ^_^");
            } else if (result.get() == ButtonType.CANCEL) {
                             textArea.getText();
            }
       
            });
        
        //==============copy===============================
        copy.setOnAction(ActionEvent-> {
             textArea.copy();
            });
        
        //==============paste=============================== 
          paste.setOnAction(ActionEvent-> {
             textArea.paste();
            });
          
           //==============cut===============================
             cut.setOnAction(ActionEvent-> {
             textArea.cut();
            });
             
            //==============delete===============================
            delete.setOnAction(ActionEvent-> {
                        textArea.setText(textArea.getText().replace(textArea.getSelectedText(),""));
            });
             
             //==============selectAll===============================
             selectAll.setOnAction(ActionEvent-> {
             textArea.selectAll();
            });
        
           //===================aboutMe=========================
           about.setOnAction(ActionEvent-> {
               Image image = new Image(getClass().getResourceAsStream("pp.jfif"));
               ImageView imageView = new ImageView(image);
               Alert alert = new Alert(AlertType.INFORMATION);
               alert.setTitle("                                                         Welcome");
               alert.setGraphic(imageView);
               alert.setHeaderText(null);
               LinearGradient lg1;
                lg1 = new LinearGradient(0, 1, 0.5, 0, true, CycleMethod.NO_CYCLE, new Stop(0f,Color.TAN), new Stop(1.5f,Color.GOLD), new Stop(0.5f,Color.WHITE), new Stop(1f,Color.TAN));
                alert.getDialogPane().setBackground(new Background(new BackgroundFill(lg1, CornerRadii.EMPTY, Insets.EMPTY)));
                alert.getDialogPane().setCursor(Cursor.HAND);
                alert.getDialogPane().setStyle("-fx-padding: 10px; -fx-font-size: 30px;  -fx-font-weight:bold;  -fx-font-family: \"Berlin Sans FB Demi\"; -fx-text-fill: green;");
                alert.getDialogPane().setBorder(new Border(new BorderStroke(Color.BROWN, BorderStrokeStyle.SOLID, null, new BorderWidths(5))));
                alert.setContentText( "JavaFx Notepad made by Mayar Elabbasy");
                imageView.setX(0);
                imageView.setY(0);
                imageView.setFitHeight(500);
                imageView.setFitWidth(500);
                imageView.setPreserveRatio(true);
                alert.showAndWait();
            });
    
   
     
        AnchorPane.setLeftAnchor(menuBar, 0.0);
        AnchorPane.setRightAnchor(menuBar, 0.0);
        AnchorPane.setTopAnchor(menuBar, 0.0);

        fileMenu.setMnemonicParsing(false);
        fileMenu.setText("File");

        new1.setMnemonicParsing(false);
        new1.getStyleClass().add("new");
        new1.setText("New");
        new1.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN));

        open.setMnemonicParsing(false);
        open.getStyleClass().add("open");
        open.setText("Open");
        open.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN));

        save.setMnemonicParsing(false);
        save.getStyleClass().add("save");
        save.setText("Save");
        save.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN));

        exit.setMnemonicParsing(false);
        exit.setText("Exit");
        exit.setAccelerator(new KeyCodeCombination(KeyCode.E, KeyCombination.CONTROL_DOWN));

        editMenu.setMnemonicParsing(false);
        editMenu.setText("Edit");

        cut.setMnemonicParsing(false);
        cut.setText("Cut");

        copy.setMnemonicParsing(false);
        copy.setText("Copy");

        paste.setMnemonicParsing(false);
        paste.setText("Paste");

        delete.setMnemonicParsing(false);
        delete.setText("Delete");

        selectAll.setMnemonicParsing(false);
        selectAll.setText("Select All");

        helpMenu.setMnemonicParsing(false);
        helpMenu.setText("Help");

        about.setMnemonicParsing(false);
        about.setText("About");

        anchorPane.getChildren().add(textArea);
        getChildren().addAll(anchorPane, menuBar);
        fileMenu.getItems().addAll(new1, open, save, exit );
        menuBar.getMenus().addAll(fileMenu, editMenu, helpMenu);
        editMenu.getItems().addAll(cut, copy, paste, delete, selectAll);
        helpMenu.getItems().add(about);
    }
}
   