package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.Board;
import chess.model.Color;
import chess.model.Field;
import chess.model.Piece;

/**
 * Projekt: Szachy
 * Test wie¿y
 * Micha³ Rapacz
 * 2015-03-26
 */

public class RookTest {

	@Test
	public void testCopy() {
		Rook oldRook = new Rook(Color.BLACK, 2, 7);
		Piece newRook = oldRook.copy();
		assertTrue(newRook instanceof Rook &&
				newRook.getColor() == Color.BLACK && newRook.getX() == 2 &&
				newRook.getY() == 7);
	}
	
	@Test
	public void cannotMoveAtTheBeginning() {
		Board board = new Board();
		board.setup();
		Rook rook = (Rook) board.getPiece(7, 7);
		List<Field> allMoves = rook.getAllLegalMoves(board);
		assertTrue(allMoves.isEmpty());
	}
	
	@Test
	public void canMoveInStraightLinesUntilObstacleMet() {
		Board board = new Board();
		board.setup();
		Rook rook = (Rook) board.getPiece(7, 7);
		board.movePiece(board.getPiece(7, 6), 7, 4);
		board.movePiece(rook, 7, 5);
		board.movePiece(rook, 5, 5);
		board.movePiece(rook, 5, 4);
		List<Field> allMoves = rook.getAllLegalMoves(board);
		assertEquals(10, allMoves.size());
		assertTrue(allMoves.contains(new Field(6, 4)));
		assertTrue(allMoves.contains(new Field(5, 5)));
		assertTrue(allMoves.contains(new Field(4, 4)));
		assertTrue(allMoves.contains(new Field(3, 4)));
		assertTrue(allMoves.contains(new Field(2, 4)));
		assertTrue(allMoves.contains(new Field(1, 4)));
		assertTrue(allMoves.contains(new Field(0, 4)));
		assertTrue(allMoves.contains(new Field(5, 3)));
		assertTrue(allMoves.contains(new Field(5, 2)));
		assertTrue(allMoves.contains(new Field(5, 1)));
	}

}
