package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SampleController implements Initializable {
    @FXML
    private TextField textField1;
    @FXML
    private TextField textField2;
    @FXML
    private Label label1;

    @FXML
    private void handleButtonAction() throws IOException {
        User user = new User(textField1.getText(), textField2.getText());
        if(user.getLogin().equals("admin") && user.getPassword().equals("admin")){
            Parent root = FXMLLoader.load(getClass().getResource("../menu/menu.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Каталог");
            stage.setScene(new Scene(root, 600, 500));
            Stage stagetmp = (Stage) textField1.getScene().getWindow();
            stagetmp.close();
            stage.setResizable(true);
            stage.show();
        }
        else if(user.isTheUserExists()){
            Parent root = FXMLLoader.load(getClass().getResource("../catalog/catalog.fxml"));
            Stage stage = new Stage();
            stage.setResizable(false);
            stage.setTitle("Каталог");
            stage.setScene(new Scene(root, 600, 500));
            Stage stagetmp = (Stage) textField1.getScene().getWindow();
            stagetmp.close();
            stage.setResizable(true);
            stage.show();
        }
        else
            label1.setText("Пользователь\nне существует");

    }

    @FXML
    private void handleLinkAction() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../registration/registration.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Регистрация");
        stage.setScene(new Scene(root, 600, 500));
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void  handleCancel(){
        textField1.setText("");
        textField2.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }




}
