package chess.model.board;

/**
 * Projekt: Szachy
 * Test planszy
 * Micha³ Rapacz
 * 2015-03-26
 */

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Color;
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
		for(int x = 0; x < Board.BOARD_SIZE; x++) {
			Piece whitePawn = board.getPiece(x, 6);
			Piece blackPawn = board.getPiece(x, 1);
			assertTrue(whitePawn instanceof Pawn &&
					whitePawn.getColor() == Color.WHITE);
			assertTrue(blackPawn instanceof Pawn &&
					blackPawn.getColor() == Color.BLACK);
		}
	}

	public void checkMiddleBoardIsEmpty() {
		for(int y = 2; y <= 5; y++) {
			for(int x = 0; x < Board.BOARD_SIZE; x++) {
				assertTrue(board.getPiece(x, y) == null);
			}
		}
	}
	
	private void checkFiguresRowIsSetupCorrectly(int y, Color color) {
		for(int x = 0; x < Board.BOARD_SIZE; x++) {
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
		board.movePiece(pawn, 5, 5);
		assertTrue(board.getPiece(5, 6) == null);
		assertTrue(board.getPiece(5, 5) == pawn);
	}
	
	@Test
	public void testBlackPieceIsTakenDuringMove() {
		board.movePiece(board.getPiece(6, 6), 6, 5);
		board.movePiece(board.getPiece(1, 1), 1, 2);
		board.movePiece(board.getPiece(5, 7), 6, 6);
		board.movePiece(board.getPiece(2, 0), 1, 1);
		board.movePiece(board.getPiece(6, 6), 1, 1);
		assertTrue(board.getAllPieces(Color.BLACK).size() == 15);
		assertTrue(board.getAllPieces(Color.WHITE).size() == 16);
	}
	
	@Test
	public void testBoardCopy() {
		Board copy = new Board(board);
		for(int x = 0; x < Board.BOARD_SIZE; x++) {
			for(int y = 0; y < Board.BOARD_SIZE; y++) {
				Piece oldPiece = board.getPiece(x, y);
				Piece newPiece = copy.getPiece(x, y);
				assertTrue((oldPiece == null && newPiece == null) ||
						oldPiece.getField().equals(newPiece.getField()));
			}
		}
	}
	
	@Test
	public void testRecordingPawnThatCanBeTakenEnPassant() {
		Pawn whitePawn = (Pawn) board.getPiece(5, 6);
		Pawn blackPawn = (Pawn) board.getPiece(4, 1);
		Pawn blackPawnToBeTakenEnPassant = (Pawn) board.getPiece(6, 1);
		board.movePiece(whitePawn, 5, 4);
		board.movePiece(blackPawn, 4, 3);
		board.movePiece(whitePawn, 5, 3);
		board.movePiece(blackPawnToBeTakenEnPassant, 6, 3);
		assertTrue(board.canPawnBeTakenEnPassant(blackPawnToBeTakenEnPassant));
		assertFalse(board.canPawnBeTakenEnPassant(blackPawn));
		assertFalse(board.canPawnBeTakenEnPassant(whitePawn));
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
		Knight whiteKnight = (Knight) board.getPiece(1, 7);
		board.movePiece(whiteKnight, 2, 5);
		board.movePiece(whiteKnight, 3, 3);
		board.movePiece(whiteKnight, 2, 1);
		assertEquals(GameState.CHECK, board.getGameState(Color.BLACK));
	}
	
	@Test
	public void stateIsMateWhenOneOfKingsIsMated() {
		board.movePiece(board.getPiece(5, 6), 5, 5);
		board.movePiece(board.getPiece(4, 1), 4, 2);
		board.movePiece(board.getPiece(6, 6), 6, 4);
		board.movePiece(board.getPiece(3, 0), 7, 4);
		assertEquals(GameState.MATE, board.getGameState(Color.WHITE));
	}
	
	@Test
	public void stateIsStalemateWhenOneOfKingsIsStalemated() {
		board = new Board();
		board.movePiece(new King(Color.WHITE, 7, 7), 7, 7);
		board.movePiece(new Queen(Color.WHITE, 1, 2), 1, 2);
		board.movePiece(new King(Color.BLACK, 0, 0), 0, 0);
		board = new Board(board);
		assertEquals(GameState.STALEMATE, board.getGameState(Color.BLACK));
	}
}
