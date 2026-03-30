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
		
		// Título dinámico con el nombre del comprador
        setTitle(ResourceBundle.getBundle("Etiquetas").getString("AcceptSale.Title") + " - " + buyer.getName());
        setBounds(100, 100, 480, 250); 
        
        // Cambiar por dinámico para seleccionar opción
        JLabel lblSelect = new JLabel("Selecciona una opción");
        lblSelect.setFont(new Font("Tahoma", Font.BOLD, 13));
        lblSelect.setHorizontalAlignment(SwingConstants.CENTER);
        lblSelect.setForeground(new Color(0, 0, 0));
        contentPane.add(lblSelect);
        
        // Botón para comprar
        JButton btnBuy = new JButton("Empezar a comprar"); 
        contentPane.add(btnBuy);
        
        
        // Botón para editar perfil
        JButton btnEdit = new JButton("Editar perfil"); 
        contentPane.add(btnEdit);

	}

}
