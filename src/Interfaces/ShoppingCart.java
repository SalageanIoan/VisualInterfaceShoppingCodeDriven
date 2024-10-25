package Interfaces;

import Internal.Product;
import Internal.ShoppingCartMain;
import Managment.Client;

import javax.swing.*;
import java.awt.*;

public class ShoppingCart extends JFrame {
    private JPanel contentPane;
    private JButton buttonCheckout;
    private JList<Product> productList;
    private DefaultListModel<Product> listModel;
    private Client client;

    public ShoppingCart(Client client) {
        this.client = client;

        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        buttonCheckout = new JButton("Checkout");

        listModel = new DefaultListModel<>();
        productList = new JList<>(listModel);
        updateCartList();

        JScrollPane scrollPane = new JScrollPane(productList);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(buttonCheckout, BorderLayout.SOUTH);

        setContentPane(contentPane);

        buttonCheckout.addActionListener(e -> onCheckout());

        setVisible(true);
    }

    public void updateCartList() {
        listModel.clear();
        for (Product product : client.getShoppingCart().getProducts()) {
            listModel.addElement(product);
        }
    }

    private void onCheckout() {
        System.out.println("Checkout initiated for " + client.getName());
        new Checkout(client, this);
        dispose();
    }

    public static void main(String[] args) {
        Client client = new Client("Ana", "ana@gmail.com");
        new ShoppingCart(client);
    }
}