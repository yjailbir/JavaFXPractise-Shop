package registration;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {
    @FXML
    private TextField text1;
    @FXML
    private TextField text2;

    @FXML
    private void register() throws IOException {
        User user = new User(text1.getText(), text2.getText());
        if(!user.isTheUserExists()){
            FileWriter writer = new FileWriter("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\UsersData.txt", true);
            writer.write(user.getLogin()+ "\n");
            writer.write(user.getPassword() + "\n");
            writer.close();
            Stage stage = (Stage) text1.getScene().getWindow();
            stage.close();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Пользователь с таким паролем и/или логином уже существует");
            alert.setContentText("Проверьте введенные логин и пароль");
            alert.showAndWait();
        };
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
