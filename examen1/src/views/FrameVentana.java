package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;

import controllers.PrincipalController;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FrameVentana extends JFrame {

	private JPanel contentPane;
	private PrincipalController principalController;

	
	/**
	 * Create the frame.
	 */
	public FrameVentana() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JMenuBar menuBar = new JMenuBar();
		
		JMenu menuPrincipal = new JMenu("Principal");
		menuPrincipal.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				principalController.initFrame();
			}
		});
		menuBar.add(menuPrincipal);
		
		JMenu menuDelincuentes = new JMenu("Delincuentes");
		menuDelincuentes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				principalController.showDelincuentes();
			}
		});
		menuBar.add(menuDelincuentes);
		
		
		this.setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new CardLayout());
		
		setContentPane(contentPane);
	}
	
	public void setPrincipalController(PrincipalController principalController) {
		this.principalController = principalController;
	}
	
	

}
