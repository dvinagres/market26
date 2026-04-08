package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.ResourceBundle;
import domain.Buyer;
import domain.Seller;
import domain.Report;
import businessLogic.BLFacade;

public class ReportGUI extends JFrame {

	private JPanel contentPane;
	private Buyer currentBuyer;
	private Seller currentSeller;

	public ReportGUI(Buyer buyer, Seller seller) {
		this.currentBuyer = buyer;
		this.currentSeller = seller;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("ReportGUI.Title") + currentSeller.getName());
		contentPane.setLayout(null);
		
		JLabel lblReason = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReportGUI.Reason"));
		lblReason.setBounds(27, 26, 388, 16);
		contentPane.add(lblReason);
		
		JTextArea textReason = new JTextArea();
		textReason.setBounds(27, 54, 388, 130);
		contentPane.add(textReason);
		
		JButton btnSend = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnSend.setBounds(27, 217, 117, 29);
		contentPane.add(btnSend);
		
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String reason = textReason.getText();
				
				if(reason.trim().isEmpty()) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("ReportGUI.ErrorEmpty"), ResourceBundle.getBundle("Etiquetas").getString("Error"), JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String date = LocalDate.now().toString();
				Report report = new Report(reason, date, currentBuyer.getName(), currentSeller);
		
				BLFacade facade = MainGUI.getBusinessLogic();
				boolean ok = facade.addReport(currentSeller.getEmail(), report);
				
				if (ok) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("ReportGUI.Success"));
				} else {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("ReportGUI.ErrorSend"), ResourceBundle.getBundle("Etiquetas").getString("Error"), JOptionPane.ERROR_MESSAGE);
				}
				
				dispose();
			}
		});
		
		JButton btnCancel = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Cancel"));
		btnCancel.setBounds(298, 217, 117, 29);
		contentPane.add(btnCancel);
		
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
}