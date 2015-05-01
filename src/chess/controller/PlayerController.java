package chess.controller;

import java.util.List;

import chess.model.board.Color;
import chess.model.players.AlphaBetaPlayer;
import chess.model.players.GreedyPlayer;
import chess.model.players.HumanPlayer;
import chess.model.players.MinimaxPlayer;
import chess.model.players.Player;
import chess.model.players.PlayerFactory;
import chess.model.players.PlayerFactory.PlayerMaker;
import chess.model.players.RandomPlayer;
import chess.model.players.strategies.StandardBoardEvaluationStrategy;

public class PlayerController {

	private static PlayerController instance;
	
	private PlayerFactory playerFactory = new PlayerFactory();
	
	private PlayerController() { }
	
	public static PlayerController getInstance() {
		if(instance == null) {
			instance = new PlayerController();
			instance.registerAvailablePlayers();
		}
		return instance;
	}

	private void registerAvailablePlayers() {
		playerFactory.registerPlayerMaker("Cz³owiek", new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new HumanPlayer(color);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - losowy",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new RandomPlayer(color);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - zach³anny",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new GreedyPlayer(color,
						new StandardBoardEvaluationStrategy(color));
			}
		});
		playerFactory.registerPlayerMaker("Komputer - minimax (3)",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new MinimaxPlayer(color,
						new StandardBoardEvaluationStrategy(color), 3);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - minimax (4)",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new MinimaxPlayer(color,
						new StandardBoardEvaluationStrategy(color), 4);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - alpha-beta (4)",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new AlphaBetaPlayer(color,
						new StandardBoardEvaluationStrategy(color), 4);
			}
		});
		playerFactory.registerPlayerMaker("Komputer - alpha-beta (5)",
				new PlayerMaker() {
			@Override
			public Player makePlayer(Color color) {
				return new AlphaBetaPlayer(color,
						new StandardBoardEvaluationStrategy(color), 5);
			}
		});
	}
	
	public List<String> getAvailablePlayers() {
		return playerFactory.getAvailablePlayers();
	}
	
	public Player makePlayer(String name, Color color) {
		return playerFactory.makePlayer(name, color);
	}

}
