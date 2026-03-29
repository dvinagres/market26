package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ResourceBundle; // Importante
import businessLogic.BLFacade;
import domain.Seller;
import domain.Sale;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAcceptedSalesGUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;

    public ViewAcceptedSalesGUI(Seller seller) {
        // Título dinámico
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("ViewSales.Title") + ": " + seller.getName());
        setBounds(100, 100, 520, 380); // Un poco más grande para que quepan los textos
        getContentPane().setLayout(null);

        JLabel lblTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ViewSales.History") + " " + seller.getName() + ":");
        lblTitle.setBounds(30, 20, 400, 25);
        getContentPane().add(lblTitle);

        // Nombres de columnas desde ResourceBundle
        String[] columnNames = {
            ResourceBundle.getBundle("Etiquetas").getString("ViewSales.ColOffer"),
            ResourceBundle.getBundle("Etiquetas").getString("ViewSales.ColTitle"),
            ResourceBundle.getBundle("Etiquetas").getString("ViewSales.ColBuyer"),
            ResourceBundle.getBundle("Etiquetas").getString("ViewSales.ColPrice")
        };
        
        tableModel = new DefaultTableModel(null, columnNames);
        table = new JTable(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(30, 60, 440, 150);
        getContentPane().add(scrollPane);

        // Botón: Crear Oferta
        JButton btnCreateSale = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ViewSales.BtnCreate"));
        btnCreateSale.setBounds(50, 220, 180, 30);
        getContentPane().add(btnCreateSale);

        btnCreateSale.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CreateSaleGUI createSaleWindow = new CreateSaleGUI(seller.getEmail());
                createSaleWindow.setVisible(true);
            }
        });

        // Botón: Ver Contraofertas
        JButton btnCounterOffers = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ViewSales.BtnCounter"));
        btnCounterOffers.setBounds(50, 260, 180, 30);
        getContentPane().add(btnCounterOffers);

        btnCounterOffers.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageCounterOffersGUI offersWindow = new ManageCounterOffersGUI(seller.getEmail());
                offersWindow.setVisible(true);
            }
        });
        
        // Botón: Cerrar
        JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
        btnClose.setBounds(260, 220, 120, 30);
        getContentPane().add(btnClose);

        btnClose.addActionListener(e -> dispose());

        loadAcceptedSales(seller.getEmail());
    }

    private void loadAcceptedSales(String email) {
        BLFacade facade = MainGUI.getBusinessLogic();
        List<Sale> soldItems = facade.getAcceptedSales(email);

        if (!soldItems.isEmpty()) {
            for (Sale s : soldItems) {
                Object[] row = new Object[4];
                row[0] = s.getSaleNumber();
                row[1] = s.getTitle();
                row[2] = s.getBuyer().getName();
                row[3] = s.getPrice() + "€";
                tableModel.addRow(row);
            }
        }
    }
}