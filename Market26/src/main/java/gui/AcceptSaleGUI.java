package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle; // Importante
import businessLogic.BLFacade;
import domain.Buyer;
import domain.Sale;
import domain.Seller;

public class AcceptSaleGUI extends JFrame {
    private JTextField txtSaleTitle;
    private JComboBox<String> comboResults;
    private Buyer currentBuyer;
    private List<Sale> currentSalesList;

    public AcceptSaleGUI(Buyer buyer) {
        this.currentBuyer = buyer;
        
        // Título dinámico con el nombre del comprador
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Title") + " - " + buyer.getName());
        setBounds(100, 100, 480, 250); 
        getContentPane().setLayout(null);

        JLabel lblInfo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.SearchLabel"));
        lblInfo.setBounds(30, 20, 200, 25);
        getContentPane().add(lblInfo);

        txtSaleTitle = new JTextField();
        txtSaleTitle.setBounds(30, 50, 150, 25);
        getContentPane().add(txtSaleTitle);

        JButton btnSearch = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnSearch"));
        btnSearch.setBounds(190, 50, 100, 25);
        getContentPane().add(btnSearch);

        comboResults = new JComboBox<String>();
        comboResults.setBounds(30, 90, 400, 25); 
        getContentPane().add(comboResults);

        JButton btnBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnBuy"));
        btnBuy.setBounds(30, 127, 200, 30);
        btnBuy.setEnabled(false); 
        getContentPane().add(btnBuy);

        JButton btnCounterOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnCounterOffer"));
        btnCounterOffer.setBounds(240, 127, 190, 30);
        btnCounterOffer.setEnabled(false); 
        getContentPane().add(btnCounterOffer);
        
        // Botón para hacer la review
        JButton btnRate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Rate")); 
        btnRate.setBounds(158, 169, 150, 29);
        btnRate.setEnabled(false);
        getContentPane().add(btnRate);
        btnRate.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		int selectedIdx = comboResults.getSelectedIndex();
        		Sale selectedSale = currentSalesList.get(selectedIdx);
        		Seller targetSeller = selectedSale.getSeller();
        		
        		RateGUI rateWindow = new RateGUI(currentBuyer, targetSeller);
        		rateWindow.setVisible(true);
        	}
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BLFacade facade = MainGUI.getBusinessLogic();
                String title = txtSaleTitle.getText();
                
                currentSalesList = facade.getActiveSalesByTitle(title);
                comboResults.removeAllItems(); 
                
                if (currentSalesList.isEmpty()) {
                    comboResults.addItem(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.NoResults"));
                    btnBuy.setEnabled(false);
                    btnCounterOffer.setEnabled(false);
                    btnRate.setEnabled(false);
                } else {
                    for (Sale s : currentSalesList) {
//                       comboResults.addItem("ID: " + s.getSaleNumber() + " | " + s.getTitle() + " | " + 
//                           ResourceBundle.getBundle("Etiquetas").getString("Price") + ": " + s.getPrice() + "€");
                    	
                    	// Añadido el nombre del vendedor a la lista de las ventas
                    	 comboResults.addItem("ID: " + s.getSaleNumber() + " | " + s.getTitle() + " | " + 
                             ResourceBundle.getBundle("Etiquetas").getString("Price") + ": " + s.getPrice() + "€" + " | " + s.getSeller().getName());
                    }
                    btnBuy.setEnabled(true); 
                    btnCounterOffer.setEnabled(true);
                    btnRate.setEnabled(true);
                }
            }
        });

        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboResults.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Sale selectedSale = currentSalesList.get(selectedIndex);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    boolean success = facade.acceptSale(currentBuyer.getEmail(), selectedSale.getSaleNumber());
                    
                    if (success) {
                        JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BuySuccess") + " " + selectedSale.getPrice() + "€!");
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BuyError"));
                    }
                }
            }
        });

        btnCounterOffer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = comboResults.getSelectedIndex();
                if (selectedIndex >= 0) {
                    Sale selectedSale = currentSalesList.get(selectedIndex);
                    
                    String input = JOptionPane.showInputDialog(null, 
                        ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.OriginalPrice") + ": " + selectedSale.getPrice() + "€\n" +
                        ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.HowMuch"), 
                        ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Haggle"), 
                        JOptionPane.QUESTION_MESSAGE);
                        
                    if (input != null && !input.trim().isEmpty()) {
                        try {
                            float offeredPrice = Float.parseFloat(input.replace(",", ".")); 
                            
                            if(offeredPrice >= selectedSale.getPrice()) {
                                JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.PriceWarning"), 
                                    ResourceBundle.getBundle("Etiquetas").getString("Warning"), JOptionPane.WARNING_MESSAGE);
                                return;
                            }

                            BLFacade facade = MainGUI.getBusinessLogic();
                            boolean success = facade.makeCounterOffer(currentBuyer.getEmail(), selectedSale.getSaleNumber(), offeredPrice);
                            
                            if (success) {
                                JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.OfferSent") + " " + offeredPrice + "€.");
                                dispose();
                            } else {
                                JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.OfferError"), 
                                    ResourceBundle.getBundle("Etiquetas").getString("Error"), JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.InvalidFormat"), 
                                ResourceBundle.getBundle("Etiquetas").getString("ErrorFormat"), JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
    }
}