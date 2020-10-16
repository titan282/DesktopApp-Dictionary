package controller;

import cmd.*;
import com.darkprograms.speech.translator.GoogleTranslate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    protected ListView<String> listView;
    @FXML
    private TextField searchWord;
    @FXML
    private TextField textRemove;
    @FXML
    private TextField textEnglishAdd;
    @FXML
    private TextArea textVietnameseAdd;
    @FXML
    private TextField textNeedEdit;

    @FXML
    private TextArea textVietnammeseNew;

    @FXML
    private TextField textEnglishNew;
    @FXML
    private Tab tabRemove;
    @FXML
    private TabPane tabPane;
    @FXML
    private TextField textRemoveAlert;
    @FXML
    private TextField textAddAlert;
    @FXML
    private TextField textEditAlert;
    @FXML
    private TextArea textTranslateEnglish;
    @FXML
    private TextArea textTranslateVietnamese;

    public static int countAmountOfChange=0;
    protected ObservableList<String> list = FXCollections.observableArrayList();
    public DictionaryManagement words = new DictionaryManagement();

    public RootController() {
    }

    // Tạo một ListView
    public void initialize(URL url, ResourceBundle resourceBundle) {
        words.insertFromFile();
        loadData();
        searchList();
        words.dictionaryToText();
    }

    public void addWord(String target, String exlain) {
        System.out.println("Hmmm");
    }

    public void removeWord(String target) {
        list.clear();
        listView.setItems(list);
    }

    public void loadData() {
        int size = words.getDictionaryData().getSize();
        for (int i = 0; i < size; i++) {
            list.addAll(words.getDictionaryData().getWord(i).getWord_target());
        }
        listView.setItems(list);
    }

    public void searchList() {
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

    public void displaySelected(MouseEvent mouseEvent) throws NullPointerException {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        String target = listView.getSelectionModel().getSelectedItem();
        if(target!=null) {
            String output = words.dictionaryLookup(target).getWord_explain();
            WebEngine webEngine = webView.getEngine();
            webEngine.loadContent(output);
            String word = listView.getSelectionModel().getSelectedItem();
        }
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
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/remove_panel.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle("Remove word!");
//        stage.setScene(scene);
//        stage.show();
        clearAllText();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(2);
    }

    public void handleAdd(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/add_panel.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle("Add New word !");
//        stage.setScene(scene);
//        stage.show();
        clearAllText();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(1);
    }

    public void handleEdit(MouseEvent mouseEvent) throws IOException {
//        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/edit_panel.fxml"));
//        Scene scene = new Scene(root);
//        Stage stage = new Stage(StageStyle.DECORATED);
//        stage.setTitle("Edit word !");
//        stage.setScene(scene);
//        stage.show();
        clearAllText();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(3);
    }
    public void clearAllText(){
        textRemove.clear();
        textEditAlert.clear();
        textEnglishNew.clear();
        textNeedEdit.clear();
        textVietnameseAdd.clear();
        textEnglishAdd.clear();
        textAddAlert.clear();
        textRemoveAlert.clear();
        textVietnammeseNew.clear();
    }
    public void deteleButton(ActionEvent actionEvent) {
        if(textRemove.getText().equals("")){
            textRemoveAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(words.haveWord(textRemove.getText())){
            Word tempWord=words.dictionaryLookup(textRemove.getText());
            words.removeWord(tempWord.getWord_target());
            list.remove(tempWord.getWord_target());
            listView.setItems(list);
            searchList();
            textRemoveAlert.setText("Xóa từ thành công!");
            countAmountOfChange++;
        }
        else{
            textRemoveAlert.setText("Từ này không có trong từ điển. Vui lòng nhập từ khác!");
        }
        String x = searchWord.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(words.dictionaryLookup(x).getWord_explain());
    }

    public void addWord(ActionEvent actionEvent) {
        if(textEnglishAdd.getText().equals("")||textVietnameseAdd.getText().equals("")){
            textAddAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(words.haveWord(textEnglishAdd.getText())){
            textAddAlert.setText("Từ này đã có trong từ điển rồi. Vui lòng nhập từ khác!");
        }
        else {
            list.add(textEnglishAdd.getText());
            listView.setItems(list);
            words.addWord(textEnglishAdd.getText(), textVietnameseAdd.getText());
            searchList();
            textAddAlert.setText("Thêm từ thành còng!");
            countAmountOfChange++;
        }
        webView.getEngine().reload();
    }

    public void editWord(ActionEvent actionEvent) {
        if(textNeedEdit.getText().equals("")||textEnglishNew.getText().equals("")||textEnglishNew.getText().equals("")){
            textEditAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(!words.haveWord(textNeedEdit.getText())){
            textEditAlert.setText("Từ này không có trong từ điển nên không edit được. Vui lòng nhập từ khác!");
        }
        else {
            Word tempWord = words.dictionaryLookup(textNeedEdit.getText());
            list.remove(tempWord.getWord_target());
            words.removeWord(tempWord.getWord_target());
            list.add(textEnglishNew.getText());
            words.addWord(textEnglishNew.getText(), textVietnammeseNew.getText());
            listView.setItems(list);
            searchList();
            textEditAlert.setText("Edit từ thành công!");
            countAmountOfChange++;
        }
        webView.getEngine().reload();
    }

    public void clickSearchBar(MouseEvent mouseEvent) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        loadWebView();
    }
    public void speakWord(MouseEvent mouseEvent)  {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        loadWebView();
    }

    public void homeClick(MouseEvent mouseEvent) {
        clearAllText();
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(0);
        loadWebView();
    }
    public void loadWebView(){
        String x = searchWord.getText();
        WebEngine webEngine = webView.getEngine();
        if(words.haveWord(x)){
            webEngine.loadContent(words.dictionaryLookup(x).getWord_explain());}
        else{
            webEngine.loadContent("");
        }
    }
    public void helpAction(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/help_panel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Help!");
        stage.setScene(scene);
        stage.show();
    }

    public void selectAddTab(Event event) {
        clearAllText();
    }

    public void selectRemove(Event event) {
        clearAllText();
    }

    public void selectEdit(Event event) {
        clearAllText();
    }

    public void homeAction(Event event) {
        loadWebView();
    }

    public void enterRemove(ActionEvent actionEvent) {
        if(textRemove.getText().equals("")){
            textRemoveAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(words.haveWord(textRemove.getText())){
            Word tempWord=words.dictionaryLookup(textRemove.getText());
            words.removeWord(tempWord.getWord_target());
            list.remove(tempWord.getWord_target());
            listView.setItems(list);
            searchList();
            textRemoveAlert.setText("Xóa từ thành công!");
        }
        else{
            textRemoveAlert.setText("Từ này không có trong từ điển. Vui lòng nhập từ khác!");
        }
        String x = searchWord.getText();
        WebEngine webEngine = webView.getEngine();
        webEngine.loadContent(words.dictionaryLookup(x).getWord_explain());
    }

    public void enterAdd(ActionEvent actionEvent) {
        if(textEnglishAdd.getText().equals("")||textVietnameseAdd.getText().equals("")){
            textAddAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(words.haveWord(textEnglishAdd.getText())){
            textAddAlert.setText("Từ này đã có trong từ điển rồi. Vui lòng nhập từ khác!");
        }
        else {
            list.add(textEnglishAdd.getText());
            listView.setItems(list);
            words.addWord(textEnglishAdd.getText(), textVietnameseAdd.getText());
            searchList();
            textAddAlert.setText("Thêm từ thành còng!");

        }
        webView.getEngine().reload();
    }

    public void enterEdit1(ActionEvent actionEvent) {
        if(textNeedEdit.getText().equals("")||textEnglishNew.getText().equals("")||textEnglishNew.getText().equals("")){
            textEditAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(!words.haveWord(textNeedEdit.getText())){
            textEditAlert.setText("Từ này không có trong từ điển nên không edit được. Vui lòng nhập từ khác!");
        }
        else {
            Word tempWord = words.dictionaryLookup(textNeedEdit.getText());
            list.remove(tempWord.getWord_target());
            words.removeWord(tempWord.getWord_target());
            list.add(textEnglishNew.getText());
            words.addWord(textEnglishNew.getText(), textVietnammeseNew.getText());
            listView.setItems(list);
            searchList();
            textEditAlert.setText("Edit từ thành công!");
        }
        webView.getEngine().reload();
    }

    public void enterEdit2(ActionEvent actionEvent) {
        if(textNeedEdit.getText().equals("")||textEnglishNew.getText().equals("")||textEnglishNew.getText().equals("")){
            textEditAlert.setText("Vui lòng điền đầy đủ thông tin");
        }
        else if(!words.haveWord(textNeedEdit.getText())){
            textEditAlert.setText("Từ này không có trong từ điển nên không edit được. Vui lòng nhập từ khác!");
        }
        else {
            Word tempWord = words.dictionaryLookup(textNeedEdit.getText());
            list.remove(tempWord.getWord_target());
            words.removeWord(tempWord.getWord_target());
            list.add(textEnglishNew.getText());
            words.addWord(textEnglishNew.getText(), textVietnammeseNew.getText());
            listView.setItems(list);
            searchList();
            textEditAlert.setText("Edit từ thành công!");
        }
        webView.getEngine().reload();
    }

    public void translateTab(MouseEvent mouseEvent) {
        SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
        selectionModel.select(4);
    }

    public void typeEnglish(KeyEvent keyEvent) throws IOException {
        textTranslateVietnamese.setText(GoogleTranslate.translate("en","vi",textTranslateEnglish.getText()));
    }

    public void updateData(MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../resources/panel/update_panel.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.setTitle("Update status!");
        stage.setScene(scene);
        stage.show();
        words.dictionaryToText();
    }
}


