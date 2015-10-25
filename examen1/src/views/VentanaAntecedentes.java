package views;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class VentanaAntecedentes extends JPanel {

	/**
	 * Create the panel.
	 */
	public VentanaAntecedentes() {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(40, 22, 61, 16);
		add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setColumns(20);
		textArea.setRows(20);
		textArea.setBounds(50, 50, 360, 189);
		add(textArea);
		
		JButton btnSalvarZ = new JButton("Salvar >>");
		btnSalvarZ.setBounds(60, 251, 117, 29);
		add(btnSalvarZ);

	}
}
