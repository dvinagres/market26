package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle; // Importante
import businessLogic.BLFacade;
import domain.CounterOffer;

public class ManageCounterOffersGUI extends JFrame {
    private JComboBox<String> comboOffers;
    private List<CounterOffer> pendingOffers; 
    private String sellerEmail;

    public ManageCounterOffersGUI(String sellerEmail) {
        this.sellerEmail = sellerEmail;
        // Título dinámico
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Title"));
        setBounds(100, 100, 480, 220);
        getContentPane().setLayout(null);

        // Label informativo
        JLabel lblInfo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Pending"));
        lblInfo.setBounds(30, 20, 350, 25);
        getContentPane().add(lblInfo);

        comboOffers = new JComboBox<String>();
        comboOffers.setBounds(30, 50, 400, 25);
        getContentPane().add(comboOffers);

        // Botón ACEPTAR
        JButton btnAccept = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Accept"));
        btnAccept.setBounds(60, 110, 150, 30);
        getContentPane().add(btnAccept);

        // Botón RECHAZAR
        JButton btnReject = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.Reject"));
        btnReject.setBounds(250, 110, 150, 30);
        getContentPane().add(btnReject);

        loadOffers();

        btnAccept.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int index = comboOffers.getSelectedIndex();
                if (index >= 0 && !pendingOffers.isEmpty()) {
                    CounterOffer offer = pendingOffers.get(index);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    
                    boolean success = facade.resolveCounterOffer(offer.getId(), true);
                    if(success) {
                        // Mensaje de éxito dinámico
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
                int index = comboOffers.getSelectedIndex();
                if (index >= 0 && !pendingOffers.isEmpty()) {
                    CounterOffer offer = pendingOffers.get(index);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    
                    boolean success = facade.resolveCounterOffer(offer.getId(), false);
                    if(success) {
                        // Mensaje de rechazo dinámico
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
        comboOffers.removeAllItems();

        if (pendingOffers.isEmpty()) {
            // Texto de lista vacía dinámico
            comboOffers.addItem(ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.NoOffers"));
        } else {
            for (CounterOffer c : pendingOffers) {
                // Traducción de la palabra "ofrece" dentro del combo
                String offersText = ResourceBundle.getBundle("Etiquetas").getString("CounterOffer.OffersYou");
                comboOffers.addItem(c.getSale().getTitle() + " | " + c.getBuyer().getName() + " " + offersText + ": " + c.getOfferedPrice() + "€");
            }
        }
    }
}