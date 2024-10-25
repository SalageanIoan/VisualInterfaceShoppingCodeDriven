package Managment;

import Internal.ShoppingCartMain;
import Internal.Product;

import java.util.ArrayList;

public class Client {
    private String name;
    private String email;
    private ShoppingCartMain shoppingCartMain;

    public Client(String name, String email) {
        this.name = name;
        this.email = email;
        this.shoppingCartMain = new ShoppingCartMain(this);
    }

    public Client() {
        this.shoppingCartMain = new ShoppingCartMain(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public ShoppingCartMain getShoppingCart() {
        return shoppingCartMain;
    }

    public ArrayList<Product> getCartProducts() {
        return shoppingCartMain.getProducts();
    }

    public void addProductToCart(Product product, int quantity) {
        shoppingCartMain.addProductToCart(product, quantity);
    }
}