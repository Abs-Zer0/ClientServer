/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import javafx.scene.control.Alert;

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
}
