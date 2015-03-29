package chess.model.pieces;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

/**
 * Projekt: Szachy
 * Test piona
 * Micha³ Rapacz
 * 2015-03-29
 */

public class KingTest {

	@Test
	public void testCopy() {
		King oldKing = new King(Color.BLACK, 2, 7);
		Piece newKing = oldKing.copy();
		assertTrue(newKing instanceof King &&
				newKing.getColor() == Color.BLACK && newKing.getX() == 2 &&
				newKing.getY() == 7);
	}
	
	@Test
	public void cannotMoveAtTheBeginning() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertTrue(allMoves.isEmpty());
	}
	
	@Test
	public void canMoveOneFieldInEachDirection() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		board.movePiece(board.getPiece(4, 6), 4, 4);
		board.movePiece(board.getPiece(5, 1), 5, 2);
		board.movePiece(king, 5, 1);
		board.movePiece(king, 4, 2);
		board.movePiece(king, 4, 3);
		List<Field> allMoves = king.getAllPotentialMoves(board);
		assertEquals(7, allMoves.size());
		assertTrue(allMoves.contains(new Field(3, 2)));
		assertTrue(allMoves.contains(new Field(4, 2)));
		assertTrue(allMoves.contains(new Field(3, 3)));
		assertTrue(allMoves.contains(new Field(5, 3)));
		assertTrue(allMoves.contains(new Field(3, 4)));
		assertTrue(allMoves.contains(new Field(4, 4)));
		assertTrue(allMoves.contains(new Field(5, 4)));
	}
	
	@Test
	public void canDoShortCastlingWhenItIsSafeAndThereIsAPlace() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertTrue(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void cannotDoShortCastlingWhenThereIsAPieceInBetween() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		board.movePiece(board.getPiece(6, 0), 7, 2);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void cannotDoShortCastlingIfIsUnderAttack() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		Knight whiteKnight = (Knight) board.getPiece(1, 7);
		board.movePiece(whiteKnight, 2, 5);
		board.movePiece(whiteKnight, 3, 3);
		board.movePiece(whiteKnight, 2, 1);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void cannotDoShortCastlingIfHasMovedBefore() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		board.movePiece(king, 5, 0);
		board.movePiece(king, 4, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void cannotDoShortCastlingIfRookHasMovedBefore() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		Rook rook = (Rook) board.getPiece(7, 0);
		board.movePiece(rook, 6, 0);
		board.movePiece(rook, 7, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void cannotDoShortCastlingIfHasToPassThroughAttackedField() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		board.movePiece(board.getPiece(1, 6), 1, 5);
		board.movePiece(board.getPiece(2, 7), 1, 6);
		board.movePiece(board.getPiece(1, 6), 6, 1);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(6, 0)));
	}
	
	@Test
	public void shortCastlingMovesTheRook() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareShortCastlingForBlack(board);
		board.movePiece(king, 6, 0);
		assertTrue(board.getPiece(5, 0) instanceof Rook);
	}
	
	@Test
	public void canDoLongCastlingWhenItIsSafeAndThereIsAPlace() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertTrue(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void cannotDoLongCastlingWhenThereIsAPieceInBetween() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		board.movePiece(board.getPiece(3, 1), 3, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void cannotDoLongCastlingIfIsUnderAttack() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		Knight whiteKnight = (Knight) board.getPiece(1, 7);
		board.movePiece(whiteKnight, 2, 5);
		board.movePiece(whiteKnight, 3, 3);
		board.movePiece(whiteKnight, 2, 1);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void cannotDoLongCastlingIfHasMovedBefore() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		board.movePiece(king, 3, 0);
		board.movePiece(king, 4, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void cannotDoLongCastlingIfRookHasMovedBefore() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		Rook rook = (Rook) board.getPiece(0, 0);
		board.movePiece(rook, 2, 0);
		board.movePiece(rook, 0, 0);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void cannotDoLongCastlingIfHasToPassThroughAttackedField() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		board.movePiece(board.getPiece(3, 6), 3, 5);
		board.movePiece(board.getPiece(2, 7), 5, 4);
		board.movePiece(board.getPiece(5, 4), 2, 1);
		List<Field> allMoves = king.getAllLegalMoves(board);
		assertFalse(allMoves.contains(new Field(2, 0)));
	}
	
	@Test
	public void longCastlingMovesTheRook() {
		Board board = new Board();
		board.setup();
		King king = (King) board.getPiece(4, 0);
		prepareLongCastlingForBlack(board);
		board.movePiece(king, 2, 0);
		assertTrue(board.getPiece(3, 0) instanceof Rook);
	}

	private void prepareShortCastlingForBlack(Board board) {
		board.movePiece(board.getPiece(6, 0), 7, 2);
		board.movePiece(board.getPiece(6, 1), 6, 3);
		board.movePiece(board.getPiece(5, 0), 6, 1);
	}
	
	private void prepareLongCastlingForBlack(Board board) {
		board.movePiece(board.getPiece(1, 0), 0, 2);
		board.movePiece(board.getPiece(1, 1), 1, 3);
		board.movePiece(board.getPiece(2, 0), 1, 1);
		board.movePiece(board.getPiece(3, 1), 3, 2);
		board.movePiece(board.getPiece(3, 0), 3, 1);
	}

}
