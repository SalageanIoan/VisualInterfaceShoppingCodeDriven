package Interfaces;

import Managment.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ClientLogin extends JFrame {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField enterYourNameTextField;
    private JTextField enterYourEmailTextField;
    private Client client;

    private static List<Client> loggedInClients = new ArrayList<Client>() {{
        add(new Client("Alice", "alice@gmail.com"));
        add(new Client("Bob", "bob@gmail.com"));
        add(new Client("Charlie", "charlie@gmail.com"));
    }};


    public ClientLogin() {
        setTitle("Client Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 170);
        setLocationRelativeTo(null);

        contentPane = new JPanel();
        buttonOK = new JButton("OK");
        buttonCancel = new JButton("Cancel");
        enterYourNameTextField = new JTextField();
        enterYourEmailTextField = new JTextField();

        contentPane.setLayout(new GridLayout(4, 2));
        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(enterYourNameTextField);
        contentPane.add(new JLabel("Enter your email:"));
        contentPane.add(enterYourEmailTextField);
        contentPane.add(buttonOK);
        contentPane.add(buttonCancel);

        setContentPane(contentPane);

        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                onCancel();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        setVisible(true);
    }

    private void onOK() {
        String name = enterYourNameTextField.getText();
        String email = enterYourEmailTextField.getText();
        if (name.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        client = new Client(name, email);
        loggedInClients.add(client);

        dispose();
        System.out.println("Client Login successful: " + client.getName() + " " + client.getEmail());

        ShoppingCart cart = new ShoppingCart(client);
        cart.setLocation(100, 100);

        Shop shop = new Shop(client, cart);
        shop.setLocation(cart.getX() + cart.getWidth() + 20, 100);

    }

    private void onCancel() {
        dispose();
        System.out.println("Client Login canceled");
    }

    public static List<Client> getLoggedInClients() {
        return loggedInClients;
    }

    public static void main(String[] args) {
        new ClientLogin();
    }
}