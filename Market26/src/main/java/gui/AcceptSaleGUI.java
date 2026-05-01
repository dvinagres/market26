package gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle;
import businessLogic.BLFacade;
import domain.Buyer;
import domain.Sale;
import domain.Seller;

public class AcceptSaleGUI extends JFrame {
    private JTextField txtSaleTitle;
    private JTable tableResults;
    private DefaultTableModel tableModel;
    private Buyer currentBuyer;
    private List<Sale> currentSalesList;

    public AcceptSaleGUI(Buyer buyer) {
        this.currentBuyer = buyer;
        
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Title") + " - " + buyer.getName());
        setBounds(100, 100, 500, 350); 
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

        String[] columnNames = {"ID", "Título", "Precio", "Vendedor"};
        tableModel = new DefaultTableModel(null, columnNames);
        tableResults = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableResults);
        scrollPane.setBounds(30, 90, 420, 100); 
        getContentPane().add(scrollPane);

        JButton btnBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnBuy"));
        btnBuy.setBounds(30, 200, 200, 30);
        btnBuy.setEnabled(false); 
        getContentPane().add(btnBuy);

        JButton btnCounterOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnCounterOffer"));
        btnCounterOffer.setBounds(240, 200, 210, 30);
        btnCounterOffer.setEnabled(false); 
        getContentPane().add(btnCounterOffer);
        
        JButton btnRate = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Rate")); 
        btnRate.setBounds(30, 240, 200, 30);
        btnRate.setEnabled(false);
        getContentPane().add(btnRate);
        
        JButton btnReport = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BtnReport")); 
        btnReport.setBounds(240, 240, 210, 30);
        btnReport.setEnabled(false);
        getContentPane().add(btnReport);

        btnRate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIdx = tableResults.getSelectedRow();
                if (selectedIdx >= 0) {
                    Sale selectedSale = currentSalesList.get(selectedIdx);
                    Seller targetSeller = selectedSale.getSeller();
                    RateGUI rateWindow = new RateGUI(currentBuyer, targetSeller);
                    rateWindow.setVisible(true);
                }
            }
        });
        
        btnReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIdx = tableResults.getSelectedRow();
                if (selectedIdx >= 0) {
                    Sale selectedSale = currentSalesList.get(selectedIdx);
                    Seller targetSeller = selectedSale.getSeller();
                    ReportGUI reportWindow = new ReportGUI(currentBuyer, targetSeller);
                    reportWindow.setVisible(true);
                }
            }
        });

        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BLFacade facade = MainGUI.getBusinessLogic();
                String title = txtSaleTitle.getText();
                
                currentSalesList = facade.getActiveSalesByTitle(title);
                tableModel.setRowCount(0); 
                
                if (currentSalesList.isEmpty()) {
                    btnBuy.setEnabled(false);
                    btnCounterOffer.setEnabled(false);
                    btnRate.setEnabled(false);
                    btnReport.setEnabled(false);
                } else {
                    for (Sale s : currentSalesList) {
                        Object[] row = {s.getSaleNumber(), s.getTitle(), s.getPrice() + "€", s.getSeller().getName()};
                        tableModel.addRow(row);
                    }
                    btnBuy.setEnabled(true); 
                    btnCounterOffer.setEnabled(true);
                    btnRate.setEnabled(true);
                    btnReport.setEnabled(true); 
                }
            }
        });

        btnBuy.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tableResults.getSelectedRow();
                if (selectedIndex >= 0) {
                    Sale selectedSale = currentSalesList.get(selectedIndex);
                    BLFacade facade = MainGUI.getBusinessLogic();
                    boolean success = facade.acceptSale(currentBuyer.getEmail(), selectedSale.getSaleNumber());
                    
                    if (success) {
                        String card = ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Card");
                        String cash = ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Cash");
                        String[] opciones = {card, cash};
                        
                        int seleccion = JOptionPane.showOptionDialog(null,
                                ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.PayQuestion"),
                                ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.PayMethod"),
                                JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, opciones, opciones);

                        String paymentMethod = (seleccion == 0) ? "Tarjeta" : "Efectivo"; 
                        String paidWithText = ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.PaidWith");
                        String methodDisp = (seleccion == 0) ? card : cash; 

                        facade.paySale(selectedSale.getSaleNumber(), paymentMethod);
                        
                        JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BuySuccess") + " " + selectedSale.getPrice() + "€ " + paidWithText + " " + methodDisp + "!");
                        dispose(); 
                    } else {
                        JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.BuyError"));
                    }
                }
            }
        });

        btnCounterOffer.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = tableResults.getSelectedRow();
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