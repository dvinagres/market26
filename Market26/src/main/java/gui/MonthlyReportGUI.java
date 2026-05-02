package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.util.List;
import java.util.ResourceBundle;
import domain.Seller;
import domain.Review;
import businessLogic.BLFacade;
import java.awt.Color;

public class MonthlyReportGUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;

	public MonthlyReportGUI(Seller seller) {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Title") + seller.getName());
		setBounds(100, 100, 500, 420);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Summary"));
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTitle.setBounds(20, 20, 400, 25);
		contentPane.add(lblTitle);
		
		// Definición de las cabeceras de la tabla
		String[] columnNames = {
				ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Score"),
				ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Comment"),
				ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Date"),
				ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Buyer")};

		tableModel = new DefaultTableModel(null, columnNames);
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(20, 60, 490, 220);
		contentPane.add(scrollPane);

		BLFacade facade = MainGUI.getBusinessLogic();
		List<Review> reviews = facade.getSellerReviews(seller.getEmail());

		if (reviews == null || reviews.isEmpty()) {
			JLabel lblNoReviews = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.NoReviews"));
			lblNoReviews.setBounds(20, 290, 400, 25);
			contentPane.add(lblNoReviews);
		} else {
			double totalScore = 0;
			StringBuilder sb = new StringBuilder();
			
			for (Review r : reviews) {
				totalScore += r.getScore();
				Object[] row = {
					r.getScore(),
					r.getComment(),
					r.getDate(),
					r.getBuyerName()
				};
				tableModel.addRow(row);
			}
			
			double average = totalScore / reviews.size();
			
			JLabel lblAverage = new JLabel(String.format(ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.Average") + " %.1f / 5.0", average));
			lblAverage.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblAverage.setForeground(new Color(0, 102, 0));
			lblAverage.setBounds(20, 290, 250, 30);
			contentPane.add(lblAverage);
		}

		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MonthlyReport.BtnClose"));
		btnClose.setBounds(320, 330, 140, 30);
		contentPane.add(btnClose);
		btnClose.addActionListener(e -> dispose());
	}
}