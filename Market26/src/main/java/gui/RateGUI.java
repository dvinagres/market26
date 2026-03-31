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
import javax.swing.ButtonGroup;

import domain.Buyer;
import domain.Seller;

public class RateGUI extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public RateGUI(Buyer buyer, Seller seller) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
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
		
		JButton btnSend = new JButton("Enviar");
		btnSend.setBounds(27, 217, 117, 29);
		contentPane.add(btnSend);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setBounds(298, 217, 117, 29);
		contentPane.add(btnCancel);
		
		JLabel lblRate = new JLabel("Valora");
		lblRate.setBounds(27, 26, 61, 16);
		contentPane.add(lblRate);
		
		JLabel lblComment = new JLabel("Deja un comentario sobre el vendedor");
		lblComment.setBounds(27, 95, 388, 16);
		contentPane.add(lblComment);
		setTitle("Valoración");

	}
}
