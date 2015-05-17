package chess.controller;

import java.util.ArrayList;
import java.util.List;

import chess.model.board.Field;
import chess.model.pieces.Piece;

/**
 * Projekt: Szachy
 * Kontroler logowania stanu partii
 * Micha³ Rapacz
 * 2015-05-17
 */

public class LogController {

	private static LogController instance;
	
	private final String[] rankNames = { "a", "b", "c", "d", "e", "f", "g", "h" };
	private List<String> loggedMoves = new ArrayList<String>();
	
	private LogController() { }
	
	public synchronized static LogController getInstance() {
		if(instance == null) {
			instance = new LogController();
		}
		return instance;
	}
	
	public synchronized void clearLogs() {
		loggedMoves.clear();
	}
	
	public synchronized void reportMove(Piece piece, Field sourceField,
			Field targetField) {
		loggedMoves.add(piece.getUnicodeString() + " " + getFieldIdentifier(
				sourceField) + "\u2192" + getFieldIdentifier(targetField));
	}
	
	public synchronized int getLogsNumber() {
		return loggedMoves.size();
	}
	
	public synchronized String getLog(int moveIndex) {
		if(moveIndex < loggedMoves.size()) {
			return loggedMoves.get(moveIndex);
		}
		return "";
	}
	
	private String getFieldIdentifier(Field field) {
		return rankNames[field.getX()] + Integer.toString(8 - field.getY());
	}
}
