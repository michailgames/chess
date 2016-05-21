package chess.model.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.model.pieces.Bishop;
import chess.model.pieces.King;
import chess.model.pieces.Knight;
import chess.model.pieces.Pawn;
import chess.model.pieces.Piece;
import chess.model.pieces.Queen;
import chess.model.pieces.Rook;

public class BoardTest {

    private Board board;

    @Before
    public void createBoard() {
        board = new Board();
        board.setup();
    }

    @Test
    public void testSetup() {
        checkMiddleBoardIsEmpty();
        checkPawnsAreSetupCorrectly();
        checkFiguresRowIsSetupCorrectly(0, Color.BLACK);
        checkFiguresRowIsSetupCorrectly(7, Color.WHITE);
    }

    public void checkPawnsAreSetupCorrectly() {
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            Piece whitePawn = board.getPiece(x, 6);
            Piece blackPawn = board.getPiece(x, 1);
            assertTrue(whitePawn instanceof Pawn && whitePawn.getColor() == Color.WHITE);
            assertTrue(blackPawn instanceof Pawn && blackPawn.getColor() == Color.BLACK);
        }
    }

    public void checkMiddleBoardIsEmpty() {
        for (int y = 2; y <= 5; y++) {
            for (int x = 0; x < Board.BOARD_SIZE; x++) {
                assertTrue(board.getPiece(x, y) == null);
            }
        }
    }

    private void checkFiguresRowIsSetupCorrectly(int y, Color color) {
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            assertTrue(board.getPiece(x, y).getColor() == color);
        }
        assertTrue(board.getPiece(0, y) instanceof Rook);
        assertTrue(board.getPiece(1, y) instanceof Knight);
        assertTrue(board.getPiece(2, y) instanceof Bishop);
        assertTrue(board.getPiece(3, y) instanceof Queen);
        assertTrue(board.getPiece(4, y) instanceof King);
        assertTrue(board.getPiece(5, y) instanceof Bishop);
        assertTrue(board.getPiece(6, y) instanceof Knight);
        assertTrue(board.getPiece(7, y) instanceof Rook);
    }

    @Test
    public void testMovingPiece() {
        Piece pawn = board.getPiece(5, 6);
        board.movePiece(5, 6, 5, 5);
        assertTrue(board.getPiece(5, 6) == null);
        assertTrue(board.getPiece(5, 5) == pawn);
    }

    @Test
    public void testBlackPieceIsTakenDuringMove() {
        board.movePiece(6, 6, 6, 5);
        board.movePiece(1, 1, 1, 2);
        board.movePiece(5, 7, 6, 6);
        board.movePiece(2, 0, 1, 1);
        board.movePiece(6, 6, 1, 1);
        assertTrue(board.getAllPieces(Color.BLACK).size() == 15);
        assertTrue(board.getAllPieces(Color.WHITE).size() == 16);
    }

    @Test
    public void testBoardCopy() {
        Board copy = new Board(board);
        for (int x = 0; x < Board.BOARD_SIZE; x++) {
            for (int y = 0; y < Board.BOARD_SIZE; y++) {
                Piece oldPiece = board.getPiece(x, y);
                Piece newPiece = copy.getPiece(x, y);
                assertTrue(oldPiece == newPiece);
            }
        }
    }

    @Test
    public void testRecordingPawnThatCanBeTakenEnPassant() {
        board.movePiece(5, 6, 5, 4);
        board.movePiece(4, 1, 4, 3);
        board.movePiece(5, 4, 5, 3);
        board.movePiece(6, 1, 6, 3);
        assertTrue(board.canPawnBeTakenEnPassant(Field.get(6, 3)));
        assertFalse(board.canPawnBeTakenEnPassant(Field.get(4, 3)));
        assertFalse(board.canPawnBeTakenEnPassant(Field.get(5, 3)));
    }

    @Test
    public void emptyBoardStateIsNone() {
        board = new Board();
        assertEquals(GameState.NONE, board.getGameState(Color.WHITE));
    }

    @Test
    public void setupBoardStateIsOpen() {
        assertEquals(GameState.OPEN, board.getGameState(Color.WHITE));
    }

    @Test
    public void stateIsCheckedWhenOneOfKingsIsChecked() {
        board.movePiece(1, 7, 2, 5);
        board.movePiece(2, 5, 3, 3);
        board.movePiece(3, 3, 2, 1);
        assertEquals(GameState.CHECK, board.getGameState(Color.BLACK));
    }

    @Test
    public void stateIsMateWhenOneOfKingsIsMated() {
        board.movePiece(5, 6, 5, 5);
        board.movePiece(4, 1, 4, 2);
        board.movePiece(6, 6, 6, 4);
        board.movePiece(3, 0, 7, 4);
        assertEquals(GameState.MATE, board.getGameState(Color.WHITE));
    }

    @Test
    public void stateIsStalemateWhenOneOfKingsIsStalemated() {
        board = new Board();
        board.insertPiece(7, 7, King.getInstance(Color.WHITE));
        board.insertPiece(1, 2, Queen.getInstance(Color.WHITE));
        board.insertPiece(0, 0, King.getInstance(Color.BLACK));
        board = new Board(board);
        assertEquals(GameState.STALEMATE, board.getGameState(Color.BLACK));
    }

    @Test
    public void equalsShouldReturnTrueForEquivalentBoards() {
        Board board2 = new Board();
        board2.setup();
        assertTrue(board.equals(board2));
    }

    @Test
    public void equalsShouldReturnFalseForDifferingBoards() {
        Board board2 = new Board();
        board2.setup();
        board2.movePiece(3, 6, 3, 5);
        assertFalse(board.equals(board2));
    }
}
