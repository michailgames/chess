package chess.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.controller.GameController.IllegalMoveException;
import chess.controller.GameController.UnauthorizedMoveException;
import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.players.AbstractAIPlayer;
import chess.model.players.NonPlayingPlayer;
import chess.model.players.Player;
import chess.model.players.RandomPlayer;

public class GameControllerTest {

    private Player whitePlayer;

    private Player blackPlayer;

    @Before
    public void setup() {
        whitePlayer = new NonPlayingPlayer(Color.WHITE);
        blackPlayer = new NonPlayingPlayer(Color.BLACK);
        GameController.getInstance().startNewGame(whitePlayer, blackPlayer);
    }

    @Test(expected = UnauthorizedMoveException.class)
    public void doesNotAcceptMoveFromOtherPlayer() {
        GameController.getInstance().reportNewMove(blackPlayer, Field.get(0, 1), Field.get(0, 2));
    }

    @Test
    public void acceptsMoveFromCurrentPlayer() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));
    }

    @Test(expected = UnauthorizedMoveException.class)
    public void doesNotAcceptTwoMovesFromOnePlayeInRow() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(1, 6), Field.get(1, 5));
    }

    @Test
    public void acceptsMovesFromPlayersInTurns() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));
        GameController.getInstance().reportNewMove(blackPlayer, Field.get(1, 1), Field.get(1, 2));
    }

    @Test(expected = IllegalMoveException.class)
    public void doesNotAcceptIllegalMoves() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(3, 5));
    }

    @Test(expected = IllegalMoveException.class)
    public void doesNotAcceptMovingOpponentPieces() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 1), Field.get(0, 2));
    }

    @Test
    public void undoRestoresPreviousBoard() {
        Board initialBoard = GameController.getInstance().getBoard();

        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));
        assertEquals(GameController.getInstance().getCurrentPlayerColor(), Color.BLACK);
        assertFalse(initialBoard.equals(GameController.getInstance().getBoard()));

        GameController.getInstance().undoMove();
        assertTrue(initialBoard.equals(GameController.getInstance().getBoard()));
        assertEquals(GameController.getInstance().getCurrentPlayerColor(), Color.WHITE);
    }

    @Test
    public void undo2Moves() {
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));

        Board expectedBoard = GameController.getInstance().getBoard();

        GameController.getInstance().reportNewMove(blackPlayer, Field.get(0, 1), Field.get(0, 2));
        GameController.getInstance().reportNewMove(whitePlayer, Field.get(1, 6), Field.get(1, 5));

        GameController.getInstance().undoMove();
        GameController.getInstance().undoMove();

        assertTrue(expectedBoard.equals(GameController.getInstance().getBoard()));
        assertEquals(GameController.getInstance().getCurrentPlayerColor(), Color.BLACK);
    }

    @Test(timeout = 8000)
    public void undoStopsAIPlayerCalculation() throws InterruptedException {
        blackPlayer = new RandomPlayer(Color.BLACK);
        GameController.getInstance().startNewGame(whitePlayer, blackPlayer);

        Board expectedBoard = GameController.getInstance().getBoard();

        GameController.getInstance().reportNewMove(whitePlayer, Field.get(0, 6), Field.get(0, 5));
        while (GameController.getInstance().getSelectedPiece() == null) {
            // wait until AI selects piece
        }
        GameController.getInstance().undoMove();
        assertNull(GameController.getInstance().getSelectedPiece());
        Thread.sleep(AbstractAIPlayer.MINIMUM_MOVE_TIME);
        assertTrue(expectedBoard.equals(GameController.getInstance().getBoard()));
    }

}
