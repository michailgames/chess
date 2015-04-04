package chess.model.pieces;

/**
 * Projekt: Szachy
 * Test biskupa
 * Micha³ Rapacz
 * 2015-03-26
 */

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class BishopTest {

	@Test
	public void testCopy() {
		Bishop oldBishop = new Bishop(Color.BLACK, 2, 7);
		Piece newBishop = oldBishop.copy();
		assertTrue(newBishop instanceof Bishop &&
				newBishop.getColor() == Color.BLACK && newBishop.getX() == 2 &&
				newBishop.getY() == 7);
	}
	
	@Test
	public void cannotMoveAtTheBeginning() {
		Board board = new Board();
		board.setup();
		Bishop bishop = (Bishop) board.getPiece(5, 7);
		List<Field> allMoves = bishop.getAllLegalMoves(board);
		assertTrue(allMoves.isEmpty());
	}
	
	@Test
	public void canMoveDiagonallyUntilObstacleMet() {
		Board board = new Board();
		board.setup();
		Bishop bishop = (Bishop) board.getPiece(5, 7);
		board.movePiece(board.getPiece(4, 6), 4, 5);
		board.movePiece(bishop, 2, 4);
		List<Field> allMoves = bishop.getAllLegalMoves(board);
		assertEquals(9, allMoves.size());
		assertTrue(allMoves.contains(new Field(1, 3)));
		assertTrue(allMoves.contains(new Field(0, 2)));
		assertTrue(allMoves.contains(new Field(1, 5)));
		assertTrue(allMoves.contains(new Field(3, 3)));
		assertTrue(allMoves.contains(new Field(4, 2)));
		assertTrue(allMoves.contains(new Field(5, 1)));
		assertTrue(allMoves.contains(new Field(3, 5)));
		assertTrue(allMoves.contains(new Field(4, 6)));
		assertTrue(allMoves.contains(new Field(5, 7)));
	}

}
