package controllers;

import java.awt.CardLayout;
import java.util.ArrayList;

import javax.swing.JFrame;

import models.Delincuente;
import views.FrameVentana;
import views.VentanaDelincuentes;
import views.VentanaLogin;

public class PrincipalController {

	VentanaLogin ventanaLogin;
	VentanaDelincuentes ventanaDelincuentes;
	FrameVentana frame;
	CardLayout cardLayout;
	
	public PrincipalController(JFrame frame) {
		
		ventanaLogin = new VentanaLogin();
		
		ventanaDelincuentes = new VentanaDelincuentes(new DelincuenteController());
		this.frame = (FrameVentana)frame;
		this.frame.setPrincipalController(this);
		this.cardLayout = (CardLayout)frame.getContentPane().getLayout();
		frame.getContentPane().add(ventanaLogin, "login");
		frame.getContentPane().add(ventanaDelincuentes, "delincuentes");

		
	}
	
	public void initFrame() {
		cardLayout.show(frame.getContentPane(), "login");
		
	}

	public void showDelincuentes() {
		cardLayout.show(frame.getContentPane(), "delincuentes");
	}
	
	

	
	
}
