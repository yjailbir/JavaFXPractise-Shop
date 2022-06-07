package models;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Purchases {
    int id;
    String login;
    ArrayList<Product> products;

    public Purchases(){}

    public Purchases(int id, String login, ArrayList<Product> products) {
        this.id = id;
        this.login = login;
        this.products = products;
    }


    public static ArrayList<Purchases> getAllPurchases() throws FileNotFoundException {
        ArrayList<Purchases> purchases = new ArrayList<>();
        FileReader reader = new FileReader("C:\\Users\\nkuda\\IdeaProjects\\shop\\src\\purchases.txt");
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()){
            Purchases tmpPurchase = new Purchases();
            tmpPurchase.setId(Integer.parseInt(scanner.nextLine()));
            tmpPurchase.setLogin(scanner.nextLine());
            ArrayList<Product> tmpProducts = new ArrayList<>();
            String[] productsString = scanner.nextLine().split(",");
            for (String str: productsString) {
                Product tmpProduct = Product.getProductById(str);
                tmpProducts.add(tmpProduct);
            }
            tmpPurchase.setProducts(tmpProducts);
            purchases.add(tmpPurchase);
        }
        return purchases;
    }

    public static Purchases getCurrentPurchase(int currentId) throws FileNotFoundException {
        ArrayList<Purchases> list = getAllPurchases();
        for (Purchases p: list) {
            if(p.getId() == currentId)
                return p;
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
