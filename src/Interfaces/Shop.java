package Interfaces;

import Internal.Product;
import Internal.ShoppingCartMain;
import Managment.Client;
import javax.swing.*;
import java.awt.*;

public class Shop extends JFrame {
    private JPanel contentPane;
    private JButton buttonCancel;
    private JButton buttonAddToCart;
    private JList<Product> productList;
    public static DefaultListModel<Product> listModel = new DefaultListModel<>();
    private Client client;
    private ShoppingCart shoppingCart;
    private ShoppingCartMain shoppingCartMain;

    public Shop(Client client, ShoppingCart shoppingCart) {
        this.client = client;
        this.shoppingCart = shoppingCart;

        setTitle("Shop");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        productList = new JList<>(listModel);

        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout());

        buttonCancel = new JButton("Cancel");
        buttonAddToCart = new JButton("Add to Cart");

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.add(buttonAddToCart);
        buttonPanel.add(buttonCancel);

        JScrollPane scrollPane = new JScrollPane(productList);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonPanel, BorderLayout.SOUTH);

        setContentPane(contentPane);

        buttonCancel.addActionListener(e -> {
            dispose();
            System.out.println("Shop closed");
        });

        buttonAddToCart.addActionListener(e -> {
            Product selectedProduct = productList.getSelectedValue();
            if (selectedProduct != null) {
                client.addProductToCart(selectedProduct, 1);
                shoppingCart.updateCartList();
                System.out.println(selectedProduct.getName() + " added to cart.");
            } else {
                JOptionPane.showMessageDialog(this, "Please select a product to add to the cart.");
            }
        });

        setVisible(true);
    }

    public static void addProductsToList() {
        listModel.addElement(new Product(50, "Parmesan", "Aged cheese, classic Italian", "Cheeses"));
        listModel.addElement(new Product(1000, "Laptop", "Laptop with i7 processor, 16GB RAM, 512GB SSD", "Electronics"));
        listModel.addElement(new Product(200, "Sneakers", "Nike Air Max 90", "Shoes"));
        listModel.addElement(new Product(500, "Smartphone", "Samsung Galaxy S20", "Electronics"));
        listModel.addElement(new Product(150, "T-shirt", "Black T-shirt with white stripes", "Clothing"));
        listModel.addElement(new Product(300, "Headphones", "Sony WH-1000XM4", "Electronics"));
        listModel.addElement(new Product(400, "Backpack", "Black backpack with 3 compartments", "Accessories"));
    }

    public static void addProduct(Product product) {
        listModel.addElement(product);
        System.out.println("Added to shop: " + product);
    }

    public static void removeProduct(String productName) {
        for (int i = 0; i < listModel.size(); i++) {
            Product product = listModel.getElementAt(i);
            if (product.getName().equals(productName)) {
                listModel.remove(i);
                System.out.println("Removed from shop: " + productName);
                break;
            }
        }
    }
}