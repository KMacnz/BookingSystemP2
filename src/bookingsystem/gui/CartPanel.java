package bookingsystem.gui;

import bookingsystem.Cart;
import java.awt.*;
import javax.swing.*;

public class CartPanel extends JPanel {

    public JTextField reciptTxtFld;
    private JTextArea cartTxtFld;

    GridBagConstraints grid = new GridBagConstraints();

    public CartPanel() {
        JPanel cartPanel = new JPanel();
        cartPanel.setLayout(new GridBagLayout());
        cartPanel.setOpaque(false);
        setOpaque(false);
        cartPanel.setPreferredSize(new Dimension(525, 480));
        this.add(cartPanel);
        
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new FlowLayout());
        btnPanel.setOpaque(false);

        //Cart Label 
        JLabel cartLabel = new JLabel("Your Cart");
        grid.gridx = 1;
        grid.gridy = 1;
        grid.ipady = 25;
        cartPanel.add(cartLabel, grid);

        cartLabel.setFont(new java.awt.Font("Kannada MN", 1, 24));
        cartLabel.setForeground(new java.awt.Color(0, 0, 0));
        cartLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        cartTxtFld = new JTextArea("Your cart is currently empty");
        cartTxtFld.setEditable(false);
        cartTxtFld.setFont(new java.awt.Font("Monospaced", 0, 15));
        cartTxtFld.setPreferredSize(new Dimension(300, 200));
        cartTxtFld.setLineWrap(true);

        grid.gridx = 1;
        grid.gridy = 2;
        cartPanel.add(cartTxtFld, grid);

        // Back Button
        ImageIcon backimg = new ImageIcon("./resources/image/back.png");
        JButton backBtn = new JButton("Back", backimg);
        backBtn.setBorderPainted(false);
        backBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        backBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        // add listener
        backBtn.addActionListener(e -> {
            StartPanel.cartPanel.setVisible(false);
            StartPanel.startPanel.setVisible(true);
        });

        // Restart Button
        ImageIcon resetimg = new ImageIcon("./resources/image/restartsmall.png");
        JButton resetBtn = new JButton("Restart Booking", resetimg);
        resetBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        resetBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        resetBtn.setBorderPainted(false);
        // add listener
        resetBtn.addActionListener(e -> {
            BookSysGui.setUpSystem();
            Cart.busCart.clear();
            Cart.boatCart.clear();
            Cart.tramCart.clear();
        });

        grid.gridx = 1;
        grid.gridy = 3;
        cartPanel.add(btnPanel, grid);
        
        btnPanel.add(resetBtn);
        btnPanel.add(backBtn);
        
        updateTxt();
    }

    // if the carts contains items, update the txtfld to show these
    public void updateTxt() {
        if (!(Cart.busCart.isEmpty() && Cart.boatCart.isEmpty() && Cart.tramCart.isEmpty())) {
            cartTxtFld.setText("");
            if (!(Cart.busCart.isEmpty())) {
                cartTxtFld.append("Bus: " + Cart.busCart.toString() + "\n\n");
            }
            if (!(Cart.boatCart.isEmpty())) {
                cartTxtFld.append("Boat: " + Cart.boatCart.toString() + "\n\n");
            }
            if (!(Cart.tramCart.isEmpty())) {
                cartTxtFld.append("Tram: " + Cart.tramCart.toString());
            }
        }
    }
}
