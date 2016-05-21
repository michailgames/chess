package chess.model.pieces;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import chess.model.board.Board;
import chess.model.board.Color;
import chess.model.board.Field;

public class PieceTest {

    @Test
    public void getColorReturnsPieceColor() {
        Piece piece = makePiece(Color.WHITE);
        assertTrue(piece.getColor() == Color.WHITE);

    }

    @Test
    public void getOppositeColorReturnsOppositeColor() {
        Piece piece = makePiece(Color.WHITE);
        assertTrue(piece.getOppositeColor() == Color.BLACK);
    }

    @Test
    public void positionWithCoordinateBelowZeroIsOutsideTheBoard() {
        Piece piece = makePiece(Color.WHITE);
        assertFalse(piece.isPositionInsideTheBoard(-1, 0));
    }

    @Test
    public void positionWithTooBigCoordinateIsOutsideTheBoard() {
        Piece piece = makePiece(Color.WHITE);
        assertFalse(piece.isPositionInsideTheBoard(0, 8));
    }

    @Test
    public void positionWithSaneCoordinatesIsInsideTheBoard() {
        Piece piece = makePiece(Color.WHITE);
        assertTrue(piece.isPositionInsideTheBoard(4, 5));
    }

    @Test
    public void getAllLegalMovesFiltersMovesThatAreNotSafeForKing() {
        Board board = new Board();
        board.setup();
        board.movePiece(4, 6, 4, 5);
        board.movePiece(3, 1, 3, 2);
        Pawn pawnThatWillGuardKing = (Pawn) board.getPiece(5, 1);
        assertFalse(pawnThatWillGuardKing.getAllLegalMoves(board, 5, 1).isEmpty());
        board.movePiece(3, 7, 7, 3);
        assertTrue(pawnThatWillGuardKing.getAllLegalMoves(board, 5, 1).isEmpty());
    }

    public static Piece makePiece(Color color) {
        return new Piece(color) {

            @Override
            public String getUnicodeString() {
                return null;
            }

            @Override
            public List<Field> getAllPotentialMoves(Board board, int startX, int startY) {
                return null;
            }
        };
    }
}
