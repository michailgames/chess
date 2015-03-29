package chess.controller;

import java.util.List;

import chess.model.Color;
import chess.model.Player;
import chess.model.PlayerFactory;

public class PlayerController {

	private static PlayerController instance;
	
	private PlayerFactory playerFactory = new PlayerFactory();
	
	private PlayerController() { }
	
	public static PlayerController getInstance() {
		if(instance == null) {
			instance = new PlayerController();
		}
		return instance;
	}
	
	public List<String> getAvailablePlayers() {
		return playerFactory.getAvailablePlayers();
	}
	
	public Player makePlayer(String name, Color color) {
		return playerFactory.makePlayer(name, color);
	}

}
