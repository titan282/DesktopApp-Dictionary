package controller;

import cmd.*;;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RootController implements Initializable {
    @FXML
    private WebView webView;
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField searchWord;
    ObservableList<String> list = FXCollections.observableArrayList();
    DictionaryManagement words = new DictionaryManagement();

    // Tạo một ListView
    public void initialize(URL url, ResourceBundle resourceBundle) {
        words.insertFromFile();
        loadData();
        searchList();
    }

    public void addWord(String target, String exlain) {

    }

    public void removeWord(String target) {
        words.removeWord(target);
        list.removeAll(target);
        listView.getItems().remove(list);
    }

    public void loadData() {
        int size = words.getDictionaryData().getSize();
        list.clear();
        for (int i = 0; i < size; i++) {
            list.addAll(words.getDictionaryData().getWord(i).getWord_target());
        }
        listView.setItems(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
    public void searchList(){
        FilteredList<String> filteredData = new FilteredList<>(list, p -> true);

        //Set the filter Predicate whenever the filter changes.
        searchWord.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(client -> {
                // If filter text is empty, display all persons.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                // Compare first name and last name of every client with filter text.
                String lowerCaseFilter = newValue.toLowerCase();

                if (client.toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches first name
                } else if (client.toLowerCase().contains(lowerCaseFilter)) {
                    return true; //filter matches last name
                }
                return false; //Does not match
            });
        });
        //Wrap the FilteredList in a SortedList.
        SortedList<String> sortedData = new SortedList<>(filteredData);
        //put the sorted list into the listview
        listView.setItems(sortedData);

    }
    public void displaySelected(MouseEvent mouseEvent) {
        String target = listView.getSelectionModel().getSelectedItem();
        String output = words.dictionaryLookup(target).getWord_explain();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(output);
        searchWord.setText(target);
    }

    public void displayPressed(KeyEvent keyEvent) {
        String target = listView.getSelectionModel().getSelectedItem();
        String output = words.dictionaryLookup(target).getWord_explain();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(output);
    }


    public void handle(ActionEvent actionEvent) {
        String x = searchWord.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(words.dictionaryLookup(x).getWord_explain());
    }

    public void searchWordType(KeyEvent keyEvent) {
        String x = searchWord.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(words.dictionaryLookup(x).getWord_explain());
    }

    public void handleRemove(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/remove_panel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Remove word !");
        stage.setScene(scene);
        stage.show();
    }

    public void handleAdd(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/add_panel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Add New word !");
        stage.setScene(scene);
        stage.show();
    }

    public void handleEdit(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/edit_panel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Edit word !");
        stage.setScene(scene);
        stage.show();
    }

    public void speakWord(MouseEvent mouseEvent) throws IOException, javazoom.jl.decoder.JavaLayerException {
//        Audio audio = Audio.getInstance();
//        InputStream sound = audio.getAudio("Hello", "en");
//        audio.play(sound);
    }
}
