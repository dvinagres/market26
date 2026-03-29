package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle; // Importante añadir esto
import businessLogic.BLFacade;
import domain.Buyer;
import domain.Seller;

public class LoginGUI extends JFrame {
    private JTextField txtEmail;
    private JPasswordField txtPassword;

    public LoginGUI() {
        // Título dinámico
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginTitle"));
        setBounds(100, 100, 300, 200);
        getContentPane().setLayout(null);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 30, 80, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(100, 30, 150, 25);
        getContentPane().add(txtEmail);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 70, 80, 25);
        getContentPane().add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(100, 70, 150, 25);
        getContentPane().add(txtPassword);

        // Botón con texto dinámico
        JButton btnLogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginBtn"));
        btnLogin.setBounds(90, 115, 100, 30);
        getContentPane().add(btnLogin);

        btnLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BLFacade facade = MainGUI.getBusinessLogic();
                
                String email = txtEmail.getText();
                String pass = new String(txtPassword.getPassword());
                
                Object user = facade.login(email, pass);
                
                if (user != null) {
                    if (user instanceof Buyer) {
                        // Mensaje de bienvenida dinámico para Comprador
                        String msgBuyer = ResourceBundle.getBundle("Etiquetas").getString("Login.WelcomeBuyer") 
                                          + " " + ((Buyer)user).getName() + "!";
                        JOptionPane.showMessageDialog(null, msgBuyer);
                        
                        AcceptSaleGUI acceptWindow = new AcceptSaleGUI((Buyer)user);
                        acceptWindow.setVisible(true);
                    } else if (user instanceof Seller) {
                        // Mensaje de bienvenida dinámico para Vendedor
                        String msgSeller = ResourceBundle.getBundle("Etiquetas").getString("Login.WelcomeSeller") 
                                           + " " + ((Seller)user).getName() + "!";
                        JOptionPane.showMessageDialog(null, msgSeller);
                        
                        ViewAcceptedSalesGUI viewSalesWindow = new ViewAcceptedSalesGUI((Seller)user);
                        viewSalesWindow.setVisible(true);
                    }
                    dispose(); 
                } else {
                    // Mensaje de error dinámico
                    JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("LoginError"));
                }
            }
        });
    }
}