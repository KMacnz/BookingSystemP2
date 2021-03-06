package bookingsystem.gui;

import bookingsystem.layout.Column;
import bookingsystem.layout.Row;
import bookingsystem.layout.SetReservation;
import java.awt.*;
import javax.swing.*;

public class BoatBookingPanel extends JPanel {

    private final DrawPanel drawPanel;
    public JButton[][] eachSeat;
    private final GridBagConstraints grid = new GridBagConstraints();
    public JTextField seatTxtFld;
    public JButton cartBtn;

    public char bCol;
    public int bRow;

    public BoatBookingPanel() {

        JPanel boatBookingPanel = new JPanel(new GridBagLayout());
        boatBookingPanel.setOpaque(false);
        setOpaque(false);
        drawPanel = new DrawPanel();

        // repaint the panel
        drawPanel.repaint();

        //initialize elements
        eachSeat = new JButton[7][7];
        for (int i = 0; i < eachSeat.length; i++) {
            for (int j = 0; j < eachSeat[i].length; j++) {
                eachSeat[i][j] = new JButton();
                eachSeat[i][j].setBackground(Color.GREEN);
                eachSeat[i][j].setOpaque(true);
                drawPanel.add(eachSeat[i][j]);

                char col = (char) (65 + i);
                int row = j + 1;
                String a = "Seat: (" + col + ", " + row + ")";

                // set reserved seats to unclickable
                if (SetReservation.reserveBoat.isReserved(new Row(row), new Column(col))) {
                    eachSeat[i][j].setEnabled(false);
                    eachSeat[i][j].setOpaque(false);
                }

                eachSeat[i][j].addActionListener(e -> {
                    seatTxtFld.setText(a);
                    cartBtn.setEnabled(true);
                    bCol = col;
                    bRow = row;
                });
            }
        }

        JPanel seatPanel = new JPanel();
        seatPanel.setOpaque(false);
        seatPanel.setLayout(new FlowLayout());

        JPanel bookerPanel = new JPanel();
        bookerPanel.setOpaque(false);
        bookerPanel.setLayout(new BoxLayout(bookerPanel, BoxLayout.Y_AXIS));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new FlowLayout());

        this.add(boatBookingPanel);

        //Boat Label 
        JLabel boatLabel = new JLabel("Choose your Boat Seats");
        grid.gridx = 1;
        grid.gridy = 1;
        grid.ipady = 50;
        boatBookingPanel.add(boatLabel, grid);

        boatLabel.setFont(new java.awt.Font("Kannada MN", 1, 24));
        boatLabel.setForeground(new java.awt.Color(0, 0, 0));
        boatLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        //Seat Text Area 
        seatTxtFld = new JTextField();
        seatTxtFld.setEditable(false);
        seatTxtFld.setFont(new java.awt.Font("Monospaced", 0, 15));
        seatTxtFld.setText("Seat:");
        seatTxtFld.setPreferredSize(new Dimension(150, 100));
        seatTxtFld.setHorizontalAlignment(JTextField.CENTER);

        // Cart Button
        ImageIcon cartimg = new ImageIcon("./resources/image/cart.png");
        cartBtn = new JButton("Add To Cart", cartimg);
        cartBtn.setBorderPainted(false);
        cartBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        cartBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        cartBtn.setEnabled(false);
        // add listener
        cartBtn.addActionListener(e -> {
            SetReservation setReservation = new SetReservation();
            setReservation.reserveBoat(bCol, bRow);
            cartBtn.setEnabled(false);
            SetReservation.reserveBoat.reserveSeat(new Row(bRow), new Column(bCol));
            eachSeat[bCol - 65][bRow - 1].setBackground(Color.RED);
            eachSeat[bCol - 65][bRow - 1].setOpaque(true);
            eachSeat[bCol - 65][bRow - 1].setEnabled(false);
        });

        // Back Button
        ImageIcon backimg = new ImageIcon("./resources/image/back.png");
        JButton backBtn = new JButton("Back", backimg);
        backBtn.setBorderPainted(false);
        backBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        backBtn.setHorizontalTextPosition(SwingConstants.CENTER);
        // add listener
        backBtn.addActionListener(e -> {
            StartPanel.boatBookingPanel.setVisible(false);
            StartPanel.startPanel.setVisible(true);
        });

        // Cart Button
        ImageIcon cartImg = new ImageIcon("./resources/image/cart.png");
        JButton vcartBtn = new JButton("View Cart", cartImg);
        vcartBtn.setBorderPainted(false);
        vcartBtn.setVerticalTextPosition(SwingConstants.BOTTOM);
        vcartBtn.setHorizontalTextPosition(SwingConstants.CENTER);

        // add listener
        vcartBtn.addActionListener(e -> {
            StartPanel.boatBookingPanel.setVisible(false);
            StartPanel.cartPanel.updateTxt();
            StartPanel.cartPanel.setVisible(true);
        });

        seatPanel.add(drawPanel);
        seatPanel.add(Box.createHorizontalStrut(5));
        bookerPanel.add(seatTxtFld);
        bookerPanel.add(bottomPanel);
        bottomPanel.add(cartBtn);
        bottomPanel.add(backBtn);
        bottomPanel.add(vcartBtn);
        seatPanel.add(bookerPanel);

        grid.gridx = 1;
        grid.gridy = 2;
        boatBookingPanel.add(seatPanel, grid);
    }

    public class DrawPanel extends JPanel {

        public DrawPanel() {
            super.setPreferredSize(new Dimension(263, 263));
            super.setOpaque(false);
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // set size of seats
            for (int i = 0; i < eachSeat.length; i++) {
                for (int j = 0; j < eachSeat[i].length; j++) {
                    eachSeat[i][j].setBounds(i * 35, j * 35, 38, 38);
                }
            }
        }
    }
}
