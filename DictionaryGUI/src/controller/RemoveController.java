package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;

public class RemoveController {
    @FXML
    private Button buttonRemove;
    @FXML
    private TextField textRemove;

    @FXML
    void remove(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resources/panel/root_panel.fxml"));
        Parent parent=loader.load();
        RootController controller = loader.getController();
        controller.removeWord(textRemove.getText());
    }

}
