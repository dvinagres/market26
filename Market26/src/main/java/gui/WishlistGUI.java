package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle;
import businessLogic.BLFacade;
import domain.Buyer;
import domain.Sale;

public class WishlistGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private Buyer buyer;

    public WishlistGUI(Buyer buyer) {
        this.buyer = buyer;
        setTitle("title");
        setBounds(100, 100, 450, 320);
        getContentPane().setLayout(null);

        String[] columnNames = {
            "a",
            "b",
            "c"
        };
        
        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(25, 20, 400, 180);
        getContentPane().add(scrollPane);

        JButton btnClose = new JButton("cerrar");
        btnClose.setBounds(160, 220, 120, 30);
        getContentPane().add(btnClose);
        btnClose.addActionListener(e -> dispose());

        loadWishlist();
    }

    private void loadWishlist() {
        BLFacade facade = MainGUI.getBusinessLogic();
        List<Sale> list = facade.getWishlist(buyer.getEmail());
        tableModel.setRowCount(0);
        for (Sale s : list) {
            Object[] row = {s.getSaleNumber(), s.getTitle(), s.getPrice() + "€"};
            tableModel.addRow(row);
        }
    }
}
