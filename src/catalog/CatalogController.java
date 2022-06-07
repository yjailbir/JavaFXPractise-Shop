package catalog;

import edit.EditController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Product;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class CatalogController implements Initializable {
    private final ObservableList<Product> productData = FXCollections.observableArrayList();
    @FXML
    private TableView<Product> catalogTable;
    @FXML
    private TableColumn<Product, String> productId;
    @FXML
    private TableColumn<Product, String> productName;
    @FXML
    private TableColumn<Product, String> productSum;
    @FXML
    private TableColumn<Product, String> productCount;
    @FXML
    private Label productIdLabel;
    @FXML
    private Label productNameLabel;
    @FXML
    private Label productSumLabel;
    @FXML
    private Label productCountLabel;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){
        try {
            FileReader reader = new FileReader("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\productsData.txt");
            Scanner scanner = new Scanner(reader);
            String[] currentProduct;
            while (scanner.hasNextLine()){
                currentProduct = scanner.nextLine().split(" ");
                productData.add(new Product(Integer.valueOf(currentProduct[0]), currentProduct[1],
                        Double.valueOf(currentProduct[2]), Integer.valueOf(currentProduct[3])));
            }
            productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
            productSum.setCellValueFactory(new PropertyValueFactory<>("productSum"));
            productCount.setCellValueFactory(new PropertyValueFactory<>("productCount"));
            catalogTable.setItems(productData);
            showProductDetails(null);
            catalogTable.getSelectionModel().selectedItemProperty().addListener(
                    (observable, oldValue, newValue) -> showProductDetails(newValue));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void showProductDetails(Product product){
        if(product!=null){
            productIdLabel.setText(product.getProductId().toString());
            productNameLabel.setText(product.getProductName());
            productSumLabel.setText(product.getProductSum().toString());
            productCountLabel.setText(product.getProductCount().toString());
        }
        else {
            productIdLabel.setText("");
            productNameLabel.setText("");
            productSumLabel.setText("");
            productCountLabel.setText("");
        }
    }
    @FXML
    private void deleteProduct() throws IOException {
        int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
        if(selectedIndex >= 0){
            catalogTable.getItems().remove(selectedIndex);
            Product.updateProductsData(catalogTable.getItems());
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Не выделено");
            alert.setHeaderText("Не выбран товар");
            alert.setContentText("Выберите товар из таблицы");
            alert.showAndWait();
        }
    }

    public boolean showProductEditDialog(Product product) throws IOException {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(EditController.class.getResource("edit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Product");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(null);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            EditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setProduct(product);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        }
        catch (IOException e){
            e.printStackTrace();
            return false;
        }
    }
    @FXML
    private void handleNewProduct() throws IOException {
        Product tmpProduct = new Product();
        boolean okClicked = this.showProductEditDialog(tmpProduct);
        if (okClicked)
            productData.add(tmpProduct);
    }

    @FXML
    private void handleEditProduct() throws IOException {
        Product selectedProduct = catalogTable.getSelectionModel().getSelectedItem();

        if(selectedProduct != null){
            boolean okClicked = showProductEditDialog(selectedProduct);
            if (okClicked){
                showProductDetails(selectedProduct);
                int selectedIndex = catalogTable.getSelectionModel().getSelectedIndex();
                productData.set(selectedIndex, selectedProduct);
                Product.updateProductsData(catalogTable.getItems());
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(null);
            alert.setTitle("Ничего не выбрано");
            alert.setHeaderText("Нет выбранного продукта");
            alert.setContentText("Выберите продукт из таблицы");
            alert.showAndWait();
        }
    }
}
