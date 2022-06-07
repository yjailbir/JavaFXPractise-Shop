package models;

import javafx.collections.ObservableList;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Product {
    String productName;
    Integer productId, productCount;
    Double productSum;

    public Product(Integer productId, String productName, Double productSum, Integer productCount) {
        this.productId = productId;
        this.productName = productName;
        this.productSum = productSum;
        this.productCount = productCount;
    }

    public Product() {

    }

    public static void updateProductsData(ObservableList<Product> list) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\productsData.txt");
        for (Product product : list){
            writer.write(product.getProductId() + " "
                    + product.getProductName() + " "
                    + product.getProductSum() + " "
                    + product.getProductCount() + "\n");
        }
       writer.close();
    }

    public static void addProduct(Product product) throws IOException {
        FileWriter writer = new FileWriter("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\productsData.txt", true);
        writer.write(product.getProductId() + " "
                + product.getProductName() + " "
                + product.getProductSum() + " "
                + product.getProductCount() + "\n");
        writer.close();
    }

    public static Product getProductById(String strId) throws FileNotFoundException {
        FileReader reader = new FileReader("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\productsData.txt");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()){
            String[] arr = scanner.nextLine().split(" ");
            Product tmpProduct = new Product(
                    Integer.parseInt(arr[0]),
                    arr[1],
                    Double.parseDouble(arr[2]),
                    Integer.parseInt(arr[3])
            );
            if (tmpProduct.getProductId().equals(Integer.parseInt(strId)))
                return tmpProduct;
        }
        return null;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductCount() {
        return productCount;
    }

    public void setProductCount(Integer productCount) {
        this.productCount = productCount;
    }

    public Double getProductSum() {
        return productSum;
    }

    public void setProductSum(Double productSum) {
        this.productSum = productSum;
    }
}
