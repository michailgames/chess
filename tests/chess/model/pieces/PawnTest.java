package chess.model.pieces;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

/**
 * Projekt: Szachy
 * Test piona
 * Micha� Rapacz
 * 2015-03-26
 */

public class PawnTest {

	@Test
	public void testCopy() {
		Pawn oldPawn = new Pawn(Color.BLACK, 2, 7);
		Piece newPawn = oldPawn.copy();
		assertTrue(newPawn instanceof Pawn &&
				newPawn.getColor() == Color.BLACK && newPawn.getX() == 2 &&
				newPawn.getY() == 7);
	}
	
	@Test
	public void whitePawnCanMoveOneOrTwoFieldsInTheBeginning() {
		Board board = new Board();
		board.setup();
		Pawn whitePawn = (Pawn) board.getPiece(5, 6);
		List<Field> allMoves = whitePawn.getAllLegalMoves(board, 5, 6);
		assertEquals(2, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 5)));
		assertTrue(allMoves.contains(new Field(5, 4)));
	}
	
	@Test
	public void blackPawnCanMoveOneOrTwoFieldsInTheBeginning() {
		Board board = new Board();
		board.setup();
		Pawn blackPawn = (Pawn) board.getPiece(5, 1);
		List<Field> allMoves = blackPawn.getAllLegalMoves(board, 5, 1);
		assertEquals(2, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 2)));
		assertTrue(allMoves.contains(new Field(5, 3)));
	}
	
	@Test
	public void whitePawnCanMoveOneFieldInTheMiddle() {
		Board board = new Board();
		board.setup();
		Pawn whitePawn = (Pawn) board.getPiece(5, 6);
		board.movePiece(5, 6, 5, 4);
		List<Field> allMoves = whitePawn.getAllLegalMoves(board, 5, 4);
		assertEquals(1, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 3)));
	}
	
	@Test
	public void blackPawnCanMoveOneFieldInTheMiddle() {
		Board board = new Board();
		board.setup();
		Pawn blackPawn = (Pawn) board.getPiece(5, 1);
		board.movePiece(5, 1, 5, 3);
		List<Field> allMoves = blackPawn.getAllLegalMoves(board, 5, 3);
		assertEquals(1, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 4)));
	}
	
	@Test
	public void pawnsCannotTakeFiguresInFrontOfThem() {
		Board board = new Board();
		board.setup();
		Pawn whitePawn = (Pawn) board.getPiece(5, 6);
		board.movePiece(5, 6, 5, 4);
		Pawn blackPawn = (Pawn) board.getPiece(5, 1);
		board.movePiece(5, 1, 5, 3);
		assertTrue(whitePawn.getAllLegalMoves(board, 5, 4).isEmpty());
		assertTrue(blackPawn.getAllLegalMoves(board, 5, 3).isEmpty());
	}
	
	@Test
	public void pawnsCanTakeDiagonally() {
		Board board = new Board();
		board.setup();
		Pawn whitePawn = (Pawn) board.getPiece(4, 6);
		board.movePiece(4, 6, 4, 4);
		Pawn blackPawn = (Pawn) board.getPiece(5, 1);
		board.movePiece(5, 1, 5, 3);
		List<Field> whiteMoves = whitePawn.getAllLegalMoves(board, 4, 4);
		List<Field> blackMoves = blackPawn.getAllLegalMoves(board, 5, 3);
		assertEquals(2, whiteMoves.size());
		assertEquals(2, blackMoves.size());
		assertTrue(whiteMoves.contains(blackPawn.getField()));
		assertTrue(whiteMoves.contains(new Field(4, 3)));
		assertTrue(blackMoves.contains(whitePawn.getField()));
		assertTrue(blackMoves.contains(new Field(5, 4)));
	}
	
	@Test
	public void whitePawnCanTakeEnPassant() {
		Board board = new Board();
		board.setup();
		Pawn whitePawn = (Pawn) board.getPiece(4, 6);
		board.movePiece(4, 6, 4, 4);
		board.movePiece(4, 4, 4, 3);
		board.movePiece(5, 1, 5, 3);
		List<Field> allMoves = whitePawn.getAllLegalMoves(board, 4, 3);
		assertEquals(2, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 2)));
		assertTrue(allMoves.contains(new Field(4, 2)));
	}
	
	
	@Test
	public void blackPawnCanTakeEnPassant() {
		Board board = new Board();
		board.setup();
		Pawn blackPawn = (Pawn) board.getPiece(5, 1);
		board.movePiece(5, 1, 5, 3);
		board.movePiece(5, 3, 5, 4);
		board.movePiece(4, 6, 4, 4);
		List<Field> allMoves = blackPawn.getAllLegalMoves(board, 5, 4);
		assertEquals(2, allMoves.size());
		assertTrue(allMoves.contains(new Field(4, 5)));
		assertTrue(allMoves.contains(new Field(5, 5)));
	}
	
	@Test
	public void enPassantMoveRemovesTheTakenPawn() {
		Board board = new Board();
		board.setup();
		board.movePiece(4, 6, 4, 4);
		board.movePiece(4, 4, 4, 3);
		board.movePiece(5, 1, 5, 3);
		board.movePiece(4, 3, 5, 2);
		assertTrue(board.getPiece(5, 3) == null);
	}
	
	@Test
	public void whitePawnPromotesToQueenAtTheLastRow() {
		Board board = new Board();
		board.setup();
		board.movePiece(5, 6, 5, 4);
		board.movePiece(5, 4, 5, 3);
		board.movePiece(5, 3, 5, 2);
		board.movePiece(5, 2, 6, 1);
		board.movePiece(6, 1, 5, 0);
		assertTrue(board.getPiece(5, 0) instanceof Queen);
	}
	
	@Test
	public void blackPawnPromotesToQueenAtTheLastRow() {
		Board board = new Board();
		board.setup();
		board.movePiece(0, 1, 0, 3);
		board.movePiece(0, 3, 0, 4);
		board.movePiece(0, 4, 0, 5);
		board.movePiece(0, 5, 1, 6);
		board.movePiece(1, 6, 2, 7);
		assertTrue(board.getPiece(2, 7) instanceof Queen);
	}

}
