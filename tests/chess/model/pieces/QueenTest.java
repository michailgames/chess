package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

/**
 * Projekt: Szachy
 * Test kr�lowej
 * Micha� Rapacz
 * 2015-03-26
 */

public class QueenTest {
	
	@Test
	public void cannotMoveAtTheBeginning() {
		Board board = new Board();
		board.setup();
		Queen queen = (Queen) board.getPiece(3, 7);
		List<Field> allMoves = queen.getAllLegalMoves(board, 3, 7);
		assertTrue(allMoves.isEmpty());
	}
	
	
	@Test
	public void canMoveInStraightLinesAndDiagonallyUntilObstacleMet() {
		Board board = new Board();
		board.setup();
		Queen queen = (Queen) board.getPiece(3, 7);
		board.movePiece(4, 6, 4, 5);
		board.movePiece(3, 7, 6, 4);
		List<Field> allMoves = queen.getAllLegalMoves(board, 6, 4);
		assertEquals(19, allMoves.size());
		assertTrue(allMoves.contains(new Field(5, 3)));
		assertTrue(allMoves.contains(new Field(4, 2)));
		assertTrue(allMoves.contains(new Field(3, 1)));
		assertTrue(allMoves.contains(new Field(6, 3)));
		assertTrue(allMoves.contains(new Field(6, 2)));
		assertTrue(allMoves.contains(new Field(6, 1)));
		assertTrue(allMoves.contains(new Field(6, 1)));
		assertTrue(allMoves.contains(new Field(7, 3)));
		assertTrue(allMoves.contains(new Field(7, 4)));
		assertTrue(allMoves.contains(new Field(7, 5)));
		assertTrue(allMoves.contains(new Field(6, 5)));
		assertTrue(allMoves.contains(new Field(5, 5)));
		assertTrue(allMoves.contains(new Field(4, 6)));
		assertTrue(allMoves.contains(new Field(3, 7)));
		assertTrue(allMoves.contains(new Field(5, 4)));
		assertTrue(allMoves.contains(new Field(4, 4)));
		assertTrue(allMoves.contains(new Field(3, 4)));
		assertTrue(allMoves.contains(new Field(2, 4)));
		assertTrue(allMoves.contains(new Field(1, 4)));
		assertTrue(allMoves.contains(new Field(0, 4)));
	}
}
