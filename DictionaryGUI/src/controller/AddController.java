package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;

public class AddController {

    @FXML
    private TextField english;

    @FXML
    private TextArea vietnamese;


    public void addWord(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/panel/root_panel.fxml"));
        Parent parent=loader.load();
        RootController controller = loader.getController();
        controller.addWord(english.getText(), vietnamese.getText());
    }
}
