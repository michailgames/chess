package chess.model.pieces;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Field;

/**
 * Projekt: Szachy
 * Test wie¿y
 * Micha³ Rapacz
 * 2015-03-26
 */

public class RookTest {
	
	@Test
	public void cannotMoveAtTheBeginning() {
		Board board = new Board();
		board.setup();
		Rook rook = (Rook) board.getPiece(7, 7);
		List<Field> allMoves = rook.getAllLegalMoves(board, 7, 7);
		assertTrue(allMoves.isEmpty());
	}
	
	@Test
	public void canMoveInStraightLinesUntilObstacleMet() {
		Board board = new Board();
		board.setup();
		Rook rook = (Rook) board.getPiece(7, 7);
		board.movePiece(7, 6, 7, 4);
		board.movePiece(7, 7, 7, 5);
		board.movePiece(7, 5, 5, 5);
		board.movePiece(5, 5, 5, 4);
		List<Field> allMoves = rook.getAllLegalMoves(board, 5, 4);
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
