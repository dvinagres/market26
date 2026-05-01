package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle;
import businessLogic.BLFacade;
import domain.CounterOffer;

public class ManageCounterOffersGUI extends JFrame {
    private JTable tableOffers;
    private DefaultTableModel tableModel;
    private List<CounterOffer> pendingOffers; 
    private String sellerEmail;

    public ManageCounterOffersGUI(String sellerEmail) {
        this.sellerEmail = sellerEmail;
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Title"));
        setBounds(100, 100, 480, 260);
        getContentPane().setLayout(null);

        JLabel lblInfo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Pending"));
        lblInfo.setBounds(30, 20, 350, 25);
        getContentPane().add(lblInfo);

        String[] columnNames = {"Producto", "Comprador", "Oferta"};
        tableModel = new DefaultTableModel(null, columnNames);
        tableOffers = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableOffers);
        scrollPane.setBounds(30, 50, 400, 100);
        getContentPane().add(scrollPane);

        JButton btnAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Accept"));
        btnAccept.setBounds(60, 170, 150, 30);
        getContentPane().add(btnAccept);

        JButton btnReject = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Reject"));
        btnReject.setBounds(250, 170, 150, 30);
        getContentPane().add(btnReject);

        loadOffers();

        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = tableOffers.getSelectedRow();
                if (index >= 0 && !pendingOffers.isEmpty()) {
                    CounterOffer offer = pendingOffers.get(index);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    
                    boolean success = facade.resolveCounterOffer(offer.getId(), true);
                    if(success) {
                        String msg = ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.MsgSuccess") 
                                     + " '" + offer.getSale().getTitle() + "' " 
                                     + ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.MsgSuccessPrice") 
                                     + " " + offer.getOfferedPrice() + "€.";
                        JOptionPane.showMessageDialog(null, msg);
                        loadOffers(); 
                    }
                }
            }
        });

        btnReject.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = tableOffers.getSelectedRow();
                if (index >= 0 && !pendingOffers.isEmpty()) {
                    CounterOffer offer = pendingOffers.get(index);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    
                    boolean success = facade.resolveCounterOffer(offer.getId(), false);
                    if(success) {
                        JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.MsgRejected"));
                        loadOffers(); 
                    }
                }
            }
        });
    }

    private void loadOffers() {
        BLFacade facade = MainGUI.getBusinessLogic();
        pendingOffers = facade.getPendingCounterOffers(sellerEmail);
        tableModel.setRowCount(0);

        if (!pendingOffers.isEmpty()) {
            for (CounterOffer c : pendingOffers) {
                Object[] row = {c.getSale().getTitle(), c.getBuyer().getName(), c.getOfferedPrice() + "€"};
                tableModel.addRow(row);
            }
        }
    }
}