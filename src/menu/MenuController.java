package menu;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML
    private Tab catalogTab;
    @FXML
    private Tab ordersTab;
    @FXML
    private TabPane tabPane;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../catalog/catalog.fxml"));
            catalogTab.setContent(loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void selectCatalogTab(){
        tabPane.getSelectionModel().select(catalogTab);
    }
    @FXML
    private void selectOrderTab(){tabPane.getSelectionModel().select(ordersTab);}
    @FXML
    private void selectCatalogInNewTab() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../catalog/catalog.fxml"));
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.setTitle("Каталог");
        stage.setScene(new Scene(root, 600, 500));
        stage.setResizable(true);
        stage.show();
    }
    @FXML
    private void info(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initOwner(null);
        alert.setTitle("О программе");
        alert.setHeaderText("");
        alert.setContentText("Программа учебная\nВерсия 1.0");
        alert.showAndWait();
    }
}
