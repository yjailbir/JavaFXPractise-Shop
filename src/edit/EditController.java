package edit;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Product;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {

    @FXML
    private TextField productIdField;
    @FXML
    private TextField productNameField;
    @FXML
    private TextField productCountField;
    @FXML
    private TextField productSumField;

    private Stage dialogStage;
    private Product product;
    private boolean okClicked = false;

    public Integer getNewProductId(){return Integer.parseInt(productIdField.getText());}
    public String getNewProductName(){return productIdField.getText();}
    public Integer getNewProductCount(){return Integer.parseInt(productCountField.getText());}
    public Double getNewProductSum(){return Double.parseDouble(productSumField.getText());}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }

    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }

    public void setProduct(Product product){
        this.product = product;
        if(product.getProductId() != null)
            productIdField.setText(product.getProductId().toString());
        else
            productIdField.setText("");
        if (product.getProductName() != null)
            productNameField.setText(product.getProductName());
        else
            productNameField.setText("");
        if (product.getProductCount() != null)
            productCountField.setText(product.getProductCount().toString());
        else
            productCountField.setText("");
        if(product.getProductSum() != null)
            productSumField.setText(product.getProductSum().toString());
        else
            productSumField.setText("");
    }

    public boolean isOkClicked(){return okClicked;}

    public boolean isInputValid(){
        String errorMessage = "";
        if(productIdField.getText() == null || productIdField.getText().length() == 0)
            errorMessage += "Нет доступного артикула\n";
        if (productNameField.getText() == null || productNameField.getText().length() == 0)
            errorMessage += "Нет доступного наименования товара\n";
        if (productSumField.getText() == null || productSumField.getText().length() == 0)
            errorMessage += "Нет доступной суммы\n";
        if (productCountField.getText() == null || productCountField.getText().length() == 0)
            errorMessage += "Нет доступного количества\n";
        if (errorMessage.length() == 0)
            return true;
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Некорректные поля");
            alert.setHeaderText("Внесите корректную информацию");
            alert.setContentText("");
            alert.showAndWait();
            return false;
        }
    }

    @FXML
    private void handleOk() throws IOException {
        if (isInputValid()){
            product.setProductId(Integer.parseInt(productIdField.getText()));
            product.setProductName(productNameField.getText());
            product.setProductSum(Double.parseDouble(productSumField.getText()));
            product.setProductCount(Integer.parseInt(productCountField.getText()));
            Product.addProduct(product);
            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel(){dialogStage.close();}
}
