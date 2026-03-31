package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle; 
import businessLogic.BLFacade;
import java.awt.Font;
import domain.Buyer;
import domain.Seller;

public class EditProfileGUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    // Objeto genérico para Buyer y Seller
	public EditProfileGUI(Object obj) {
		
		// Cambiar todos los textos por dinámicos 
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.Title"));
        setBounds(100, 100, 350, 300);
        getContentPane().setLayout(null);
        
        JLabel lblName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.Name"));
        lblName.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblName.setBounds(30, 30, 80, 25);
        getContentPane().add(lblName);
        
        txtName = new JTextField();
        txtName.setBounds(120, 30, 180, 25);
        getContentPane().add(txtName);
        
        JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.Email"));
        lblEmail.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblEmail.setBounds(30, 70, 80, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 70, 180, 25);
        getContentPane().add(txtEmail);
        
        JLabel lblPass = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.Pass"));
        lblPass.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
        lblPass.setBounds(30, 110, 80, 25);
        getContentPane().add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 110, 180, 25);
        getContentPane().add(txtPassword);
        
        JButton btnConfirmar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
        btnConfirmar.setBounds(30, 184, 117, 29);
        getContentPane().add(btnConfirmar);
        btnConfirmar.addActionListener(new ActionListener() {
        	
        	public void actionPerformed(ActionEvent e) {
        		BLFacade facade = MainGUI.getBusinessLogic();
        		
        		String currentMail;
        		
        		String newName = txtName.getText();
        		String newMail = txtEmail.getText();
        		String newPass = new String(txtPassword.getPassword());
        		
        		String okMsg;
        		String noMsg;
        		
        		
        		if(obj instanceof Buyer) {
        			Buyer b = (Buyer)obj;
        			currentMail = b.getEmail();
        		} else {
        			Seller s = (Seller)obj;
        			currentMail = s.getEmail();
        		}
        		
        		if(!newName.isEmpty()) {
        			okMsg = ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.NameOk") + newName;
        			noMsg = ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.NameNo");

        			if (facade.editName(currentMail, newName)) {
        				JOptionPane.showMessageDialog(null, okMsg);
        			} else {
        				JOptionPane.showMessageDialog(null, noMsg);
        			}
        		}
        		
        		if(!newMail.isEmpty()) {
        			okMsg = ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.MailOk") + newMail;
        			noMsg = ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.MailNo");

        			if (facade.editMail(currentMail, newMail) && !newMail.equals(currentMail)) {
        				JOptionPane.showMessageDialog(null, okMsg);
        			} else {
        				JOptionPane.showMessageDialog(null, noMsg);
        			}
        		}
        		
        		if(!newPass.isEmpty()) {
        			okMsg = ResourceBundle.getBundle("Etiquetas").getString("EditProfileGUI.PassOk");

        			if (facade.editPassword(currentMail, newPass)) {
        				JOptionPane.showMessageDialog(null, okMsg);
        			}
        		}
        		
        		dispose();
        	}
        	
        });
        
        JButton btnCancelar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Cancel")); 
        btnCancelar.setBounds(183, 184, 117, 29);
        getContentPane().add(btnCancelar);
        btnCancelar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        
        
	}
}
