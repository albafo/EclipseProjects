package controllers;

import views.FrameVentana;

public class Main {

	public static void main(String[] args) {
		
		FrameVentana frame = new FrameVentana();
		PrincipalController principalController = new PrincipalController(frame);
		principalController.initFrame();
		frame.setVisible(true);

	}

}
