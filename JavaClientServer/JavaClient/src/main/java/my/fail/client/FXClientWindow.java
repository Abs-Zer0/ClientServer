/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package my.fail.client;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 *
 * @author ���0������ �0��
 */
public class FXClientWindow extends Application {

    public void Init() {
        this.root = new VBox();
        this.ioView = new HBox();
        this.inputView = new ListView<>();
        try {
            this.img = new RelativeImageView("src/main/resources/Enet.png");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FXClientWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.outputView = new ListView<>();
        this.msg = new TextField();
        this.btns = new HBox();
        this.stopBtn = new Button("Stop");
        this.connectBtn = new Button("Connect");

        this.client = new ClientManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.Init();
        stage.setTitle("Java for client");
        stage.setOnCloseRequest((WindowEvent event) -> this.exit());

        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setWidth(800);
        stage.setHeight(600);

        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) -> this.onResize(stage);
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener);

        Scene scene = new Scene(this.root);
        scene.setFill(Color.LIGHTGRAY);
        stage.setScene(scene);

        this.ioView.getChildren().addAll(this.inputView, this.img, this.outputView);
        this.btns.getChildren().addAll(this.stopBtn, this.connectBtn);
        this.root.getChildren().addAll(this.ioView, this.msg, this.btns);

        this.btns.setSpacing(20.0);
        this.btns.setPadding(new Insets(10.0));

        this.inputView.setOnScroll((ScrollEvent eh) -> inputScroll());
        this.outputView.setOnScroll((ScrollEvent eh) -> outputScroll());
        this.msg.setOnAction((ActionEvent eh) -> this.startSession());
        this.stopBtn.setOnAction((ActionEvent eh) -> this.stopClient());
        this.connectBtn.setOnAction((ActionEvent t) -> this.connectClient());

        stage.show();

        this.onResize(stage);
    }

    private void exit() {
        try {
            this.client.close();
        } catch (IOException ex) {
            Logger.getLogger(FXClientWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void onResize(Stage stage) {
        this.root.setPrefSize(stage.getWidth(), stage.getHeight());
        this.resizeIO();
        this.msg.setPrefSize(this.root.getWidth(), 0.08 * this.root.getWidth());
        this.resizeBtns();
    }

    private void resizeIO() {
        this.ioView.setPrefSize(this.root.getWidth(), 0.8 * this.root.getHeight());
        this.inputView.setPrefSize(0.4 * this.ioView.getWidth(), this.ioView.getHeight());
        this.img.setWidth(0.2 * this.ioView.getWidth());
        this.outputView.setPrefSize(0.4 * this.ioView.getWidth(), this.ioView.getHeight());
    }

    private void resizeBtns() {
        this.btns.setPrefSize(this.root.getWidth(), 0.12 * this.root.getHeight());
        this.stopBtn.setPrefSize(this.btns.getWidth() / 2 - 20.0, this.btns.getHeight() - 20.0);
        this.connectBtn.setPrefSize(this.btns.getWidth() / 2 - 20.0, this.btns.getHeight() - 20.0);
    }

    private void startSession() {
        String text = this.msg.getText();
        this.msg.clear();

        try {
            String result = this.client.StartSession(text);
            this.inputView.getItems().add(text);
            this.outputView.getItems().add(result);
        } catch (IOException ex) {
            DialogWindow.ErrorDialog("Error", ex.getMessage(), null);
        }
    }

    private void connectClient() {
        try {
            this.client.Connect("127.0.0.1", "4004");

            DialogWindow.InfoDialog("Successful", "Connected to server", null);
        } catch (IOException ex) {
            Logger.getLogger(FXClientWindow.class.getName()).log(Level.SEVERE, null, ex);

            DialogWindow.ErrorDialog("Error", ex.getMessage(), null);
        }
    }

    private void stopClient() {
        try {
            this.client.Stop();

            DialogWindow.InfoDialog("Successful", "Client has stopped", null);
        } catch (IOException ex) {
            Logger.getLogger(FXClientWindow.class.getName()).log(Level.SEVERE, null, ex);

            DialogWindow.ErrorDialog("Error", ex.getMessage(), null);
        }
    }

    private void inputScroll() {
        System.out.println("scroll input");
    }

    private void outputScroll() {
        System.out.println("scroll output");
    }

    private VBox root;
    private HBox ioView;
    private ListView<String> inputView;
    private RelativeImageView img;
    private ListView<String> outputView;
    private TextField msg;
    private HBox btns;
    private Button stopBtn;
    private Button connectBtn;

    private ClientManager client;
}
