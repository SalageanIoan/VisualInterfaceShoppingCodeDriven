package Interfaces;

import Internal.Product;
import Managment.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class AdminMenu extends JFrame {
    private JPanel contentPane;
    private JLabel adminLabel;
    private JButton addProductButton;
    private JButton removeProductButton;
    private JButton logoutButton;
    private JButton viewClientsButton;
    private JButton viewShoppingCartsButton;

    public AdminMenu() {
        setTitle("Admin Menu");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(420, 300);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        adminLabel = new JLabel("Admin");
        addProductButton = new JButton("Add Product");
        removeProductButton = new JButton("Remove Product");
        logoutButton = new JButton("Logout");
        viewClientsButton = new JButton("View Clients");
        viewShoppingCartsButton = new JButton("View Shopping Carts");

        contentPane.setLayout(new GridLayout(6, 1));
        contentPane.add(adminLabel);
        contentPane.add(addProductButton);
        contentPane.add(removeProductButton);
        contentPane.add(viewClientsButton);
        contentPane.add(viewShoppingCartsButton);
        contentPane.add(logoutButton);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 35));

        adminLabel.setHorizontalAlignment(SwingConstants.CENTER);

        setContentPane(contentPane);

        addProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onAddProduct();
            }
        });

        removeProductButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onRemoveProduct();
            }
        });

        viewClientsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onClients();
            }
        });

        viewShoppingCartsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onViewShoppingCarts();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onLogout();
            }
        });

        setVisible(true);
    }

    private void onClients() {
        System.out.println("Viewing clients");

        List<Client> clients = ClientLogin.getLoggedInClients();

        String[] clientDetails = new String[clients.size()];
        for (int i = 0; i < clients.size(); i++) {
            Client client = clients.get(i);
            clientDetails[i] = client.getName() + " - " + client.getEmail();
        }

        JList<String> clientList = new JList<>(clientDetails);
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        clientList.setVisibleRowCount(-1);
        JScrollPane listScroller = new JScrollPane(clientList);
        listScroller.setPreferredSize(new Dimension(250, 80));

        JOptionPane.showMessageDialog(null, listScroller, "Client List", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onAddProduct() {
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField descriptionField = new JTextField();
        JTextField categoryField = new JTextField();

        Object[] message = {"Product Name:",nameField, "Price:", priceField,"Description:",descriptionField,"Category:",categoryField};

        int option = JOptionPane.showConfirmDialog(null, message, "Add Product", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String name = nameField.getText();
            int price;
            try {
                price = Integer.parseInt(priceField.getText());
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price. Please enter a valid number.");
                return;
            }
            String description = descriptionField.getText();
            String category = categoryField.getText();

            Product newProduct = new Product(price, name, description, category);
            Shop.addProduct(newProduct);
            System.out.println("Added product: " + newProduct);
        }
    }

    private void onRemoveProduct() {
        if (Shop.listModel.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No products available to remove.");
            return;
        }

        List<String> productNames = new ArrayList<>();
        for (int i = 0; i < Shop.listModel.getSize(); i++) {
            productNames.add(Shop.listModel.getElementAt(i).getName());
        }

        String selectedProduct = (String) JOptionPane.showInputDialog(null, "Select a product to remove:",
                "Remove Product", JOptionPane.QUESTION_MESSAGE, null,
                productNames.toArray(), productNames.get(0));

        if (selectedProduct != null) {
            Shop.removeProduct(selectedProduct);
            System.out.println("Removed product: " + selectedProduct);
        }
    }

    private void onViewShoppingCarts() {
        System.out.println("Viewing shopping carts");

        List<Client> clients = ClientLogin.getLoggedInClients();//doar cientii care s-au logat
        StringBuilder cartSummary = new StringBuilder();

        for (Client client : clients) {
            cartSummary.append("Client: ").append(client.getName()).append(" - Email: ").append(client.getEmail()).append("\n");
            cartSummary.append("Shopping Cart:\n");
            List<Product> products = client.getShoppingCart().getProducts();
            if (products.isEmpty()) {
                cartSummary.append("No products in cart.\n");
            } else {
                for (Product product : products) {
                    cartSummary.append(product.toString()).append("\n");
                }
            }
            cartSummary.append("\n");
        }

        JTextArea cartTextArea = new JTextArea(cartSummary.toString());
        cartTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(cartTextArea);
        scrollPane.setPreferredSize(new Dimension(300, 200));

        JOptionPane.showMessageDialog(null, scrollPane, "Shopping Carts", JOptionPane.INFORMATION_MESSAGE);
    }

    private void onLogout() {
        dispose();
        new LoginSelection();
    }

    public static void main(String[] args) {
        new AdminMenu();
    }
}