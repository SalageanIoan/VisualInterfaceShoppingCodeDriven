package Interfaces;

import Internal.Product;
import Managment.Client;
import Managment.ClientPremium;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Checkout extends JFrame {
    private JPanel contentPane;
    private JButton buttonCheckout;
    private Client client;
    private ShoppingCart shoppingCart;
    private JTextArea summaryTextArea;

    public Checkout(Client client,ShoppingCart shoppingCart) {
        this.client=client;
        this.shoppingCart = shoppingCart;

        setTitle("Checkout");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        buttonCheckout = new JButton("Confirm Checkout");
        summaryTextArea = new JTextArea();
        summaryTextArea.setEditable(false);

        displayCheckoutSummary();

        contentPane.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);
        contentPane.add(buttonCheckout, BorderLayout.SOUTH);
        setContentPane(contentPane);

        buttonCheckout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                confirmCheckout();
            }
        });

        setVisible(true);
    }

    private void displayCheckoutSummary() {
        StringBuilder summary = new StringBuilder();
        ArrayList<Product> cartItems = client.getCartProducts();
        double totalPrice = 0;

        summary.append("Checkout Summary:\n\n");
        for (Product product : cartItems) {
            summary.append(product.toString()).append("\n");
            totalPrice += product.getPrice();
        }

        if (totalPrice > 1000) {
            client = new ClientPremium(client.getName(), client.getEmail(), true);
            summary.append("\nCongratulations! You are now a premium client.You will receive a 10% discount!");
        }
        summary.append("\nOriginal Price: $").append(totalPrice);
        totalPrice = totalPrice-(totalPrice*0.1);
        summary.append("\nDiscounted Price: $").append( totalPrice);

        summaryTextArea.setText(summary.toString());
    }

    private void confirmCheckout() {
        JOptionPane.showMessageDialog(this, "Checkout confirmed for " + client.getName(), "Confirmation", JOptionPane.INFORMATION_MESSAGE);
        shoppingCart.dispose();
        dispose();
        new LoginSelection();
    }

    public static void main(String[] args) {
        Client client=new Client();
        new Checkout(client,new ShoppingCart(client));
    }
}