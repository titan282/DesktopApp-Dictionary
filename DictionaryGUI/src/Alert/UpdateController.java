package Alert;

import controller.RootController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UpdateController implements Initializable {
    @FXML
    private Button button;
    @FXML
    private TextField status;
    public void exitAlert(ActionEvent actionEvent) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        int count= RootController.countAmountOfChange;
        if(count>0){
            status.setText("Update successful!");
        }
        else status.setText("Chưa có thay đổi gì !");
    }
}
