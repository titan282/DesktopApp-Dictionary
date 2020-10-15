package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class RemoveController extends RootController{
    @FXML
    private Button buttonRemove;
    @FXML
    private TextField textRemove;
    @FXML
    void remove(ActionEvent event) {
        RootController x=new RootController();
        x.removeWord("test");
    }

}
