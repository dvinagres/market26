package gui;

import javax.swing.*;
import java.awt.event.*;
import java.util.ResourceBundle; // Importar para los idiomas
import businessLogic.BLFacade;

public class RegisterGUI extends JFrame {
    private JTextField txtName;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JRadioButton radioBuyer;
    private JRadioButton radioSeller;

    public RegisterGUI() {
        // Título dinámico
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register.Title"));
        setBounds(100, 100, 350, 300);
        getContentPane().setLayout(null);

        JLabel lblName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Register.Name") + ":");
        lblName.setBounds(30, 30, 80, 25);
        getContentPane().add(lblName);

        txtName = new JTextField();
        txtName.setBounds(120, 30, 180, 25);
        getContentPane().add(txtName);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 70, 80, 25);
        getContentPane().add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 70, 180, 25);
        getContentPane().add(txtEmail);

        JLabel lblPass = new JLabel("Password:");
        lblPass.setBounds(30, 110, 80, 25);
        getContentPane().add(lblPass);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(120, 110, 180, 25);
        getContentPane().add(txtPassword);

        // Opciones de tipo de usuario con traducción
        radioBuyer = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Register.Buyer"));
        radioBuyer.setBounds(50, 150, 110, 25);
        radioBuyer.setSelected(true); 
        
        radioSeller = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("Register.Seller"));
        radioSeller.setBounds(170, 150, 110, 25);

        ButtonGroup group = new ButtonGroup();
        group.add(radioBuyer);
        group.add(radioSeller);
        
        getContentPane().add(radioBuyer);
        getContentPane().add(radioSeller);

        JButton btnRegistrar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register.BtnRegister"));
        btnRegistrar.setBounds(100, 200, 120, 30);
        getContentPane().add(btnRegistrar);

        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                BLFacade facade = MainGUI.getBusinessLogic();
                
                String name = txtName.getText();
                String email = txtEmail.getText();
                String pass = new String(txtPassword.getPassword());
                
                boolean success = false;
                
                if (radioBuyer.isSelected()) {
                    success = facade.registerBuyer(name, email, pass);
                } else {
                    success = facade.registerSeller(name, email, pass);
                }
                
                if (success) {
                    JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("Register.Success"));
                    dispose(); 
                } else {
                    JOptionPane.showMessageDialog(null, ResourceBundle.getBundle("Etiquetas").getString("Register.ErrorEmail"));
                }
            }
        });
    }
}