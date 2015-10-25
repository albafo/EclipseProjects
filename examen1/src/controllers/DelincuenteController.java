package controllers;

import java.util.ArrayList;

import models.Delincuente;

public class DelincuenteController {
	
	
	public ArrayList<Delincuente> getDelincuetes() {
		
		return Delincuente.loadFromDB(false);
	}
}
