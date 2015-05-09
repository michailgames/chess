package chess.model.pieces;

/**
 * Projekt: Szachy
 * Test klasy skoczka
 * Micha³ Rapacz
 * 2015-03-26
 */

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class KnightTest {

	@Test
	public void testCopy() {
		Knight oldKnight = new Knight(Color.BLACK, 2, 7);
		Piece newKnight = oldKnight.copy();
		assertTrue(newKnight instanceof Knight &&
				newKnight.getColor() == Color.BLACK && newKnight.getX() == 2 &&
				newKnight.getY() == 7);
	}
	
	@Test
	public void canMakeKnightMovesFromStartPosition() {
		Board board = new Board();
		board.setup();
		Knight knight = (Knight) board.getPiece(6, 7);
		List<Field> allMoves = knight.getAllLegalMoves(board, 6, 7);
		assertEquals(2, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 5)));
		assertTrue(allMoves.contains(new Field(7, 5)));
	}
	
	@Test
	public void canMakeKnightMovesInTheMiddleBoard() {
		Board board = new Board();
		board.setup();
		Knight knight = (Knight) board.getPiece(6, 7);
		board.movePiece(6, 7, 5, 5);
		board.movePiece(5, 5, 4, 3);
		List<Field> allMoves = knight.getAllLegalMoves(board, 4, 3);
		assertEquals(8, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 5)));
		assertTrue(allMoves.contains(new Field(3, 5)));
		assertTrue(allMoves.contains(new Field(2, 4)));
		assertTrue(allMoves.contains(new Field(2, 2)));
		assertTrue(allMoves.contains(new Field(3, 1)));
		assertTrue(allMoves.contains(new Field(5, 1)));
		assertTrue(allMoves.contains(new Field(6, 2)));
		assertTrue(allMoves.contains(new Field(6, 4)));
	}
}
