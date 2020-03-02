/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class DialogWindow {

    public static void InfoDialog(String title, String text, String header) {
        Alert window = new Alert(Alert.AlertType.INFORMATION);
        if (title != null) {
            window.setTitle(title);
        }

        if (text != null) {
            window.setContentText(text);
        }

        window.setHeaderText(header);

        window.showAndWait();
    }

    public static void ErrorDialog(String title, String text, String header) {
        Alert window = new Alert(Alert.AlertType.ERROR);
        if (title != null) {
            window.setTitle(title);
        }

        if (text != null) {
            window.setContentText(text);
        }

        window.setHeaderText(header);

        window.showAndWait();
    }

    public static String InputDialog(String title) {
        Stage stage = new Stage();
        VBox root = new VBox();
        TextField field = new TextField();
        HBox btns = new HBox();
        Button ok = new Button("OK");
        Button cancel = new Button("Cancel");
        Scene scene = new Scene(root);

        stage.setTitle(title);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setMinWidth(200);
        stage.setMinHeight(2 * field.getHeight());
        stage.setResizable(false);

        EventHandler<ActionEvent> close = (ActionEvent eh) -> stage.close();
        EventHandler<ActionEvent> closeAndFree = (ActionEvent eh) -> {
            field.setText("");
            stage.close();
        };

        field.setOnAction(close);
        field.setMinWidth(200);
        ok.setMinSize(100, field.getHeight());
        ok.setOnAction(close);
        cancel.setMinSize(100, field.getHeight());
        cancel.setOnAction(closeAndFree);

        btns.getChildren().addAll(ok, cancel);
        root.getChildren().addAll(field, btns);

        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);

        stage.showAndWait();

        return field.getText();
    }
}
