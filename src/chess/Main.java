package chess;

/**
 * Projekt: Szachy
 * Klasa uruchamiaj�ca program
 * Micha� Rapacz
 * 2016-03-26
 */

import chess.controller.ApplicationController;

public class Main {

	public static void main(String[] args) {
		ApplicationController.getInstance().createView();
	}

}
