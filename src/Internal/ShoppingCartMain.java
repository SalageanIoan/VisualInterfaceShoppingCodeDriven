package Internal;

import Managment.Client;

import java.util.ArrayList;

public class ShoppingCartMain {
    private ArrayList<Product> products;
    private int totalPrice;
    private Client client;

    public ShoppingCartMain(Client client) {
        this.products = new ArrayList<>();
        this.totalPrice = 0;
        this.client = client;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void addProductToCart(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.products.add(product);
        }
        this.totalPrice += product.getPrice() * quantity;
    }

    public void showCart() {
        if (products.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            System.out.println("Products in your cart:");
            for (Product product : products) {
                product.showProduct();
            }
            System.out.println("Total Price: " + totalPrice);
        }
    }
}