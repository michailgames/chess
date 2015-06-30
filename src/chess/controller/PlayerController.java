package chess.controller;

import java.util.List;

import chess.model.board.Color;
import chess.model.players.AlphaBetaPlayer;
import chess.model.players.GreedyPlayer;
import chess.model.players.HumanPlayer;
import chess.model.players.Player;
import chess.model.players.PlayerFactory;
import chess.model.players.PlayerFactory.PlayerMaker;
import chess.model.players.RandomPlayer;
import chess.model.players.strategies.StandardBoardEvaluationStrategy;

public class PlayerController {

    private static PlayerController instance;

    private PlayerFactory playerFactory = new PlayerFactory();

    private PlayerController() {}

    public static PlayerController getInstance() {
        if (instance == null) {
            instance = new PlayerController();
            instance.registerAvailablePlayers();
        }
        return instance;
    }

    private void registerAvailablePlayers() {
        playerFactory.registerPlayerMaker("Human", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new HumanPlayer(color);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 0", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new RandomPlayer(color);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 1", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new GreedyPlayer(color, new StandardBoardEvaluationStrategy(color));
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 2", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new AlphaBetaPlayer(color, new StandardBoardEvaluationStrategy(color), 2);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 3", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new AlphaBetaPlayer(color, new StandardBoardEvaluationStrategy(color), 3);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 4", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new AlphaBetaPlayer(color, new StandardBoardEvaluationStrategy(color), 4);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 5", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new AlphaBetaPlayer(color, new StandardBoardEvaluationStrategy(color), 5);
            }
        });
        playerFactory.registerPlayerMaker("CPU - level 6", new PlayerMaker() {

            @Override
            public Player makePlayer(Color color) {
                return new AlphaBetaPlayer(color, new StandardBoardEvaluationStrategy(color), 6);
            }
        });
        // playerFactory.registerPlayerMaker(
        // "CPU - adaptive alpha-beta (3-10s)", new PlayerMaker() {
        // @Override
        // public Player makePlayer(Color color) {
        // return new AdaptiveAlphaBetaPlayer(color,
        // new StandardBoardEvaluationStrategy(color), 4, 3000, 10000);
        // }
        // });
    }

    public List<String> getAvailablePlayers() {
        return playerFactory.getAvailablePlayers();
    }

    public Player makePlayer(String name, Color color) {
        return playerFactory.makePlayer(name, color);
    }

}
