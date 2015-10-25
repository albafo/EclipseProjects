package views;

import javax.swing.JPanel;
import javax.swing.JList;

import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import controllers.DelincuenteController;
import models.Delincuente;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class VentanaDelincuentes extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private DefaultListModel<Delincuente> listModel = new DefaultListModel<Delincuente>();
	private DelincuenteController delincuenteController;
	private JList list;
	/**
	 * Create the panel.
	 */
	public VentanaDelincuentes(DelincuenteController delincuenteController) {
		
		this.delincuenteController = delincuenteController;
		setLayout(new GridLayout(0, 2, 0, 0));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		reloadDelincuentesModel();
		
		list = new JList(listModel);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				
			}
		});
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(0, 0, 150, 300);
		panel.add(scrollPane);
		
		JPanel panel_1 = new JPanel();
		add(panel_1);
		panel_1.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 39, 157, 28);
		panel_1.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(6, 17, 61, 16);
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(6, 82, 61, 16);
		panel_1.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(6, 99, 61, 28);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(102, 82, 61, 16);
		panel_1.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(102, 99, 61, 28);
		panel_1.add(textField_2);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setBounds(6, 138, 61, 16);
		panel_1.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(6, 160, 157, 28);
		panel_1.add(textField_3);
		
		JLabel lblNewLabel_4 = new JLabel("New label");
		lblNewLabel_4.setBounds(6, 198, 61, 16);
		panel_1.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(6, 214, 157, 28);
		panel_1.add(textField_4);
		
		JLabel lblNewLabel_5 = new JLabel("New label");
		lblNewLabel_5.setBounds(6, 247, 61, 16);
		panel_1.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(6, 266, 157, 28);
		panel_1.add(textField_5);

	}

	private void reloadDelincuentesModel() {
		listModel.removeAllElements();
		ArrayList<Delincuente> delincuentes=delincuenteController.getDelincuetes();
		
		for(int i=0; i<delincuentes.size(); i++) {
			
			listModel.addElement(delincuentes.get(i));
		}
		
		
	}
}
