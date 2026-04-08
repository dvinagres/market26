package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle; 
import domain.Seller;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;

public class InterSellerGUI extends JFrame {

	private JPanel contentPane;
	private Seller currentSeller;

	public InterSellerGUI(Seller seller) {
		this.currentSeller = seller;
		
		setBounds(100, 100, 480, 300); 
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 1, 0, 0));
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("InterSellerGUI.Title") + ": " + seller.getName());
        
		JLabel lblSelect = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InterSellerGUI.Option"));
		lblSelect.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelect.setForeground(new Color(0, 0, 0));
		contentPane.add(lblSelect);
        
		JButton btnBuy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InterSellerGUI.Sales"));
		contentPane.add(btnBuy);
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ViewAcceptedSalesGUI acceptWindow = new ViewAcceptedSalesGUI(seller);
				acceptWindow.setVisible(true);
			}
		});
        
		JButton btnEdit = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InterSellerGUI.Edit")); 
		contentPane.add(btnEdit);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditProfileGUI editWindow = new EditProfileGUI(seller);
				editWindow.setVisible(true);
			}
		});
		
		JButton btnReport = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InterSellerGUI.Report")); 
		contentPane.add(btnReport);
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MonthlyReportGUI reportWindow = new MonthlyReportGUI(seller);
				reportWindow.setVisible(true);
			}
		});
	}
}