package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class AddController {

    @FXML
    private TextField english;

    @FXML
    private TextArea vietnamese;


    public void addWord(MouseEvent mouseEvent) throws IOException {
        RootController x=new RootController();
        x.addWord("f","f");
    }
}
