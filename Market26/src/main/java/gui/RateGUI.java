package gui;

import java.awt.EventQueue;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.ResourceBundle;
import javax.swing.JTextArea;
import java.awt.GridLayout;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import domain.Buyer;
import domain.Seller;
import domain.Review;
import businessLogic.BLFacade;

import java.time.LocalDate;
import java.util.List;

public class RateGUI extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private Buyer currentBuyer;
	private Seller currentSeller;

	public RateGUI(Buyer buyer, Seller seller) {
		this.currentBuyer = buyer;
		this.currentSeller = seller;
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("RateGUI.Title") + currentSeller.getName());
		contentPane.setLayout(null);
		
		JRadioButton rdbtn1 = new JRadioButton("1");
		buttonGroup.add(rdbtn1);
		rdbtn1.setBounds(27, 52, 53, 23);
		contentPane.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("2");
		buttonGroup.add(rdbtn2);
		rdbtn2.setBounds(92, 52, 53, 23);
		contentPane.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("3");
		buttonGroup.add(rdbtn3);
		rdbtn3.setBounds(157, 52, 53, 23);
		contentPane.add(rdbtn3);
		
		JRadioButton rdbtn4 = new JRadioButton("4");
		buttonGroup.add(rdbtn4);
		rdbtn4.setBounds(222, 52, 53, 23);
		contentPane.add(rdbtn4);
		
		JRadioButton rdbtn5 = new JRadioButton("5");
		buttonGroup.add(rdbtn5);
		rdbtn5.setBounds(287, 52, 53, 23);
		contentPane.add(rdbtn5);
		
		JTextArea textComment = new JTextArea();
		textComment.setBounds(27, 123, 388, 82);
		contentPane.add(textComment);
		
		JLabel lblRate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RateGUI.Rate"));
		lblRate.setBounds(27, 26, 61, 16);
		contentPane.add(lblRate);
		
		JLabel lblComment = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RateGUI.Comment") + currentSeller.getName());
		lblComment.setBounds(27, 95, 388, 16);
		contentPane.add(lblComment);
		
		JButton btnSend = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
		btnSend.setBounds(27, 217, 117, 29);
		contentPane.add(btnSend);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Recoger resultados de los objetos para crear el Review
				int score = 0;
				
				if(rdbtn1.isSelected()) score = 1;
				else if(rdbtn2.isSelected()) score = 2;
				else if(rdbtn3.isSelected()) score = 3;
				else if(rdbtn4.isSelected()) score = 4;
				else if(rdbtn5.isSelected()) score = 5;
				
				if(score == 0) {
					JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("RateGUI.Error"));
					return;
				}
				
				String buyerName = currentBuyer.getName();
				String comment = textComment.getText();
				// java.time.LocalDate.now() coge la fecha actual
				String date = LocalDate.now().toString();
				Review review = new Review(buyerName, score, comment, date);
		
				BLFacade facade = MainGUI.getBusinessLogic();
				
				boolean ok = facade.addReview(currentSeller.getEmail(), review);
				
				String okMsg = ResourceBundle.getBundle("Etiquetas").getString("RateGUI.RateOk");
				String noMsg = ResourceBundle.getBundle("Etiquetas").getString("RateGUI.RateNo");
				
				if (ok) {
					JOptionPane.showMessageDialog(null, okMsg);
				} else {
					JOptionPane.showMessageDialog(null, noMsg);
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
