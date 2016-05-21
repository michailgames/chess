package chess.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.model.board.Color;
import chess.model.board.Field;
import chess.model.pieces.Pawn;
import chess.model.players.NonPlayingPlayer;
import chess.model.players.Player;

public class LogControllerTest {

    private Player whitePlayer;

    private Player blackPlayer;

    @Before
    public void setup() {
        whitePlayer = new NonPlayingPlayer(Color.WHITE);
        blackPlayer = new NonPlayingPlayer(Color.BLACK);
        GameController.getInstance().startNewGame(whitePlayer, blackPlayer);
    }

    @Test
    public void moveIsLoggedProperly() {
        assertEquals(0, LogController.getInstance().getLogsNumber());
        GameController.getInstance().reportNewMove(whitePlayer, new Field(6, 6), new Field(6, 4));
        assertEquals(1, LogController.getInstance().getLogsNumber());
        String moveLog = LogController.getInstance().getLog(0);
        assertTrue(moveLog.contains(Pawn.getInstance(Color.WHITE).getUnicodeString()));
        assertTrue(moveLog.contains("g2"));
        assertTrue(moveLog.contains("g4"));
    }

    @Test
    public void oneMoveCausesOneLog() {
        assertEquals(0, LogController.getInstance().getLogsNumber());
        GameController.getInstance().reportNewMove(whitePlayer, new Field(6, 6), new Field(6, 4));
        assertEquals(1, LogController.getInstance().getLogsNumber());
        GameController.getInstance().reportNewMove(blackPlayer, new Field(6, 0), new Field(7, 2));
        assertEquals(2, LogController.getInstance().getLogsNumber());
        GameController.getInstance().reportNewMove(whitePlayer, new Field(5, 7), new Field(7, 5));
        assertEquals(3, LogController.getInstance().getLogsNumber());
    }

    @Test
    public void startingNewGameClearsLogs() {
        assertEquals(0, LogController.getInstance().getLogsNumber());
        GameController.getInstance().reportNewMove(whitePlayer, new Field(6, 6), new Field(6, 4));
        GameController.getInstance().reportNewMove(blackPlayer, new Field(6, 0), new Field(7, 2));
        GameController.getInstance().reportNewMove(whitePlayer, new Field(5, 7), new Field(7, 5));
        assertEquals(3, LogController.getInstance().getLogsNumber());

        GameController.getInstance().startNewGame(whitePlayer, blackPlayer);
        assertEquals(0, LogController.getInstance().getLogsNumber());
    }
}
