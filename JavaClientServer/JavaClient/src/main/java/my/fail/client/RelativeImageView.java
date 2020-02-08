/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Абс0лютный Н0ль
 */
public class RelativeImageView extends ImageView {

    private final double relative;

    public RelativeImageView(String path) throws FileNotFoundException {
        super(new Image(new FileInputStream(new File(path))));
        this.relative = this.getImage().getWidth() / this.getImage().getHeight();
    }

    public void setWidth(double val) {
        this.setFitWidth(val);
        this.setFitHeight(val / this.relative);
    }

    public void setHeight(double val) {
        this.setFitHeight(val);
        this.setFitWidth(val * this.relative);
    }
}
