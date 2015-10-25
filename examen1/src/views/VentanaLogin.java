package views;

import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.JLabel;

public class VentanaLogin extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public VentanaLogin() {
		setLayout(new GridLayout(0, 3));
		
		JPanel panel = new JPanel();
		add(panel);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(6, 53, 138, 28);
		panel.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(6, 6, 138, 45);
		panel.add(comboBox);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBounds(6, 135, 138, 29);
		panel.add(btnLogin);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.setEnabled(false);
		btnLogout.setBounds(6, 176, 138, 29);
		panel.add(btnLogout);
		
		JLabel lblNewLabel = new JLabel("Mensaje Error");
		lblNewLabel.setBounds(6, 278, 138, 16);
		panel.add(lblNewLabel);

	}

}
