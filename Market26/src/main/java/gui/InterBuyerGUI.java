package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.List;
import java.util.ResourceBundle; 
import businessLogic.BLFacade;
import domain.Buyer;
import domain.Sale;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class InterBuyerGUI extends JFrame {

	private JPanel contentPane;
	private Buyer currentBuyer;

	
	public InterBuyerGUI(Buyer buyer) {
		this.currentBuyer = buyer;
		
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 1, 0, 0));
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("InterBuyerGUI.Title") + ": " + buyer.getName());
        setBounds(100, 100, 480, 250); 
        
        JLabel lblSelect = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InterBuyerGUI.Option"));
        lblSelect.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelect.setForeground(new Color(0, 0, 0));
        contentPane.add(lblSelect);
        
        // Botón para comprar
        JButton btnBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InterBuyerGUI.Buy"));
        contentPane.add(btnBuy);
        btnBuy.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		AcceptSaleGUI acceptWindow = new AcceptSaleGUI(buyer);
        		acceptWindow.setVisible(true);
        	}
        });
        
        // Botón para editar perfil
        JButton btnEdit = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InterBuyerGUI.Edit"));
        contentPane.add(btnEdit);
        btnEdit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		EditProfileGUI editWindow = new EditProfileGUI(buyer);
        		editWindow.setVisible(true);
        	}
        });
	}

}
